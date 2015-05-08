/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: src/android/java/src/com/googlecode/eyesfree/braille/display/IBrailleService.aidl
 */
package com.googlecode.eyesfree.braille.display;
/**
 * Interface for clients to talk to the braille display service.
 */
public interface IBrailleService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.googlecode.eyesfree.braille.display.IBrailleService
{
private static final java.lang.String DESCRIPTOR = "com.googlecode.eyesfree.braille.display.IBrailleService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.googlecode.eyesfree.braille.display.IBrailleService interface,
 * generating a proxy if needed.
 */
public static com.googlecode.eyesfree.braille.display.IBrailleService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.googlecode.eyesfree.braille.display.IBrailleService))) {
return ((com.googlecode.eyesfree.braille.display.IBrailleService)iin);
}
return new com.googlecode.eyesfree.braille.display.IBrailleService.Stub.Proxy(obj);
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
case TRANSACTION_registerCallback:
{
data.enforceInterface(DESCRIPTOR);
com.googlecode.eyesfree.braille.display.IBrailleServiceCallback _arg0;
_arg0 = com.googlecode.eyesfree.braille.display.IBrailleServiceCallback.Stub.asInterface(data.readStrongBinder());
boolean _result = this.registerCallback(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_unregisterCallback:
{
data.enforceInterface(DESCRIPTOR);
com.googlecode.eyesfree.braille.display.IBrailleServiceCallback _arg0;
_arg0 = com.googlecode.eyesfree.braille.display.IBrailleServiceCallback.Stub.asInterface(data.readStrongBinder());
this.unregisterCallback(_arg0);
return true;
}
case TRANSACTION_displayDots:
{
data.enforceInterface(DESCRIPTOR);
byte[] _arg0;
_arg0 = data.createByteArray();
this.displayDots(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.googlecode.eyesfree.braille.display.IBrailleService
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
/**
     * Register a callback for the {@code callingApp} which will receive
     * certain braille display related events.
     */
@Override public boolean registerCallback(com.googlecode.eyesfree.braille.display.IBrailleServiceCallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerCallback, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * Unregister a previously registered callback for the {@code callingApp}.
     */
@Override public void unregisterCallback(com.googlecode.eyesfree.braille.display.IBrailleServiceCallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterCallback, _data, null, android.os.IBinder.FLAG_ONEWAY);
}
finally {
_data.recycle();
}
}
/**
     * Updates the main cells of the connected braille display
     * with a given dot {@code pattern}.
     *
     * @return {@code true} on success and {@code false} otherwise.
     */
@Override public void displayDots(byte[] patterns) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeByteArray(patterns);
mRemote.transact(Stub.TRANSACTION_displayDots, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_registerCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_unregisterCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_displayDots = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
/**
     * Register a callback for the {@code callingApp} which will receive
     * certain braille display related events.
     */
public boolean registerCallback(com.googlecode.eyesfree.braille.display.IBrailleServiceCallback callback) throws android.os.RemoteException;
/**
     * Unregister a previously registered callback for the {@code callingApp}.
     */
public void unregisterCallback(com.googlecode.eyesfree.braille.display.IBrailleServiceCallback callback) throws android.os.RemoteException;
/**
     * Updates the main cells of the connected braille display
     * with a given dot {@code pattern}.
     *
     * @return {@code true} on success and {@code false} otherwise.
     */
public void displayDots(byte[] patterns) throws android.os.RemoteException;
}
