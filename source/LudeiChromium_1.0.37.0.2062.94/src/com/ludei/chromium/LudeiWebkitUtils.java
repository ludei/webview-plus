package com.ludei.chromium;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebView;


public class LudeiWebkitUtils {
	
	public static JsResult makeJsResult(WebView view, final LudeiJsResult ludeiResult) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
			return makeJsResultPreJB(view, ludeiResult);
		else
			return makeJsResultJB(ludeiResult);
	}
	
	static JsResult mResult = null;
	
	@TargetApi(14)
	@SuppressWarnings("unchecked")
	public static JsResult makeJsResultPreJB(final WebView view, final LudeiJsResult ludeiResult) {
		try {
			Class<?> callbackProxyClazz = ((Class<?>) Class.forName("android.webkit.CallbackProxy"));
			Constructor<?> callbackProxyConstructor = callbackProxyClazz.getDeclaredConstructor(new Class[]{Context.class, WebView.class});
			final Object callbackProxy = callbackProxyConstructor.newInstance(view.getContext(), view);
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						synchronized (callbackProxy) {
							callbackProxy.wait();
						}
						
						Field f = Class.forName("android.webkit.JsResult").getDeclaredField("mResult");
						f.setAccessible(true);
						boolean r = (Boolean)f.get(mResult);
						if (r) {
							ludeiResult.confirm();
							
						} else {
							ludeiResult.cancel();
						}
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}).start();
			
			Class<JsResult> jsResultClazz = ((Class<JsResult>) Class.forName("android.webkit.JsResult"));
			Constructor<JsResult> constructor = jsResultClazz.getDeclaredConstructor(new Class[] {callbackProxyClazz, boolean.class});
			constructor.setAccessible(true); 
			mResult = constructor.newInstance(callbackProxy, false);
			Method setReady = jsResultClazz.getDeclaredMethod("setReady");
			setReady.setAccessible(true);
			setReady.invoke(mResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mResult;
	}

	@TargetApi(16)
	@SuppressWarnings("unchecked")
	public static JsResult makeJsResultJB(final LudeiJsResult ludeiResult) {		
		JsResult result = null;
		try {
			Class<?> resultReceiverClazz = ((Class<?>) Class.forName("android.webkit.JsResult$ResultReceiver"));
			Object resultReceiverProxy = Proxy.newProxyInstance(resultReceiverClazz.getClassLoader(), new Class[]{resultReceiverClazz}, new InvocationHandler() {
				
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					Method m = Class.forName("android.webkit.JsResult").getMethod("getResult");
					m.setAccessible(true);
					boolean r = (Boolean)m.invoke(args[0]);
					if (r) {
						ludeiResult.confirm();
						
					} else {
						ludeiResult.cancel();
					}
					
					return null;
				}
			});
			
			Class<JsResult> jsResultClazz = ((Class<JsResult>) Class.forName("android.webkit.JsResult"));
			Constructor<JsResult> constructor = jsResultClazz.getDeclaredConstructor(new Class[] {resultReceiverClazz});
			constructor.setAccessible(true); 
			result = constructor.newInstance(resultReceiverProxy);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static JsPromptResult makeJsPromptResult(WebView view, final LudeiJsPromptResult ludeiResult) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
			return makeJsPromptResultPreJB(view, ludeiResult);
		else
			return makeJsPromptResultJB(ludeiResult);
	}
	
	static JsPromptResult mPromptResult = null;
	
	@SuppressWarnings("unchecked")
	public static JsPromptResult makeJsPromptResultPreJB(WebView view, final LudeiJsPromptResult ludeiResult) {
		try {			
			Class<?> callbackProxyClazz = ((Class<?>) Class.forName("android.webkit.CallbackProxy"));
			Constructor<?> callbackProxyConstructor = callbackProxyClazz.getDeclaredConstructor(new Class[]{Context.class, WebView.class});
			final Object callbackProxy = callbackProxyConstructor.newInstance(view.getContext(), view);
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						synchronized (callbackProxy) {
							callbackProxy.wait();
						}
						
						Field f = Class.forName("android.webkit.JsResult").getDeclaredField("mResult");
						f.setAccessible(true);
						boolean r = (Boolean)f.get(mPromptResult);
						if (r) {
							f = Class.forName("android.webkit.JsPromptResult").getDeclaredField("mStringResult");
							f.setAccessible(true);
							String s = (String)f.get(mPromptResult);
							ludeiResult.confirm(s);
							
						} else {
							ludeiResult.cancel();
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			
			Class<JsPromptResult> jsPromptResultClazz = ((Class<JsPromptResult>) Class.forName("android.webkit.JsPromptResult"));
			Constructor<JsPromptResult> constructor = jsPromptResultClazz.getDeclaredConstructor(new Class[] {callbackProxyClazz});
			constructor.setAccessible(true); 
			mPromptResult = constructor.newInstance(callbackProxy);
			Method setReady = Class.forName("android.webkit.JsResult").getDeclaredMethod("setReady");
			setReady.setAccessible(true);
			setReady.invoke(mPromptResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mPromptResult;
	}
	
	@SuppressWarnings("unchecked")
	public static JsPromptResult makeJsPromptResultJB(final LudeiJsPromptResult ludeiResult) {
		JsPromptResult result = null;
		try {
			Class<?> resultReceiverClazz = ((Class<?>) Class.forName("android.webkit.JsResult$ResultReceiver"));
			Object resultReceiverProxy = Proxy.newProxyInstance(resultReceiverClazz.getClassLoader(), new Class[]{resultReceiverClazz}, new InvocationHandler() {
				
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					Method m = Class.forName("android.webkit.JsPromptResult").getMethod("getResult");
					m.setAccessible(true);
					boolean r = (Boolean)m.invoke(args[0]);
					if (r) {
						m = Class.forName("android.webkit.JsPromptResult").getMethod("getStringResult");
						m.setAccessible(true);
						String s = (String)m.invoke(args[0]);
						ludeiResult.confirm(s);
						
					} else {
						ludeiResult.cancel();
					}
					
					return null;
				}
			});
			
			Class<JsPromptResult> jsPromptResultClazz = ((Class<JsPromptResult>) Class.forName("android.webkit.JsPromptResult"));
			Constructor<JsPromptResult> constructor = jsPromptResultClazz.getDeclaredConstructor(new Class[] {resultReceiverClazz});
			constructor.setAccessible(true); 
			result = constructor.newInstance(resultReceiverProxy);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
