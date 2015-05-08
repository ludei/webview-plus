/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: ../net/android/java/src/org/chromium/net/IRemoteAndroidKeyStore.aidl
 */
package org.chromium.net;
/**
 * Interface for communication with an Android KeyStore in another process.
 */
public interface IRemoteAndroidKeyStore extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.chromium.net.IRemoteAndroidKeyStore
{
private static final java.lang.String DESCRIPTOR = "org.chromium.net.IRemoteAndroidKeyStore";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.chromium.net.IRemoteAndroidKeyStore interface,
 * generating a proxy if needed.
 */
public static org.chromium.net.IRemoteAndroidKeyStore asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.chromium.net.IRemoteAndroidKeyStore))) {
return ((org.chromium.net.IRemoteAndroidKeyStore)iin);
}
return new org.chromium.net.IRemoteAndroidKeyStore.Stub.Proxy(obj);
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
case TRANSACTION_getClientCertificateAlias:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getClientCertificateAlias();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getEncodedCertificateChain:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
byte[] _result = this.getEncodedCertificateChain(_arg0);
reply.writeNoException();
reply.writeByteArray(_result);
return true;
}
case TRANSACTION_getPrivateKeyHandle:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _result = this.getPrivateKeyHandle(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_setClientCallbacks:
{
data.enforceInterface(DESCRIPTOR);
org.chromium.net.IRemoteAndroidKeyStoreCallbacks _arg0;
_arg0 = org.chromium.net.IRemoteAndroidKeyStoreCallbacks.Stub.asInterface(data.readStrongBinder());
this.setClientCallbacks(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getRSAKeyModulus:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
byte[] _result = this.getRSAKeyModulus(_arg0);
reply.writeNoException();
reply.writeByteArray(_result);
return true;
}
case TRANSACTION_getPrivateKeyEncodedBytes:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
byte[] _result = this.getPrivateKeyEncodedBytes(_arg0);
reply.writeNoException();
reply.writeByteArray(_result);
return true;
}
case TRANSACTION_getDSAKeyParamQ:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
byte[] _result = this.getDSAKeyParamQ(_arg0);
reply.writeNoException();
reply.writeByteArray(_result);
return true;
}
case TRANSACTION_getECKeyOrder:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
byte[] _result = this.getECKeyOrder(_arg0);
reply.writeNoException();
reply.writeByteArray(_result);
return true;
}
case TRANSACTION_rawSignDigestWithPrivateKey:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
byte[] _arg1;
_arg1 = data.createByteArray();
byte[] _result = this.rawSignDigestWithPrivateKey(_arg0, _arg1);
reply.writeNoException();
reply.writeByteArray(_result);
return true;
}
case TRANSACTION_getPrivateKeyType:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _result = this.getPrivateKeyType(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_releaseKey:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.releaseKey(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements org.chromium.net.IRemoteAndroidKeyStore
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
// Remote calls for SSlClientCertificateRequest - these allow retrieving
// the alias of the certificate to be used, its encoded chain and a handle
// for identifying a private key in the remote process.

@Override public java.lang.String getClientCertificateAlias() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getClientCertificateAlias, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public byte[] getEncodedCertificateChain(java.lang.String alias) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
byte[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(alias);
mRemote.transact(Stub.TRANSACTION_getEncodedCertificateChain, _data, _reply, 0);
_reply.readException();
_result = _reply.createByteArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getPrivateKeyHandle(java.lang.String alias) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(alias);
mRemote.transact(Stub.TRANSACTION_getPrivateKeyHandle, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
// Registers callbacks for service->client communication.

@Override public void setClientCallbacks(org.chromium.net.IRemoteAndroidKeyStoreCallbacks callbacks) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callbacks!=null))?(callbacks.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_setClientCallbacks, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
// Remote calls for AndroidKeyStore - these functions are performing operations
// with a PrivateKey in the remote process using the handle provided by
// |getPrivateKeyHandle|.

@Override public byte[] getRSAKeyModulus(int handle) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
byte[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(handle);
mRemote.transact(Stub.TRANSACTION_getRSAKeyModulus, _data, _reply, 0);
_reply.readException();
_result = _reply.createByteArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public byte[] getPrivateKeyEncodedBytes(int handle) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
byte[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(handle);
mRemote.transact(Stub.TRANSACTION_getPrivateKeyEncodedBytes, _data, _reply, 0);
_reply.readException();
_result = _reply.createByteArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public byte[] getDSAKeyParamQ(int handle) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
byte[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(handle);
mRemote.transact(Stub.TRANSACTION_getDSAKeyParamQ, _data, _reply, 0);
_reply.readException();
_result = _reply.createByteArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public byte[] getECKeyOrder(int handle) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
byte[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(handle);
mRemote.transact(Stub.TRANSACTION_getECKeyOrder, _data, _reply, 0);
_reply.readException();
_result = _reply.createByteArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public byte[] rawSignDigestWithPrivateKey(int handle, byte[] message) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
byte[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(handle);
_data.writeByteArray(message);
mRemote.transact(Stub.TRANSACTION_rawSignDigestWithPrivateKey, _data, _reply, 0);
_reply.readException();
_result = _reply.createByteArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getPrivateKeyType(int handle) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(handle);
mRemote.transact(Stub.TRANSACTION_getPrivateKeyType, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void releaseKey(int handle) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(handle);
mRemote.transact(Stub.TRANSACTION_releaseKey, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_getClientCertificateAlias = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getEncodedCertificateChain = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getPrivateKeyHandle = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_setClientCallbacks = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_getRSAKeyModulus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getPrivateKeyEncodedBytes = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getDSAKeyParamQ = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_getECKeyOrder = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_rawSignDigestWithPrivateKey = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_getPrivateKeyType = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_releaseKey = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
}
// Remote calls for SSlClientCertificateRequest - these allow retrieving
// the alias of the certificate to be used, its encoded chain and a handle
// for identifying a private key in the remote process.

public java.lang.String getClientCertificateAlias() throws android.os.RemoteException;
public byte[] getEncodedCertificateChain(java.lang.String alias) throws android.os.RemoteException;
public int getPrivateKeyHandle(java.lang.String alias) throws android.os.RemoteException;
// Registers callbacks for service->client communication.

public void setClientCallbacks(org.chromium.net.IRemoteAndroidKeyStoreCallbacks callbacks) throws android.os.RemoteException;
// Remote calls for AndroidKeyStore - these functions are performing operations
// with a PrivateKey in the remote process using the handle provided by
// |getPrivateKeyHandle|.

public byte[] getRSAKeyModulus(int handle) throws android.os.RemoteException;
public byte[] getPrivateKeyEncodedBytes(int handle) throws android.os.RemoteException;
public byte[] getDSAKeyParamQ(int handle) throws android.os.RemoteException;
public byte[] getECKeyOrder(int handle) throws android.os.RemoteException;
public byte[] rawSignDigestWithPrivateKey(int handle, byte[] message) throws android.os.RemoteException;
public int getPrivateKeyType(int handle) throws android.os.RemoteException;
public void releaseKey(int handle) throws android.os.RemoteException;
}
