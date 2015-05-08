package com.ludei.chromium;

import org.chromium.base.ThreadUtils;

class LudeiJsResultHandler implements LudeiJsResult.ResultReceiver{
    private LudeiContentsClientBridge mBridge;
    private final int mId;

    LudeiJsResultHandler(LudeiContentsClientBridge bridge, int id) {
        mBridge = bridge;
        mId = id;
    }

    public void onJsResultComplete(final LudeiJsResult result) {
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (mBridge == null) {
                    return;
                }

                if (result.getResult()) {
                    String promptResult = null;
                    if (result instanceof LudeiJsPromptResult) {
                        promptResult = ((LudeiJsPromptResult)result).getStringResult();
                    }

                    mBridge.confirmJsResult(mId, promptResult);
                }
                else {
                    mBridge.cancelJsResult(mId);
                }
                mBridge = null;
            }
        });
    }

    public static LudeiJsResult createJsResult(LudeiContentsClientBridge bridge, int id) {
        return new LudeiJsResult(new LudeiJsResultHandler(bridge, id));
    }

    public static LudeiJsPromptResult createJsPromptResult(LudeiContentsClientBridge bridge, int id) {
        return new LudeiJsPromptResult(new LudeiJsResultHandler(bridge, id));
    }
}
