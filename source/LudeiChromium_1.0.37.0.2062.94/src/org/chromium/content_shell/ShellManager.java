// Copyright (c) 2012 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.chromium.content_shell;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ludei.chromium.LudeiContentViewDelegate;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.ContentView;
import org.chromium.content.browser.ContentViewRenderView;
import org.chromium.ui.base.WindowAndroid;

/**
 * Container and generator of ShellViews.
 */
@JNINamespace("content")
public class ShellManager extends FrameLayout {

    public static final String DEFAULT_SHELL_URL = "";
    private static boolean sStartup = true;
    private WindowAndroid mWindow;
    private Shell mActiveShell;
    private Context ctx;
    private ViewGroup container;
    private LudeiContentViewDelegate mDelegate;

    private String mStartupUrl = DEFAULT_SHELL_URL;

    // The target for all content rendering.
    private ContentViewRenderView mContentViewRenderView;

    /**
     * Constructor for inflating via XML.
     */
    public ShellManager(Context context, AttributeSet attrs, ViewGroup containerView, LudeiContentViewDelegate delegate) {
        super(context, attrs);
        this.container = containerView;
        this.ctx = context;
        this.mDelegate = delegate;
        nativeInit(this);
    }

    /**
     * @param window The window used to generate all shells.
     */
    public void setWindow(WindowAndroid window, final Runnable readyToRenderCallback) {
        assert window != null;
        mWindow = window;
        mContentViewRenderView = new ContentViewRenderView(getContext()) {
            @Override
            protected void onReadyToRender() {
                if (readyToRenderCallback != null) {
                    readyToRenderCallback.run();
                }

            }
        };
        mContentViewRenderView.onNativeLibraryLoaded(window);
    }

    /**
     * @return The window used to generate all shells.
     */
    public WindowAndroid getWindow() {
        return mWindow;
    }

    /**
     * Sets the startup URL for new shell windows.
     */
    public void setStartupUrl(String url) {
        mStartupUrl = url;
    }

    /**
     * @return The currently visible shell view or null if one is not showing.
     */
    public Shell getActiveShell() {
        return mActiveShell;
    }

    /**
     * Creates a new shell pointing to the specified URL.
     * @param url The URL the shell should load upon creation.
     */
    public void launchShell(String url) {
        ThreadUtils.assertOnUiThread();
        Shell previousShell = mActiveShell;
        nativeLaunchShell(url);
        if (previousShell != null) previousShell.close();
    }


    @SuppressWarnings("unused")
    @CalledByNative
    private Object createShell(long nativeShellPtr) {
        assert mContentViewRenderView != null;

        Shell shellView = new Shell(ctx, null, mDelegate);
        shellView.setWindow(mWindow);

        if (mActiveShell != null) removeShell(mActiveShell);

        shellView.setContentViewRenderView(mContentViewRenderView);
        container.addView(shellView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        mActiveShell = shellView;
        ContentView contentView = mActiveShell.getContentView();
        if (contentView != null) {
            mContentViewRenderView.setCurrentContentViewCore(contentView.getContentViewCore());
            contentView.getContentViewCore().onShow();
        }

        return shellView;
    }

    @SuppressWarnings("unused")
    @CalledByNative
    private void removeShell(Shell shellView) {
        if (shellView == mActiveShell) mActiveShell = null;
        ContentView contentView = shellView.getContentView();
        if (contentView != null) contentView.getContentViewCore().onHide();
        shellView.setContentViewRenderView(null);
        shellView.setWindow(null);
        container.removeView(shellView);
    }

    private static native void nativeInit(Object shellManagerInstance);
    private static native void nativeLaunchShell(String url);
}
