package com.ludei.chromium;

import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("ludei")
public class LudeiHttpAuthHandler {

    private int mNativeXWalkHttpAuthHandler;
    private final boolean mFirstAttempt;

    public void proceed(String username, String password) {
        if (mNativeXWalkHttpAuthHandler != 0) {
            nativeProceed(mNativeXWalkHttpAuthHandler, username, password);
            mNativeXWalkHttpAuthHandler = 0;
        }
    }

    public void cancel() {
        if (mNativeXWalkHttpAuthHandler != 0) {
            nativeCancel(mNativeXWalkHttpAuthHandler);
            mNativeXWalkHttpAuthHandler = 0;
        }
    }

    public boolean isFirstAttempt() {
        return mFirstAttempt;
    }

    @CalledByNative
    public static LudeiHttpAuthHandler create(int nativeXWalkAuthHandler, boolean firstAttempt) {
        return new LudeiHttpAuthHandler(nativeXWalkAuthHandler, firstAttempt);
    }

    private LudeiHttpAuthHandler(int nativeXWalkHttpAuthHandler, boolean firstAttempt) {
        mNativeXWalkHttpAuthHandler = nativeXWalkHttpAuthHandler;
        mFirstAttempt = firstAttempt;
    }

    @CalledByNative
    void handlerDestroyed() {
        mNativeXWalkHttpAuthHandler = 0;
    }

    private native void nativeProceed(int nativeXWalkHttpAuthHandler,
                                      String username, String password);
    private native void nativeCancel(int nativeXWalkHttpAuthHandler);
}

