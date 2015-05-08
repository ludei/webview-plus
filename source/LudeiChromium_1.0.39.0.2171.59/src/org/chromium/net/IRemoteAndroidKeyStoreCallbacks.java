/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: ../net/android/java/src/org/chromium/net/IRemoteAndroidKeyStoreCallbacks.aidl
 */
package org.chromium.net;
/**
 * Interface for communication from the remote authentication service back to the client.
 */
public interface IRemoteAndroidKeyStoreCallbacks extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.chromium.net.IRemoteAndroidKeyStoreCallbacks
{
private static final java.lang.String DESCRIPTOR = "org.chromium.net.IRemoteAndroidKeyStoreCallbacks";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.chromium.net.IRemoteAndroidKeyStoreCallbacks interface,
 * generating a proxy if needed.
 */
public static org.chromium.net.IRemoteAndroidKeyStoreCallbacks asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.chromium.net.IRemoteAndroidKeyStoreCallbacks))) {
return ((org.chromium.net.IRemoteAndroidKeyStoreCallbacks)iin);
}
return new org.chromium.net.IRemoteAndroidKeyStoreCallbacks.Stub.Proxy(obj);
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
case TRANSACTION_onDisabled:
{
data.enforceInterface(DESCRIPTOR);
this.onDisabled();
reply.writeNoException();
return true;
}
case TRANSACTION_onInitComplete:
{
data.enforceInterface(DESCRIPTOR);
this.onInitComplete();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements org.chromium.net.IRemoteAndroidKeyStoreCallbacks
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
      * A critical failure has occurred and the service won't be able to recover.
      * The client should unbind and optionally rebind at a later time.
      */
@Override public void onDisabled() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onDisabled, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
     * The service has started up and is fully initialized. This allows for the
     * service to take some time to initialize. Remote calls shouldn't be invoked
     * until this call has fired.
     */
@Override public void onInitComplete() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_onInitComplete, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onDisabled = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_onInitComplete = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
/**
      * A critical failure has occurred and the service won't be able to recover.
      * The client should unbind and optionally rebind at a later time.
      */
public void onDisabled() throws android.os.RemoteException;
/**
     * The service has started up and is fully initialized. This allows for the
     * service to take some time to initialize. Remote calls shouldn't be invoked
     * until this call has fired.
     */
public void onInitComplete() throws android.os.RemoteException;
}
