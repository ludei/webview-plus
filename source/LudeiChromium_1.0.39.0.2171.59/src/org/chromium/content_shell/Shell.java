// Copyright (c) 2012 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.chromium.content_shell;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.ludei.chromium.LudeiContentViewDelegate;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.content.browser.ContentView;
import org.chromium.content.browser.ContentViewCore;
import org.chromium.content.browser.ContentViewRenderView;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.base.WindowAndroid;

/**
 * Container for the various UI components that make up a shell window.
 */
@JNINamespace("content")
public class Shell extends FrameLayout {

    private static final long COMPLETED_PROGRESS_TIMEOUT_MS = 200;

    // TODO(jrg): a mContentView.destroy() call is needed, both upstream and downstream.
    private ContentView mContentView;
    private ContentViewRenderView mContentViewRenderView;
    private WindowAndroid mWindow;
    private LudeiContentViewDelegate mDelegate;
    private long mNativeShell;

    private boolean mLoading = false;

    /**
     * Constructor for inflating via XML.
     */
    public Shell(Context context, AttributeSet attrs, LudeiContentViewDelegate delegate) {
        super(context, attrs);
        mDelegate = delegate;
    }

    /**
     * Set the SurfaceView being renderered to as soon as it is available.
     */
    public void setContentViewRenderView(ContentViewRenderView contentViewRenderView) {
        FrameLayout contentViewHolder = (FrameLayout) this;
        if (contentViewRenderView == null) {
            if (mContentViewRenderView != null) {
                contentViewHolder.removeView(mContentViewRenderView);
            }
        } else {
            contentViewHolder.addView(contentViewRenderView,
                    new FrameLayout.LayoutParams(
                            FrameLayout.LayoutParams.MATCH_PARENT,
                            FrameLayout.LayoutParams.MATCH_PARENT));
        }
        mContentViewRenderView = contentViewRenderView;
    }

    /**
     * @param window The owning window for this shell.
     */
    public void setWindow(WindowAndroid window) {
        mWindow = window;
    }
    
    /**
     * Closes the shell and cleans up the native instance, which will handle destroying all
     * dependencies.
     */
    public void close() {
        if (mNativeShell == 0) return;
        nativeCloseShell(mNativeShell);
    }
    
    @CalledByNative
    private void onNativeDestroyed() {
        mWindow = null;
        mNativeShell = 0;
        mContentView.getContentViewCore().destroy();
    }

    /**
     * @return Whether the Shell has been destroyed.
     * @see #onNativeDestroyed()
     */
    public boolean isDestroyed() {
        return mNativeShell == 0;
    }

    /**
     * @return Whether or not the Shell is loading content.
     */
    public boolean isLoading() {
        return mLoading;
    }


    /**
     * Loads an URL.  This will perform minimal amounts of sanitizing of the URL to attempt to
     * make it valid.
     *
     * @param url The URL to be loaded by the shell.
     */
    public void loadUrl(String url) {
        if (url == null) return;
        if (TextUtils.equals(url, mContentView.getContentViewCore().getWebContents().getUrl())) {
            mContentView.getContentViewCore().getWebContents().getNavigationController().reload(true);
        } else {
            mContentView.getContentViewCore().getWebContents().getNavigationController().loadUrl(new LoadUrlParams(sanitizeUrl(url)));
        }
    }

    /**
     * Given an URL, this performs minimal sanitizing to ensure it will be valid.
     * @param url The url to be sanitized.
     * @return The sanitized URL.
     */
    public static String sanitizeUrl(String url) {
        if (url == null) return url;
        if (url.startsWith("www.") || url.indexOf(":") == -1) url = "http://" + url;
        return url;
    }

    @SuppressWarnings("unused")
    @CalledByNative
    private void onUpdateUrl(String url) {

    }

    @SuppressWarnings("unused")
    @CalledByNative
    private void onLoadProgressChanged(double progress) {
        mDelegate.onProgressChanged(progress);

    }

    @CalledByNative
    private void toggleFullscreenModeForTab(boolean enterFullscreen) {
    }

    @CalledByNative
    private boolean isFullscreenForTabOrPending() {
        return false;
    }

    @SuppressWarnings("unused")
    @CalledByNative
    private void setIsLoading(boolean loading) {
        mLoading = loading;
    }

    /**
     * Initializes the ContentView based on the native tab contents pointer passed in.
     * @param nativeTabContents The pointer to the native tab contents object.
     */
    @SuppressWarnings("unused")
    @CalledByNative
    private void initFromNativeTabContents(long nativeTabContents) {
    	
        
        Context context = getContext();
        ContentViewCore mContentViewCore = new ContentViewCore(context);
        mContentView = ContentView.newInstance(context, mContentViewCore);
        mContentViewCore.initialize(mContentView, mContentView, nativeTabContents, mWindow);
        //mContentViewCore.setContentViewClient(mContentViewClient);
        mDelegate.setNativeContents(nativeTabContents);

        this.addView(mContentView,
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT));
        mContentView.requestFocus();
        mContentViewRenderView.onNativeLibraryLoaded(mWindow);
        mContentViewRenderView.setCurrentContentViewCore(mContentViewCore);
    }
    
    @CalledByNative
    private void enableUiControl(int controlId, boolean enabled) {

    }
    
    private static native void nativeCloseShell(long shellPtr);

    @CalledByNative
    private Object getLudeiDelegate() {
        return mDelegate;
    }

    @CalledByNative
    private Object getLudeiContentsClientBridge() { return mDelegate.getContentsClientBridge();}

    /**
     * @return The {@link ContentView} currently shown by this Shell.
     */
    public ContentView getContentView() {
        return mContentView;
    }
}
