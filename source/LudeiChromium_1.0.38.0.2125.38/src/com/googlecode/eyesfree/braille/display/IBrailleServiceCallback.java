/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: src/android/java/src/com/googlecode/eyesfree/braille/display/IBrailleServiceCallback.aidl
 */
package com.googlecode.eyesfree.braille.display;
/**
 * Callback interface that a braille display client can expose to
 * get information about various braille display events.
 */
public interface IBrailleServiceCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.googlecode.eyesfree.braille.display.IBrailleServiceCallback
{
private static final java.lang.String DESCRIPTOR = "com.googlecode.eyesfree.braille.display.IBrailleServiceCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.googlecode.eyesfree.braille.display.IBrailleServiceCallback interface,
 * generating a proxy if needed.
 */
public static com.googlecode.eyesfree.braille.display.IBrailleServiceCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.googlecode.eyesfree.braille.display.IBrailleServiceCallback))) {
return ((com.googlecode.eyesfree.braille.display.IBrailleServiceCallback)iin);
}
return new com.googlecode.eyesfree.braille.display.IBrailleServiceCallback.Stub.Proxy(obj);
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
case TRANSACTION_onDisplayConnected:
{
data.enforceInterface(DESCRIPTOR);
com.googlecode.eyesfree.braille.display.BrailleDisplayProperties _arg0;
if ((0!=data.readInt())) {
_arg0 = com.googlecode.eyesfree.braille.display.BrailleDisplayProperties.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onDisplayConnected(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_onDisplayDisconnected:
{
data.enforceInterface(DESCRIPTOR);
this.onDisplayDisconnected();
reply.writeNoException();
return true;
}
case TRANSACTION_onInput:
{
data.enforceInterface(DESCRIPTOR);
com.googlecode.eyesfree.braille.display.BrailleInputEvent _arg0;
if ((0!=data.readInt())) {
_arg0 = com.googlecode.eyesfree.braille.display.BrailleInputEvent.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onInput(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.googlecode.eyesfree.braille.display.IBrailleServiceCallback
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
@Override public void onDisplayConnected(com.googlecode.eyesfree.braille.display.BrailleDisplayProperties displayProperties) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((displayProperties!=null)) {
_data.writeInt(1);
displayProperties.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onDisplayConnected, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void onDisplayDisconnected() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onDisplayDisconnected, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void onInput(com.googlecode.eyesfree.braille.display.BrailleInputEvent inputEvent) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((inputEvent!=null)) {
_data.writeInt(1);
inputEvent.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onInput, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onDisplayConnected = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onDisplayDisconnected = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_onInput = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public void onDisplayConnected(com.googlecode.eyesfree.braille.display.BrailleDisplayProperties displayProperties) throws android.os.RemoteException;
public void onDisplayDisconnected() throws android.os.RemoteException;
public void onInput(com.googlecode.eyesfree.braille.display.BrailleInputEvent inputEvent) throws android.os.RemoteException;
}
