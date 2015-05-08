package com.ludei.chromium;

public class LudeiJsPromptResult extends LudeiJsResult {
    // String result of the prompt
    private String mStringResult;

    /**
     * Handle a confirmation response from the user.
     */
    public void confirm(String result) {
        mStringResult = result;
        confirm();
    }

    /**
     * @hide Only for use by WebViewProvider implementations
     */
    public LudeiJsPromptResult(ResultReceiver receiver) {
        super(receiver);
    }

    /**
     * @hide Only for use by WebViewProvider implementations
     */
    public String getStringResult() {
        return mStringResult;
    }
}
