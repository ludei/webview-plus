package com.ludei.chromium;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.*;
import android.widget.EditText;
import android.widget.FrameLayout;


public class LudeiDefaultWebChromeClient {

    // Strings for displaying Dialog.
    private static String mJSAlertTitle;
    private static String mJSConfirmTitle;
    private static String mJSPromptTitle;
    private static String mOKButton;
    private static String mCancelButton;

    private Context mContext;
    private AlertDialog mDialog;
    private EditText mPromptText;
    private View mCustomView;
    private LudeiContentView mView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    private boolean mOriginalFullscreen;
    private boolean mIsFullscreen = false;

    public LudeiDefaultWebChromeClient(Context context, LudeiContentView view) {
        mContext = context;
        mView = view;
        initResources(context);
    }

    private static void initResources(Context context) {
        if (mJSAlertTitle != null) return;
        mJSAlertTitle =  "Alert"; //context.getString(R.string.js_alert_title);
        mJSConfirmTitle = "Confirm"; //context.getString(R.string.js_confirm_title);
        mJSPromptTitle = "Prompt"; //context.getString(R.string.js_prompt_title);
        mOKButton = context.getString(android.R.string.ok);
        mCancelButton = context.getString(android.R.string.cancel);
    }

    public boolean onJsAlert(WebView view, String url, String message, final LudeiJsResult result) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        dialogBuilder.setTitle(mJSAlertTitle)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(mOKButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                        dialog.dismiss();
                    }
                });
        mDialog = dialogBuilder.create();
        mDialog.show();
        return false;
    }

    public boolean onJsConfirm(WebView view, String url, String message, final LudeiJsResult result) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        dialogBuilder.setTitle(mJSConfirmTitle)
                .setMessage(message)
                .setPositiveButton(mOKButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(mCancelButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.cancel();
                        dialog.dismiss();
                    }
                });
        mDialog = dialogBuilder.create();
        mDialog.show();
        return false;
    }

    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final LudeiJsPromptResult result) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        dialogBuilder.setTitle(mJSPromptTitle)
                .setMessage(message)
                .setPositiveButton(mOKButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm(mPromptText.getText().toString());
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(mCancelButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.cancel();
                        dialog.dismiss();
                    }
                });
        mPromptText = new EditText(mContext);
        mPromptText.setVisibility(View.VISIBLE);
        mPromptText.setText(defaultValue);
        mPromptText.selectAll();

        dialogBuilder.setView(mPromptText);
        mDialog = dialogBuilder.create();
        mDialog.show();
        return false;
    }

    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        Activity activity = mView.getActivity();

        if (mCustomView != null || activity == null) {
            callback.onCustomViewHidden();
            return;
        }

        mCustomView = view;
        mCustomViewCallback = callback;

        onToggleFullscreen(true);

        // Add the video view to the activity's ContentView.
        activity.getWindow().addContentView(view,
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        Gravity.CENTER));
    }

    public void onHideCustomView() {
        Activity activity = mView.getActivity();

        if (mCustomView == null || activity == null) return;

        onToggleFullscreen(false);

        // Remove video view from activity's ContentView.
        FrameLayout decor = (FrameLayout) activity.getWindow().getDecorView();
        decor.removeView(mCustomView);
        mCustomViewCallback.onCustomViewHidden();

        mCustomView = null;
        mCustomViewCallback = null;
    }

    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        callback.invoke(origin, true, true);
    }

    public void onToggleFullscreen(boolean enterFullscreen) {
        Activity activity = mView.getActivity();
        if (enterFullscreen) {
            if ((activity.getWindow().getAttributes().flags &
                    WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0) {
                mOriginalFullscreen = true;
            } else {
                mOriginalFullscreen = false;
            }
            if (!mOriginalFullscreen) {
                activity.getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        } else {
            // Clear the activity fullscreen flag.
            if (!mOriginalFullscreen) {
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        }
        mIsFullscreen = enterFullscreen;
    }

    public boolean isFullscreen() {
        return mIsFullscreen;
    }
}
