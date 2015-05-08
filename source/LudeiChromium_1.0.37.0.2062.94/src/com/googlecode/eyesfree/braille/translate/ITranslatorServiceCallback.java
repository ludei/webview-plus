/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: src/android/java/src/com/googlecode/eyesfree/braille/translate/ITranslatorServiceCallback.aidl
 */
package com.googlecode.eyesfree.braille.translate;
public interface ITranslatorServiceCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.googlecode.eyesfree.braille.translate.ITranslatorServiceCallback
{
private static final java.lang.String DESCRIPTOR = "com.googlecode.eyesfree.braille.translate.ITranslatorServiceCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.googlecode.eyesfree.braille.translate.ITranslatorServiceCallback interface,
 * generating a proxy if needed.
 */
public static com.googlecode.eyesfree.braille.translate.ITranslatorServiceCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.googlecode.eyesfree.braille.translate.ITranslatorServiceCallback))) {
return ((com.googlecode.eyesfree.braille.translate.ITranslatorServiceCallback)iin);
}
return new com.googlecode.eyesfree.braille.translate.ITranslatorServiceCallback.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_onInit:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.onInit(_arg0);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.googlecode.eyesfree.braille.translate.ITranslatorServiceCallback
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void onInit(int status) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(status);
mRemote.transact(Stub.TRANSACTION_onInit, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
}
static final int TRANSACTION_onInit = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void onInit(int status) throws android.os.RemoteException;
}
