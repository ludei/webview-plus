package com.ludei.chromium;

/**
 * An instance of this class is passed as a parameter in various {@link WebChromeClient} action
 * notifications. The object is used as a handle onto the underlying JavaScript-originated request,
 * and provides a means for the client to indicate whether this action should proceed.
 */
public class LudeiJsResult {
    /**
     * Callback interface, implemented by the WebViewProvider implementation to receive
     * notifications when the JavaScript result represented by a JsResult instance has
     * @hide Only for use by WebViewProvider implementations
     */
    public interface ResultReceiver {
        public void onJsResultComplete(LudeiJsResult result);
    }
    // This is the caller of the prompt and is the object that is waiting.
    private final ResultReceiver mReceiver;
    // This is a basic result of a confirm or prompt dialog.
    private boolean mResult;

    /**
     * Handle the result if the user cancelled the dialog.
     */
    public final void cancel() {
        mResult = false;
        wakeUp();
    }

    /**
     * Handle a confirmation response from the user.
     */
    public final void confirm() {
        mResult = true;
        wakeUp();
    }

    /**
     * @hide Only for use by WebViewProvider implementations
     */
    public LudeiJsResult(ResultReceiver receiver) {
        mReceiver = receiver;
    }

    /**
     * @hide Only for use by WebViewProvider implementations
     */
    public final boolean getResult() {
        return mResult;
    }

    /* Notify the caller that the JsResult has completed */
    private final void wakeUp() {
        mReceiver.onJsResultComplete(this);
    }
}
