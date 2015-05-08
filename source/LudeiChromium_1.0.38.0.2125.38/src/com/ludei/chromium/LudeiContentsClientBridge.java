package com.ludei.chromium;

import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.http.SslCertificate;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.*;

import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.base.ThreadUtils;
import org.chromium.components.navigation_interception.InterceptNavigationDelegate;
import org.chromium.components.navigation_interception.NavigationParams;
import org.chromium.content.browser.ContentVideoViewClient;
import org.chromium.content.browser.ContentViewDownloadDelegate;


@JNINamespace("ludei")
public class LudeiContentsClientBridge {

    private WebView contentView;
    private WebViewClient mWebViewClient;
    private WebChromeClient mWebChromeClient;
    private LudeiDefaultWebChromeClient mDefaultWebChromeClient;
    private Bitmap mFavicon;
    private DownloadListener mDownloadListener;
    private InterceptNavigationDelegate mInterceptNavigationDelegate;

    // The native peer of the object
    private int mNativeContentsClientBridge;

    public LudeiContentsClientBridge(WebView view, LudeiDefaultWebChromeClient defaultClient) {
        contentView = view;
        mDefaultWebChromeClient = defaultClient;
    }

    public void setWebChromeClient(WebChromeClient client) {
        mWebChromeClient = client;
    }

    public WebChromeClient getWebChromeClient() {
        return mWebChromeClient;
    }

    public void setwebViewClient(WebViewClient client) {
        mWebViewClient = client;
    }

    public InterceptNavigationDelegate getInterceptNavigationDelegate() {
        return mInterceptNavigationDelegate;
    }


    public boolean shouldOverrideUrlLoading(String url) {
        if (mWebViewClient != null && contentView != null)
            return mWebViewClient.shouldOverrideUrlLoading(contentView, url);
        return false;
    }


    public void onUnhandledKeyEvent(KeyEvent event) {
    }


    public void getVisitedHistory(ValueCallback<String[]> callback) {
    }

    
    public void doUpdateVisitedHistory(String url, boolean isReload) {
    }

    
    public void onProgressChanged(int progress) {
        if (mWebChromeClient != null && contentView != null)
            mWebChromeClient.onProgressChanged(contentView, progress);
    }

    
    public WebResourceResponse shouldInterceptRequest(String url) {
        if (mWebViewClient != null && contentView != null)
            return mWebViewClient.shouldInterceptRequest(contentView, url);
        return null;
    }

    
    public void onLoadResource(String url) {
        if (mWebViewClient != null && contentView != null)
            mWebViewClient.onLoadResource(contentView, url);
    }

    
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return false;
    }

    @CalledByNative
    public void onReceivedHttpAuthRequest(HttpAuthHandler handler, String host, String realm) {
        if (mWebViewClient != null && contentView != null)
            mWebViewClient.onReceivedHttpAuthRequest(contentView, handler, host, realm);
    }
    
    public void onReceivedSslError(SslErrorHandler handler, SslError error) {
        if (mWebViewClient != null && contentView != null)
            mWebViewClient.onReceivedSslError(contentView, handler, error);
    }

    
    public void onReceivedLoginRequest(String realm, String account, String args) {
    }

    
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        if (mWebChromeClient != null) {
            mWebChromeClient.onGeolocationPermissionsShowPrompt(origin, callback);
        }
    }

    
    public void onGeolocationPermissionsHidePrompt() {
        if (mWebChromeClient != null) {
            mWebChromeClient.onGeolocationPermissionsHidePrompt();
        }
    }

    
    public void handleJsAlert(String url, String message, JsResult result) {
        if (mWebChromeClient != null && contentView != null) {
            mWebChromeClient.onJsAlert(contentView, url, message, result);
        }
    }

    
    public void handleJsBeforeUnload(String url, String message, JsResult result) {
        if (mWebChromeClient != null && contentView != null) {
            mWebChromeClient.onJsBeforeUnload(contentView, url, message, result);
        }
    }

    
    public void handleJsConfirm(String url, String message, JsResult result) {
        if (mWebChromeClient != null && contentView != null) {
            mWebChromeClient.onJsConfirm(contentView, url, message, result);
        }
    }

    
    public void handleJsPrompt(String url, String message, String defaultValue, JsPromptResult result) {
        if (mWebChromeClient != null && contentView != null) {
            mWebChromeClient.onJsPrompt(contentView, url, message, defaultValue, result);
        }
    }

    
    public void onFindResultReceived(int activeMatchOrdinal, int numberOfMatches,
                                     boolean isDoneCounting) {
    }

    
    public void onNewPicture(Picture picture) {
    }

    
    public void onPageStarted(String url) {
        if (mWebViewClient != null && contentView != null) {
            mWebViewClient.onPageStarted(contentView, url, mFavicon);
        }
    }

    
    public void onPageFinished(String url) {
        if (mWebViewClient != null && contentView != null) {
            mWebViewClient.onPageFinished(contentView, url);
        }
    }

    
    public void onReceivedError(int errorCode, String description, String failingUrl) {
        if (mWebViewClient != null && contentView != null) {
            mWebViewClient.onReceivedError(contentView, errorCode, description, failingUrl);
        }
    }

    
    /*public void onRendererUnresponsive() {
        if (mWebViewClient != null && contentView != null) {
            mWebViewClient.onRendererUnresponsive(contentView);
        }
    }

    
    public void onRendererResponsive() {
        if (mWebViewClient != null && contentView != null) {
            mWebViewClient.onRendererResponsive(contentView);
        }
    } */

    
    public void onFormResubmission(Message dontResend, Message resend) {
        dontResend.sendToTarget();
    }

    
    public void onDownloadStart(String url,
                                String userAgent,
                                String contentDisposition,
                                String mimeType,
                                long contentLength) {
    }

    
    public boolean onCreateWindow(boolean isDialog, boolean isUserGesture) {
        return false;
    }

    
    /*public void onCloseWindow() {
        if (mWebViewClient != null) {
            mWebViewClient.onCloseWindow(contentView);
        }
    }*/

    
    public void onRequestFocus() {
    }

    
    public void onReceivedTouchIconUrl(String url, boolean precomposed) {
        if (mWebChromeClient != null && contentView != null) {
            mWebChromeClient.onReceivedTouchIconUrl(contentView, url, precomposed);
        }
    }

    
    public void onReceivedIcon(Bitmap bitmap) {
        if (mWebChromeClient != null && contentView != null) {
            mWebChromeClient.onReceivedIcon(contentView, bitmap);
        }
        mFavicon = bitmap;
    }

    
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        if (mWebChromeClient != null) {
            mWebChromeClient.onShowCustomView(view, callback);
        }
    }

    
    public void onHideCustomView() {
        if (mWebChromeClient != null) {
            mWebChromeClient.onHideCustomView();
        }
    }

    
    public void onScaleChangedScaled(float oldScale, float newScale) {
    }

    
    protected View getVideoLoadingProgressView() {
        if (mWebChromeClient != null)
            return mWebChromeClient.getVideoLoadingProgressView();
        return null;
    }

    
    public Bitmap getDefaultVideoPoster() {
        return null;
    }

    
    public void didFinishLoad(String url) {
    }

    
    public void onTitleChanged(String title) {
        if (mWebChromeClient != null && contentView != null) {
            mWebChromeClient.onReceivedTitle(contentView, title);
        }
    }

    // Used by the native peer to set/reset a weak ref to the native peer.
    @CalledByNative
    private void setNativeContentsClientBridge(int nativeContentsClientBridge) {
        mNativeContentsClientBridge = nativeContentsClientBridge;
    }

    // If returns false, the request is immediately canceled, and any call to proceedSslError
    // has no effect. If returns true, the request should be canceled or proceeded using
    // proceedSslError().
    // Unlike the webview classic, we do not keep keep a database of certificates that
    // are allowed by the user, because this functionality is already handled via
    // ssl_policy in native layers.
    @CalledByNative
    private boolean allowCertificateError(int certError, byte[] derBytes, final String url,
                                          final int id) {
        // TODO(yongsheng): Implement this.
        return false;
    }

    private void proceedSslError(boolean proceed, int id) {
        if (mNativeContentsClientBridge == 0) return;
        nativeProceedSslError(mNativeContentsClientBridge, proceed, id);
    }

    @CalledByNative
    private void handleJsAlert(String url, String message, int id) {
        LudeiJsResult result = LudeiJsResultHandler.createJsResult(this, id);
        mDefaultWebChromeClient.onJsAlert(contentView, url, message, result);

        //TODO check user WebChromeClient before=> Fix JsResult inheritance...
        //handleJsAlert(url, message, handler);
    }

    @CalledByNative
    private void handleJsConfirm(String url, String message, int id) {
        LudeiJsResult result = LudeiJsResultHandler.createJsResult(this, id);
        mDefaultWebChromeClient.onJsConfirm(contentView, url, message, result);

        //TODO check user WebChromeClient before=> Fix JsResult inheritance...
        //handleJsConfirm(url, message, handler);
    }

    @CalledByNative
    private void handleJsPrompt(String url, String message, String defaultValue, int id) {
        LudeiJsPromptResult result = LudeiJsResultHandler.createJsPromptResult(this, id);
        mDefaultWebChromeClient.onJsPrompt(contentView, url, message, defaultValue, result);

        //TODO check user WebChromeClient before=> Fix JsResult inheritance...
        //handleJsPrompt(url, message, defaultValue, handler);
    }

    @CalledByNative
    private void handleJsBeforeUnload(String url, String message, int id) {

        //TODO check user WebChromeClient before=> Fix JsResult inheritance...
        //handleJsBeforeUnload(url, message, handler);
    }

    void confirmJsResult(int id, String prompt) {
        if (mNativeContentsClientBridge == 0) return;
        nativeConfirmJsResult(mNativeContentsClientBridge, id, prompt);
    }

    void cancelJsResult(int id) {
        if (mNativeContentsClientBridge == 0) return;
        nativeCancelJsResult(mNativeContentsClientBridge, id);
    }

    void exitFullscreen(int nativeWebContents) {
        if (mNativeContentsClientBridge == 0) return;
        nativeExitFullscreen(mNativeContentsClientBridge, nativeWebContents);
    }

    void setDownloadListener(DownloadListener listener) {
        mDownloadListener = listener;
    }

    // Implement ContentViewDownloadDelegate methods.
    public void requestHttpGetDownload(String url, String userAgent, String contentDisposition,
                                       String mimetype, String cookie, String referer, long contentLength) {
        if (mDownloadListener != null) {
            mDownloadListener.onDownloadStart(url, userAgent,
                    contentDisposition, mimetype, contentLength);
        }
    }

    public void onDownloadStarted(String filename, String mimeType) {
    }

    public void onDangerousDownload(String filename, int downloadId) {
    }

    //--------------------------------------------------------------------------------------------
    //  Native methods
    //--------------------------------------------------------------------------------------------
    private native void nativeProceedSslError(int nativeContentsClientBridge,
                                              boolean proceed, int id);

    private native void nativeConfirmJsResult(int nativeContentsClientBridge, int id,
                                              String prompt);
    private native void nativeCancelJsResult(int nativeContentsClientBridge, int id);
    private native void nativeExitFullscreen(int nativeContentsClientBridge, int nativeWebContents);


    //----------------------------
    // Helper Methods
    //----------------------------


    private static class DownloadInfo {
        final String mUrl;
        final String mUserAgent;
        final String mContentDisposition;
        final String mMimeType;
        final long mContentLength;

        DownloadInfo(String url,
                     String userAgent,
                     String contentDisposition,
                     String mimeType,
                     long contentLength) {
            mUrl = url;
            mUserAgent = userAgent;
            mContentDisposition = contentDisposition;
            mMimeType = mimeType;
            mContentLength = contentLength;
        }
    }

    private static class LoginRequestInfo {
        final String mRealm;
        final String mAccount;
        final String mArgs;

        LoginRequestInfo(String realm, String account, String args) {
            mRealm = realm;
            mAccount = account;
            mArgs = args;
        }
    }

    private static class OnReceivedErrorInfo {
        final int mErrorCode;
        final String mDescription;
        final String mFailingUrl;

        OnReceivedErrorInfo(int errorCode, String description, String failingUrl) {
            mErrorCode = errorCode;
            mDescription = description;
            mFailingUrl = failingUrl;
        }
    }

    private final static int MSG_ON_LOAD_RESOURCE = 1;
    private final static int MSG_ON_PAGE_STARTED = 2;
    private final static int MSG_ON_DOWNLOAD_START = 3;
    private final static int MSG_ON_RECEIVED_LOGIN_REQUEST = 4;
    private final static int MSG_ON_RECEIVED_ERROR = 5;

    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case MSG_ON_LOAD_RESOURCE: {
                    final String url = (String) msg.obj;
                    onLoadResource(url);
                    break;
                }
                case MSG_ON_PAGE_STARTED: {
                    final String url = (String) msg.obj;
                    onPageStarted(url);
                    break;
                }
                case MSG_ON_DOWNLOAD_START: {
                    DownloadInfo info = (DownloadInfo) msg.obj;
                    onDownloadStart(info.mUrl, info.mUserAgent,
                            info.mContentDisposition, info.mMimeType, info.mContentLength);
                    break;
                }
                case MSG_ON_RECEIVED_LOGIN_REQUEST: {
                    LoginRequestInfo info = (LoginRequestInfo) msg.obj;
                    onReceivedLoginRequest(info.mRealm, info.mAccount, info.mArgs);
                    break;
                }
                case MSG_ON_RECEIVED_ERROR: {
                    OnReceivedErrorInfo info = (OnReceivedErrorInfo) msg.obj;
                    onReceivedError(info.mErrorCode, info.mDescription,
                            info.mFailingUrl);
                    break;
                }
                default:
                    throw new IllegalStateException(
                            "XWalkContentsClientCallbackHelper: unhandled message " + msg.what);
            }
        }
    };

    public void postOnLoadResource(String url) {
        mHandler.sendMessage(mHandler.obtainMessage(MSG_ON_LOAD_RESOURCE, url));
    }

    public void postOnPageStarted(String url) {
        mHandler.sendMessage(mHandler.obtainMessage(MSG_ON_PAGE_STARTED, url));
    }

    public void postOnDownloadStart(String url, String userAgent, String contentDisposition,
                                    String mimeType, long contentLength) {
        DownloadInfo info = new DownloadInfo(url, userAgent, contentDisposition, mimeType,
                contentLength);
        mHandler.sendMessage(mHandler.obtainMessage(MSG_ON_DOWNLOAD_START, info));
    }

    public void postOnReceivedLoginRequest(String realm, String account, String args) {
        LoginRequestInfo info = new LoginRequestInfo(realm, account, args);
        mHandler.sendMessage(mHandler.obtainMessage(MSG_ON_RECEIVED_LOGIN_REQUEST, info));
    }

    public void postOnReceivedError(int errorCode, String description, String failingUrl) {
        OnReceivedErrorInfo info = new OnReceivedErrorInfo(errorCode, description, failingUrl);
        mHandler.sendMessage(mHandler.obtainMessage(MSG_ON_RECEIVED_ERROR, info));
    }



}
