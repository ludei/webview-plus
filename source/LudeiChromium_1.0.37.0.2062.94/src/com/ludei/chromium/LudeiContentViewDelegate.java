package com.ludei.chromium;

import android.webkit.WebResourceResponse;
import org.chromium.components.navigation_interception.InterceptNavigationDelegate;
import org.chromium.components.navigation_interception.NavigationParams;
import org.chromium.content.browser.ContentViewCore;
import org.chromium.content.browser.WebContentsObserverAndroid;

public class LudeiContentViewDelegate implements LudeiContentsIoThreadClient, InterceptNavigationDelegate {

    private LudeiContentView mContentView;
    private LudeiContentsClientBridge mBridge;
    private LudeiWebContentsObserver mWebContentsObserver;

    LudeiContentViewDelegate(LudeiContentView contentView, LudeiContentsClientBridge bridge){
        this.mContentView = contentView;
        this.mBridge = bridge;

    }


    @Override
    public boolean shouldIgnoreNavigation(NavigationParams navigationParams) {
        final String url = navigationParams.url;
        boolean ignoreNavigation = mBridge.shouldOverrideUrlLoading(url);

        if (!ignoreNavigation) {
            // Post a message to UI thread to notify the page is starting to load.
            mBridge.postOnPageStarted(url);
        }

        return ignoreNavigation;

    }

    /**
     * Override from LudeiContentsIoThreadClient
     */
    @Override
    public int getCacheMode() {
        return mContentView.getSettings().getCacheMode();
    }

    @Override
    public InterceptedRequestData shouldInterceptRequest(final String url,
                                                         boolean isMainFrame) {

        WebResourceResponse webResourceResponse = mBridge.shouldInterceptRequest(url);
        InterceptedRequestData interceptedRequestData = null;

        if (webResourceResponse == null) {
            mBridge.postOnLoadResource(url);
        } else {
            if (isMainFrame && webResourceResponse.getData() == null) {
                mBridge.postOnReceivedError(-1, null, url);
            }
            interceptedRequestData = new InterceptedRequestData(webResourceResponse.getMimeType(),
                    webResourceResponse.getEncoding(),
                    webResourceResponse.getData());
        }
        return interceptedRequestData;
    }

    @Override
    public boolean shouldBlockContentUrls() {
        return !mContentView.getSettings().getAllowContentAccess();
    }

    @Override
    public boolean shouldBlockFileUrls() {
        return !mContentView.getSettings().getAllowFileAccess();
    }

    @Override
    public boolean shouldBlockNetworkLoads() {
        return mContentView.getSettings().getBlockNetworkLoads();
    }

    @Override
    public void onDownloadStart(String url,
                                String userAgent,
                                String contentDisposition,
                                String mimeType,
                                long contentLength) {
        mBridge.postOnDownloadStart(url, userAgent, contentDisposition, mimeType, contentLength);
    }

    @Override
    public void newLoginRequest(String realm, String account, String args) {
        mBridge.postOnReceivedLoginRequest(realm, account, args);
    }


    public class LudeiWebContentsObserver extends WebContentsObserverAndroid {
        private LudeiContentsClientBridge mBridge;
        public LudeiWebContentsObserver(ContentViewCore contentViewCore, LudeiContentsClientBridge bridge) {
            super(contentViewCore);
            this.mBridge = bridge;
        }

        @Override
        public void didStopLoading(String url) {
            mBridge.onPageFinished(url);
        }

        @Override
        public void didFailLoad(boolean isProvisionalLoad, boolean isMainFrame, int errorCode, String description, String failingUrl) {
            if (isMainFrame) {
                mBridge.onReceivedError(errorCode, description, failingUrl);
            }
        }

        @Override
        public void didNavigateAnyFrame(String url, String baseUrl, boolean isReload) {
            mBridge.doUpdateVisitedHistory(url, isReload);
        }

        @Override
        public void didFinishLoad(long frameId, String validatedUrl, boolean isMainFrame) {
            if (isMainFrame) {
                mBridge.onPageFinished(validatedUrl);
            }
        }
    }


    public void onProgressChanged(double progress) {
        int bridgeProgress = (int) (progress * 100);
        mBridge.onProgressChanged(bridgeProgress);
    }

    public void installWebContentsObserver(ContentViewCore contentViewCore) {
        if (mWebContentsObserver != null) {
            mWebContentsObserver.detachFromWebContents();
        }
        mWebContentsObserver = new LudeiWebContentsObserver(contentViewCore, mBridge);
    }

    public LudeiContentsClientBridge getContentsClientBridge() {
        return mBridge;
    }

    public void setNativeContents(long nativeWebContents) {

        mContentView.getSettings().getAwSettings().setWebContents(nativeWebContents);
    }

}
