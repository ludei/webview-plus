package com.ludei.chromium;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.os.Bundle;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.*;
import android.widget.FrameLayout;
import android.widget.Toast;
import org.chromium.base.BaseSwitches;
import org.chromium.base.CommandLine;
import org.chromium.base.PathUtils;
import org.chromium.base.library_loader.ProcessInitException;
import org.chromium.content.browser.*;
import org.chromium.content.common.ContentSwitches;
import org.chromium.content_shell.Shell;
import org.chromium.content_shell.ShellManager;
import org.chromium.ui.base.ActivityWindowAndroid;
import org.chromium.ui.base.WindowAndroid;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

public class LudeiContentView extends FrameLayout {

    public static boolean REMOTE_DEBUGGING = true;

    /**
     * Construct a new WebView with a Context object.
     * @param context A Context object used to access application assets.
     */
    public LudeiContentView(Context context) {
        this(context, null);
    }

    /**
     * Construct a new WebView with layout parameters.
     * @param context A Context object used to access application assets.
     * @param attrs An AttributeSet passed to our parent.
     */
    public LudeiContentView(Context context, AttributeSet attrs) {
        this(context, attrs, null);
    }


    public LudeiContentView(Context context, AttributeSet attrs, WebView proxyWebView) {
        super(context, attrs);
        this.mProxyWebView = proxyWebView;
        this.initialize(context);
    }

    public void onPause() {
        ContentViewCore contentView = this.getContentViewCore();
        if (contentView != null) {
            contentView.onHide();
        }
    }

    public void onResume() {
        ContentViewCore contentView = this.getContentViewCore();
        if (contentView != null) {
            contentView.onShow();
        }
    }

    public LudeiWebSettingsProxy getSettings() {
        return mSettings;
    }


    public void setHorizontalScrollbarOverlay(boolean overlay) {

    }

    public void setVerticalScrollbarOverlay(boolean overlay) {
    }

    public boolean overlayHorizontalScrollbar() {
        return false;
    }

    public boolean overlayVerticalScrollbar() {
        return false;
    }

    public void savePassword(String host, String username, String password) {
    }

    public void setHttpAuthUsernamePassword(String host, String realm,
                                            String username, String password) {
    }

    public String[] getHttpAuthUsernamePassword(String host, String realm) {
        return null;
    }

    public void destroy() {
        ContentViewCore contentView = this.getContentViewCore();
        if (contentView != null) {
            contentView.destroy();
        }
    }

    public static void enablePlatformNotifications() {
    }

    public static void disablePlatformNotifications() {
    }

    public void loadUrl(String url) {

        final LoadUrlParams params = new LoadUrlParams(url);
        params.setLoadType(LoadUrlParams.LOAD_TYPE_DEFAULT);

        this.postTask(new Runnable() {
            @Override
            public void run() {
            	getContentViewCore().loadUrl(params);
            }
        });

    }

    public void loadData(String data, String mimeType, String encoding) {

        final LoadUrlParams params = new LoadUrlParams(data);
        params.setLoadType(LoadUrlParams.LOAD_TYPE_DATA);
        this.postTask(new Runnable() {
            @Override
            public void run() {
            	getContentViewCore().loadUrl(params);
            }
        });
    }

    public void loadDataWithBaseURL(String baseUrl, String data,
                                    String mimeType, String encoding, String failUrl) {

        final LoadUrlParams params = new LoadUrlParams(data);
        params.setLoadType(LoadUrlParams.LOAD_TYPE_DATA);
        params.setBaseUrlForDataUrl(baseUrl);
        this.postTask(new Runnable() {
            @Override
            public void run() {
            	getContentViewCore().loadUrl(params);
            }
        });
    }

    public void stopLoading() {
        ContentViewCore contentView = this.getContentViewCore();
        if (contentView != null) {
            contentView.stopLoading();
        }
    }

    public void reload() {
        ContentViewCore contentView = this.getContentViewCore();
        if (contentView != null) {
            contentView.reload(true);
        }
    }

    public boolean canGoBack() {
        ContentViewCore contentView = this.getContentViewCore();
        return contentView != null ? contentView.canGoBack() : false;
    }

    public void goBack() {
        ContentViewCore contentView = this.getContentViewCore();
        if (contentView != null) {
            contentView.goBack();
        }
    }

    public boolean canGoForward() {
        ContentViewCore contentView = this.getContentViewCore();
        return contentView != null ? contentView.canGoForward() : false;
    }

    public void goForward() {
        ContentViewCore contentView = this.getContentViewCore();
        if (contentView != null) {
            contentView.goForward();
        }
    }

    public boolean canGoBackOrForward(int steps) {
        return this.canGoBack() || this.canGoForward();
    }

    public void goBackOrForward(int steps) {
        if (this.canGoBack()) {
            this.goBack();
        }
        else if (this.canGoForward()) {
            this.goForward();
        }
    }

    public boolean pageUp(boolean top) {
        return false;
    }

    public boolean pageDown(boolean bottom) {
        return false;
    }

    public void clearView() {

    }

    public Picture capturePicture() {
        return null;
    }

    public float getScale() {
        ContentViewCore contentView = this.getContentViewCore();
        return contentView != null ? contentView.getScale() : 0;
    }

    public void setInitialScale(int scaleInPercent) {

    }

    public void invokeZoomPicker() {
    }

    public void requestFocusNodeHref(Message hrefMsg) {

    }

    public void requestImageRef(Message msg) {
    }

    public String getUrl() {
        ContentViewCore contentView = this.getContentViewCore();
        return contentView != null ? contentView.getUrl() : "";
    }

    public String getTitle() {
        ContentViewCore contentView = this.getContentViewCore();
        return contentView != null ? contentView.getTitle() : "";
    }

    public Bitmap getFavicon() {
        return null;
    }

    public int getProgress() {
        return 0;
    }

    public int getContentHeight() {
        return 0;
    }

    public void pauseTimers() {
    }

    public void resumeTimers() {
    }

    public void clearCache() {
    }

    public void clearFormData() {
    }

    public void clearHistory() {
        ContentViewCore contentView = this.getContentViewCore();
        if (contentView != null) {
            contentView.clearHistory();
        }
    }

    public void clearSslPreferences() {
        ContentViewCore contentView = this.getContentViewCore();
        if (contentView != null) {
            contentView.clearSslPreferences();
        }
    }


    public void documentHasImages(Message response) {
    }

    public void setWebViewClient(WebViewClient client) {
        mBridge.setwebViewClient(client);
    }

    public void setDownloadListener(DownloadListener listener) {
        mBridge.setDownloadListener(listener);
    }

    public void setWebChromeClient(WebChromeClient client) {
        mBridge.setWebChromeClient(client);
    }

    public void addJavascriptInterface(Object obj, String interfaceName) {

        final Object object = obj;
        final String name = interfaceName;

        this.postTask(new Runnable() {
            @Override
            public void run() {
            	
            	Class<? extends Annotation> requiredAnnotation = null;
            	try {
            		requiredAnnotation = (Class<? extends Annotation>)Class.forName("android.webkit.JavascriptInterface");
				} catch (ClassNotFoundException e) {

				}
            	getContentViewCore().addPossiblyUnsafeJavascriptInterface(object, name, requiredAnnotation);
            }
        });
    }

    public View getZoomControls() {
        return null;
    }

    public boolean zoomIn() {
        ContentViewCore contentView = this.getContentViewCore();
        return contentView != null ? contentView.zoomIn() : false;
    }

    public boolean zoomOut() {
        ContentViewCore contentView = this.getContentViewCore();
        return contentView != null ? contentView.zoomOut() : false;
    }

    //Custom method added to trigger 'online' event (used in Cordova)
    public void setNetworkAvailable(boolean networkUp) {
        mSettings.getAwSettings().setNetworkAvailable(networkUp);
    }

    /*
     * Private Methods
     */
    private static final String COMMAND_LINE_FILE = "/data/local/tmp/content-shell-command-line";
    private static final String TAG = "LudeiChromium";
    private static final String ACTIVE_SHELL_URL_KEY = "activeUrl";
    private static final String[] MANDATORY_PAK_FILES = new String[] {"content_shell.pak"};
    private static final String PRIVATE_DATA_DIRECTORY_SUFFIX = "content_shell";

    private Context ctx;
    private LudeiWebSettingsProxy mSettings;
    private LudeiContentViewDelegate mDelegate;
    private LudeiContentsClientBridge mBridge;
    private WebView mProxyWebView;
    //tasks waiting for ContentView initialization
    private ArrayList<Runnable> pendingTasks = new ArrayList<Runnable>();
    private ShellManager shellManager;

    private void postTask(Runnable task) {
        ContentViewCore contentView = this.getContentViewCore();
        if (contentView != null) {
            task.run();
        }
        else {
            pendingTasks.add(task);
        }
    }

    public void onReadyToRender() {
        mDelegate.installWebContentsObserver(getContentViewCore());
        for (Runnable task: pendingTasks) {
            task.run();
        }
        pendingTasks.clear();
    }


    private static boolean staticInitialization = false;

    private void initialize(Context context) {

        Log.i("Ludei", "***************************");
        Log.i("Ludei", "Initializing Ludei WebView+");
        Log.i("Ludei", "**************************");

        if (!staticInitialization) {
            PathUtils.setPrivateDataDirectorySuffix(PRIVATE_DATA_DIRECTORY_SUFFIX);
            ResourceExtractor.setMandatoryPaksToExtract(MANDATORY_PAK_FILES);
            ResourceProvider.registerResources();
            staticInitialization = true;
        }

        this.ctx = context;
        if (mProxyWebView == null) {
            mProxyWebView = new LudeiWebView(context, null, 0, this);
        }
        mBridge = new LudeiContentsClientBridge(mProxyWebView, new LudeiDefaultWebChromeClient(this.ctx, this));
        mDelegate = new LudeiContentViewDelegate(this, mBridge);

        // Initializing the command line must occur before loading the library.
        if (!CommandLine.isInitialized()) {
            CommandLine.initFromFile(COMMAND_LINE_FILE);
            String[] commandLineParams = null;//getCommandLineParamsFromIntent(getIntent());

            if (commandLineParams != null) {
                CommandLine.getInstance().appendSwitchesAndArguments(commandLineParams);
            }
            CommandLine.getInstance().appendSwitch("allow-file-access-from-files");
            if (!LudeiContentView.REMOTE_DEBUGGING) {
                CommandLine.getInstance().appendSwitch("disable-remote-debugging");
            }
            CommandLine.getInstance().appendSwitch("ignore-gpu-blacklist");
        }
        waitForDebuggerIfNeeded();

        DeviceUtils.addDeviceSpecificUserAgentSwitch(context);
        try {
        	org.chromium.base.library_loader.LibraryLoader.ensureInitialized();
        } catch (ProcessInitException e) {
            Log.e(TAG, "ContentView initialization failed.", e);
            // Since the library failed to initialize nothing in the application
            // can work, so kill the whole application not just the activity
            System.exit(-1);
            return;
        }

        mSettings =  LudeiWebSettingsProxy.Create(context,(new LudeiWebSettings(context, true, false)));
        shellManager = new ShellManager(context, null, this, mDelegate);

        WindowAndroid mWindowAndroid = new ActivityWindowAndroid((Activity)ctx);
        //mWindowAndroid.restoreInstanceState(savedInstanceState);
        shellManager.setWindow(mWindowAndroid, new Runnable() {
            @Override
            public void run() {
                LudeiContentView.this.onReadyToRender();
            }
        });


        if (CommandLine.getInstance().hasSwitch(ContentSwitches.DUMP_RENDER_TREE)) {
            try {
                BrowserStartupController.get(context).startBrowserProcessesSync(
                        true);
            }
            catch (ProcessInitException e) {
                Log.e(TAG, "Failed to load native library.", e);
                System.exit(-1);
            }
        } else {
            try {
                BrowserStartupController.get(context).startBrowserProcessesAsync(
                        new BrowserStartupController.StartupCallback() {
                            @Override
                            public void onSuccess(boolean alreadyStarted) {
                                //finishInitialization(savedInstanceState);
                                finishInitialization(null);
                            }

                            @Override
                            public void onFailure() {
                                initializationFailed();
                            }
                        });
            }
            catch (ProcessInitException e) {
                Log.e(TAG, "Unable to load native library.", e);
                System.exit(-1);
            }
        }
    }


    private void finishInitialization(Bundle savedInstanceState) {
        postTask(new Runnable() {
            @Override
            public void run() {
            	getContentViewCore().setContentViewClient(new ContentViewClient() {
                    @Override
                    public ContentVideoViewClient getContentVideoViewClient() {
                        //return new ActivityContentVideoViewClient(LudeiChromiumActivity.this);
                        return new ActivityContentVideoViewClient((Activity)LudeiContentView.this.ctx);
                    }
                });
            }
        });

    }

    private void initializationFailed() {
        String message = "LudeiChromium initialization failed";
        Log.e(TAG, message);
        Toast.makeText(LudeiContentView.this.ctx, message, Toast.LENGTH_SHORT).show();
        //finish();
    }


    private void waitForDebuggerIfNeeded() {
        if (CommandLine.getInstance().hasSwitch(BaseSwitches.WAIT_FOR_JAVA_DEBUGGER)) {
            Log.e(TAG, "Waiting for Java debugger to connect...");
            android.os.Debug.waitForDebugger();
            Log.e(TAG, "Java debugger connected. Resuming execution.");
        }
    }


    private ContentViewCore getContentViewCore() {
        Shell shell = shellManager.getActiveShell();
        ContentView cv = shell != null ? shell.getContentView() : null;
        return cv != null ? cv.getContentViewCore() : null;
    }

    public Activity getActivity() {
        if (this.ctx instanceof Activity) {
            return (Activity)this.ctx;
        }
        return null;
    }
}
