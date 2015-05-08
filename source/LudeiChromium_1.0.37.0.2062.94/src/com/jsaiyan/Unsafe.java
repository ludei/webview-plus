/* 
 * JSaiyan is licensed under MIT licensed. See LICENSE.md file for more information.
 * Copyright (c) 2014 MortimerGoro
*/

package com.jsaiyan;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class Unsafe {


    /**
     * Offset of the superclass field in a Class Struck in Dalvik
     * Use findSuperClassOffset() if this constant value fails
     */
    private static long DALVIK_SUPER_CLASS_OFFSET = 72L;
    private RuntimeUnsafeWrapper wrapper;
    private Unsafe() throws Exception
    {
        System.loadLibrary("jsaiyan");
        try {
        	wrapper = new RuntimeUnsafeWrapper();
        
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    private static Unsafe sharedInstance;

    /**
     * Gets the shared instance
     * @return
     */
    public static Unsafe instance() {
        if (sharedInstance == null) {
            try {
                sharedInstance = new Unsafe();
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return sharedInstance;
    }

    /**
     * Allocates a Object without calling constructors
     * @param c
     * @return
     */
    public native Object allocateObject(Class<?> c);

    /**
     * Call non virtual constructor
     * @param instance
     * @param c
     * @param params
     */
    public void callConstructor(Object instance, Class<?> c, Object... params) throws NoSuchMethodException {
        callSuperMethod(instance, c, "<init>", params);
    }

    /**
     * Call super method
     * @param instance
     * @param superClass
     * @param methodName
     * @param params
     */
    public void callSuperMethod(Object instance, Class<?> superClass, String methodName, Object... params) throws NoSuchMethodException {
        Class<?>[] classes = new Class<?>[params.length];
        int i = 0;
        for (Object param: params) {
            classes[i++] = param.getClass();
        }
        Method method = superClass.getDeclaredMethod(methodName, classes);
        String signature = getSignature(method);
        nativeCallSuper(instance, superClass, methodName, signature, params);
    }

    /**
     * Change the class of a Object
     * @param instance
     * @param c
     */
    public void changeClass(Object instance, Class<?> c) {
        //get the class pointer of the object (see Dalvik Object.h)
        long fakeClassAddress = toAddress(c);
        putAddress(toAddress(instance), fakeClassAddress);
    }

    public void changeSuperClass(Class<?> target, Class<?> desiredSuperClass)
    {
        try {
            long targetClassAddress = toAddress(target);
            long desiredSuperClassAddress = toAddress(desiredSuperClass);
            putAddress(targetClassAddress + DALVIK_SUPER_CLASS_OFFSET, desiredSuperClassAddress);
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }


    /**
     * Throws checked exceptions but your code is not forced to catch it
     * @param exception
     */
    public native void throwException(java.lang.Throwable exception);

    /**
     * Allocate memory
     * @param size
     * @return
     */
    public native long malloc(long size);

    /**
     * Free memory
     * @param size
     * @return
     */
    public native void free(long size);

    /**
     * Copy memory memory
     * @param size
     * @return
     */
    public native void memcpy(long destination, long source, long size);

    /**
     * Fetches a native pointer from a given memory address
     * @param address
     * @return
     */
    public native long getAddress(long address);

    /**
     * Stores a native pointer into a given memory address
     * @param address
     * @param value
     */
    public native void putAddress(long address, long value);

    /**
     * Returns the shallow size of object
     * @param o
     * @return
     */
    public long sizeOf(Object o) {
    	//TODO: Perf improvement: find size offset in DalvikVM to avoid iterating Object fields
    	
        long max = 0;
        
        Class<?> c = o.getClass();
        while (c != Object.class) {
            for (Field field : c.getDeclaredFields()) {
            	if ((field.getModifiers() & Modifier.STATIC) != 0) {
            		//ignore static fields
            		continue;
            	}
                long fieldOffset = wrapper.objectFieldOffset(field);
                if (fieldOffset > max) {
                    max = fieldOffset;
                }
            }
            c = c.getSuperclass();
        }

        //sizeOf must be multiple of 8
        return ((max/8) + 1) * 8;
    }

    /**
     * For correct native address cast signed int to unsigned long, 
     * @param value
     * @return
     */
    public long normalize(int value) {
    	return value>=0 ? value : (~0L >>> 32) & value;
    }

    /**
     * Gets the address in memory on a Java Object
     * @param obj
     * @return
     */
    public long toAddress(Object obj) {
        Object[] array = new Object[] {obj};
        long offset = wrapper.arrayBaseOffset(Object[].class);
        return normalize(wrapper.getInt(array, offset));
    }

    /**
     * Gets object from its address in memory
     * @param address
     * @return
     */
    public Object fromAddress(long address) {
        Object[] array = new Object[] {null};
        long offset = wrapper.arrayBaseOffset(Object[].class);
        wrapper.putLong(array, offset, address);
        return array[0];
    }


    /**
     * get a shallow copy of a object
     * @param obj
     * @return
     */
    public Object shallowCopy(Object obj) {
        long size = sizeOf(obj);
        long source = toAddress(obj);
        long dest = malloc(size);
        memcpy(dest, source, size);
        return fromAddress(dest);
    }

    //private Utilities
    
    private native void nativeCallSuper(Object instance, Class<?> clazz, String methodName, String signature,  Object[] params);
    private String getSignature(Method method) {
        String result = "(";
        for (Class<?> clazz: method.getParameterTypes()) {
            result+= classToSignature(clazz);
        }

        result+=")";
        result+= classToSignature(method.getReturnType());
        return result;
    }

    private String classToSignature(Class<?> c) {
        if (c == null || c == void.class) {
            return "V";
        }
        else if (c.isPrimitive()) {
            if (c == char.class) {
                return "C";
            }
            else if (c == short.class) {
                return "S";
            }
            else if (c == byte.class) {
                return "B";
            }
            else if (c == int.class) {
                return "I";
            }
            else if (c == long.class) {
                return "L";
            }
            else if (c == float.class) {
                return "F";
            }
            else if (c == double.class) {
                return "D";
            }
            else if (c == boolean.class) {
                return "Z";
            }
            else {
                throw new RuntimeException("Unkown primitive type");
            }
        }

        else if (c.isArray()) {
            return "[" + classToSignature(c.getComponentType());
        }
        else {
            String result = "L";
            result += c.getCanonicalName().replace(".","/");
            result+= ";";
            return result;
        }
    }

    /**
     * Helper function to find super field Object in a Dalvik Class Struct.
     * @return
     */
    public long findSuperClassOffset() {
        class Helper {}
        class Helper2 extends Helper {}

        long superClassAddress = toAddress(Helper.class);

        long maxOffset = 200;
        for (long i = 8; i < maxOffset; i+=8) {
            //long value = getAddress(topClassAddress + i);
            long value = normalize(wrapper.getInt(Helper2.class, i));
            if (value == superClassAddress) {
                return i;
            }
        }
        return 0;
    }
}
