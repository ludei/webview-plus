package com.ludei.chromium;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

public class LudeiWebView extends WebView {

    private LudeiContentView proxy;

    public LudeiWebView(Context context) {
        this(context, null);
    }


    public LudeiWebView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.webViewStyle);
    }


    public LudeiWebView(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs, defStyle, null);
    }

    public LudeiWebView(Context context, AttributeSet attrs, int defStyle, boolean privateBrowsing) {
        this(context, attrs, defStyle, null);
    }

    public LudeiWebView(Context context, AttributeSet attrs, int defStyle, LudeiContentView proxyContentView) {

        super(context, attrs, defStyle);
        this.proxy = proxyContentView;

        if (this.proxy == null) {
            this.proxy = new LudeiContentView(context, null);
            this.addView(proxy, new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
        }
    }

    @Override
    public void onPause() {
        proxy.onPause();
    }

    @Override
    public void onResume() {
        proxy.onResume();
    }

    @Override
    public WebSettings getSettings() {
        if (proxy == null)
            return super.getSettings();

        return proxy.getSettings();
    }


    @Override
    public void setHorizontalScrollbarOverlay(boolean overlay) {
        proxy.setHorizontalScrollbarOverlay(overlay);

    }

    @Override
    public void setVerticalScrollbarOverlay(boolean overlay) {
        proxy.setVerticalScrollbarOverlay(overlay);
    }

    @Override
    public boolean overlayHorizontalScrollbar() {
        return proxy.overlayHorizontalScrollbar();
    }

    @Override
    public boolean overlayVerticalScrollbar() {
        return proxy.overlayVerticalScrollbar();
    }

    @Override
    public void savePassword(String host, String username, String password) {
        proxy.savePassword(host, username, password);
    }

    @Override
    public void setHttpAuthUsernamePassword(String host, String realm,
                                            String username, String password) {
        proxy.setHttpAuthUsernamePassword(host, realm, username, password);
    }

    @Override
    public String[] getHttpAuthUsernamePassword(String host, String realm) {
        return proxy.getHttpAuthUsernamePassword(host, realm);
    }

    @Override
    public void destroy() {
        if (proxy != null)
            proxy.destroy();
    }

    @Override
    public void loadUrl(String url) {
        proxy.loadUrl(url);
    }

    @Override
    public void loadData(String data, String mimeType, String encoding) {
        proxy.loadData(data, mimeType, encoding);
    }

    @Override
    public void loadDataWithBaseURL(String baseUrl, String data,
                                    String mimeType, String encoding, String failUrl) {
        proxy.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, failUrl);
    }

    @Override
    public void stopLoading() {
        proxy.stopLoading();
    }

    @Override
    public void reload() {
        proxy.reload();
    }

    @Override
    public boolean canGoBack() {
        return proxy.canGoBack();
    }

    @Override
    public void goBack() {
        proxy.goBack();
    }

    @Override
    public boolean canGoForward() {
        return proxy.canGoForward();
    }

    @Override
    public void goForward() {
        proxy.goForward();
    }

    @Override
    public boolean canGoBackOrForward(int steps) {
        return proxy.canGoBackOrForward(steps);
    }

    @Override
    public void goBackOrForward(int steps) {
        proxy.goBackOrForward(steps);
    }

    @Override
    public boolean pageUp(boolean top) {
        return proxy.pageUp(top);
    }

    @Override
    public boolean pageDown(boolean bottom) {
        return proxy.pageDown(bottom);
    }

    @Override
    public void clearView() {
        proxy.clearView();
    }

    @Override
    public Picture capturePicture() {
        return proxy.capturePicture();
    }

    @Override
    public float getScale() {
        return proxy.getScale();
    }

    @Override
    public void setInitialScale(int scaleInPercent) {
        proxy.setInitialScale(scaleInPercent);
    }

    @Override
    public void invokeZoomPicker() {
        proxy.invokeZoomPicker();
    }

    @Override
    public void requestFocusNodeHref(Message hrefMsg) {
        proxy.requestFocusNodeHref(hrefMsg);
    }

    @Override
    public void requestImageRef(Message msg) {
        proxy.requestImageRef(msg);
    }

    @Override
    public String getUrl() {
        return proxy.getUrl();
    }

    @Override
    public String getTitle() {
        return proxy.getTitle();
    }

    @Override
    public Bitmap getFavicon() {
        return proxy.getFavicon();
    }

    @Override
    public int getProgress() {
        return proxy.getProgress();
    }

    @Override
    public int getContentHeight() {
        return proxy.getContentHeight();
    }

    @Override
    public void pauseTimers() {
        proxy.pauseTimers();
    }

    @Override
    public void resumeTimers() {
        proxy.resumeTimers();
    }

    //@Override
    public void clearCache() {
        proxy.clearCache();
    }

    @Override
    public void clearFormData() {
        proxy.clearFormData();;
    }

    @Override
    public void clearHistory() {
        proxy.clearHistory();
    }

    @Override
    public void clearSslPreferences() {
        proxy.clearSslPreferences();
    }


    @Override
    public void documentHasImages(Message response) {
        proxy.documentHasImages(response);
    }

    @Override
    public void setWebViewClient(WebViewClient client) {
        proxy.setWebViewClient(client);
    }

    @Override
    public void setDownloadListener(DownloadListener listener) {
        proxy.setDownloadListener(listener);
    }

    @Override
    public void setWebChromeClient(WebChromeClient client) {
        proxy.setWebChromeClient(client);
    }

    @Override
    public void addJavascriptInterface(Object obj, String interfaceName) {
        proxy.addJavascriptInterface(obj, interfaceName);
    }

    //@Override
    public View getZoomControls() {
        return proxy.getZoomControls();
    }

    @Override
    public boolean zoomIn() {
        return proxy.zoomIn();
    }

    @Override
    public boolean zoomOut() {
        return proxy.zoomOut();
    }

    @Override
    public void setNetworkAvailable(boolean networkUp) {
        proxy.setNetworkAvailable(networkUp);
    }
    
    public WebBackForwardList copyBackForwardList() {
    	return proxy.copyBackForwardList();
    }
    
    @Override
    public View getFocusedChild() {
    	if (!canGoBack())
    		return null;
    	
    	return super.getFocusedChild();
    }
}
