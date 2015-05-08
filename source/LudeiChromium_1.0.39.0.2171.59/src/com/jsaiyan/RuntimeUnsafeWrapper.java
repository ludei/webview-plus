/* 
 * JSaiyan is licensed under MIT licensed. See LICENSE.md file for more information.
 * Copyright (c) 2014 MortimerGoro
*/

package com.jsaiyan;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RuntimeUnsafeWrapper {


    private Object unsafe;

    private Method _objectFieldOffset;
    private Method _arrayBaseOffset;
    private Method _arrayIndexScale;
    private Method _compareAndSwapInt;
    private Method _compareAndSwapLong;
    private Method _compareAndSwapObject;
    private Method _getIntVolatile;
    private Method _getLongVolatile;
    private Method _getObjectVolatile;
    private Method _putIntVolatile;
    private Method _putLongVolatile;
    private Method _putObjectVolatile;
    private Method _getInt;
    private Method _getLong;
    private Method _getObject;
    private Method _putInt;
    private Method _putLong;
    private Method _putObject;
    private Method _putOrderedInt;
    private Method _putOrderedLong;
    private Method _putOrderedObject;
    private Method _park;
    private Method _unpark;
    private Method _allocateInstance;


    public RuntimeUnsafeWrapper() throws Exception {
        Class<?> c = Class.forName("sun.misc.Unsafe");
        Field f = c.getDeclaredField("theUnsafe"); //Internal reference
        f.setAccessible(true);
        unsafe = (Object) f.get(null);

        _objectFieldOffset = c.getDeclaredMethod("objectFieldOffset", Field.class);
        _arrayBaseOffset = c.getDeclaredMethod("arrayBaseOffset", Class.class);
        _arrayIndexScale = c.getDeclaredMethod("arrayIndexScale", Class.class);
        _compareAndSwapInt = c.getDeclaredMethod("compareAndSwapInt", Object.class, long.class, int.class, int.class);
        _compareAndSwapLong = c.getDeclaredMethod("compareAndSwapLong", Object.class, long.class, long.class, long.class);
        _compareAndSwapObject = c.getDeclaredMethod("compareAndSwapObject", Object.class, long.class, Object.class, Object.class);
        _getIntVolatile = c.getDeclaredMethod("getIntVolatile", Object.class, long.class);
        _getLongVolatile = c.getDeclaredMethod("getLongVolatile", Object.class, long.class);
        _getObjectVolatile = c.getDeclaredMethod("getObjectVolatile", Object.class, long.class);
        _putIntVolatile = c.getDeclaredMethod("putIntVolatile", Object.class, long.class, int.class);
        _putLongVolatile = c.getDeclaredMethod("putLongVolatile", Object.class, long.class, long.class);
        _putObjectVolatile = c.getDeclaredMethod("putObjectVolatile", Object.class, long.class, Object.class);
        _getInt = c.getDeclaredMethod("getInt", Object.class, long.class);
        _getLong = c.getDeclaredMethod("getLong", Object.class, long.class);
        _getObject = c.getDeclaredMethod("getObject", Object.class, long.class);
        _putInt = c.getDeclaredMethod("putInt", Object.class, long.class, int.class);
        _putLong = c.getDeclaredMethod("putLong", Object.class, long.class, long.class);
        _putObject = c.getDeclaredMethod("putObject", Object.class, long.class, Object.class);
        _putOrderedInt = c.getDeclaredMethod("putOrderedInt", Object.class, long.class, int.class);
        _putOrderedLong = c.getDeclaredMethod("putOrderedLong", Object.class, long.class, long.class);
        _putOrderedObject = c.getDeclaredMethod("putOrderedObject", Object.class, long.class, Object.class);
        _park = c.getDeclaredMethod("park", boolean.class, long.class);
        _unpark = c.getDeclaredMethod("unpark", Object.class);
        _allocateInstance = c.getDeclaredMethod("allocateInstance", Class.class);
    }

    /**
     * Gets the raw byte offset from the start of an object's memory to
     * the memory used to store the indicated instance field.
     *
     * @param field non-null; the field in question, which must be an
     * instance field
     * @return the offset to the field
     */
    public long objectFieldOffset(Field field) {
        try {
            return (Long)_objectFieldOffset.invoke(unsafe, field);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Gets the offset from the start of an array object's memory to
     * the memory used to store its initial (zeroeth) element.
     *
     * @param clazz non-null; class in question; must be an array class
     * @return the offset to the initial element
     */
    public int arrayBaseOffset(Class<?> clazz) {
        try {
            return (Integer)_arrayBaseOffset.invoke(unsafe, clazz);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Gets the size of each element of the given array class.
     *
     * @param clazz non-null; class in question; must be an array class
     * @return &gt; 0; the size of each element of the array
     */
    public int arrayIndexScale(Class<?> clazz) {
        try {
            return (Integer)_arrayIndexScale.invoke(unsafe, clazz);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Performs a compare-and-set operation on an <code>int</code>
     * field within the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @param expectedValue expected value of the field
     * @param newValue new value to store in the field if the contents are
     * as expected
     * @return <code>true</code> if the new value was in fact stored, and
     * <code>false</code> if not
     */
    public boolean compareAndSwapInt(Object obj, long offset,
                                            int expectedValue, int newValue)
    {
        try {
            return (Boolean)_compareAndSwapInt.invoke(unsafe, obj, offset, expectedValue, newValue);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
    /**
     * Performs a compare-and-set operation on a <code>long</code>
     * field within the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @param expectedValue expected value of the field
     * @param newValue new value to store in the field if the contents are
     * as expected
     * @return <code>true</code> if the new value was in fact stored, and
     * <code>false</code> if not
     */
    public boolean compareAndSwapLong(Object obj, long offset,
                                             long expectedValue, long newValue)
    {
        try {
            return (Boolean)_compareAndSwapLong.invoke(unsafe, obj, offset, expectedValue, newValue);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Performs a compare-and-set operation on an <code>Object</code>
     * field (that is, a reference field) within the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @param expectedValue expected value of the field
     * @param newValue new value to store in the field if the contents are
     * as expected
     * @return <code>true</code> if the new value was in fact stored, and
     * <code>false</code> if not
     */
    public boolean compareAndSwapObject(Object obj, long offset,
                                               Object expectedValue, Object newValue)
    {
        try {
            return (Boolean)_compareAndSwapObject.invoke(unsafe, obj, offset, expectedValue, newValue);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Gets an <code>int</code> field from the given object,
     * using <code>volatile</code> semantics.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @return the retrieved value
     */
    public int getIntVolatile(Object obj, long offset)
    {
        try {
            return (Integer)_getIntVolatile.invoke(unsafe, obj, offset);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Stores an <code>int</code> field into the given object,
     * using <code>volatile</code> semantics.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @param newValue the value to store
     */
    public void putIntVolatile(Object obj, long offset, int newValue)
    {
        try {
            _putIntVolatile.invoke(unsafe, obj, offset, newValue);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Gets a <code>long</code> field from the given object,
     * using <code>volatile</code> semantics.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @return the retrieved value
     */
    public long getLongVolatile(Object obj, long offset)
    {
        try {
            return (Long)_getLongVolatile.invoke(unsafe, obj, offset);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Stores a <code>long</code> field into the given object,
     * using <code>volatile</code> semantics.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @param newValue the value to store
     */
    public void putLongVolatile(Object obj, long offset, long newValue)
    {
        try {
            _putLongVolatile.invoke(unsafe, obj, offset, newValue);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Gets an <code>Object</code> field from the given object,
     * using <code>volatile</code> semantics.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @return the retrieved value
     */
    public Object getObjectVolatile(Object obj, long offset)
    {
        try {
            return (Object)_getObjectVolatile.invoke(unsafe, obj, offset);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Stores an <code>Object</code> field into the given object,
     * using <code>volatile</code> semantics.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @param newValue the value to store
     */
    public void putObjectVolatile(Object obj, long offset,
                                         Object newValue)
    {
        try {
            _putObjectVolatile.invoke(unsafe, obj, offset, newValue);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Gets an <code>int</code> field from the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @return the retrieved value
     */
    public int getInt(Object obj, long offset)
    {
        try {
            return (Integer)_getInt.invoke(unsafe, obj, offset);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Stores an <code>int</code> field into the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @param newValue the value to store
     */
    public void putInt(Object obj, long offset, int newValue)
    {
        try {
            _putInt.invoke(unsafe, obj, offset, newValue);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Lazy set an int field.
     */
    public void putOrderedInt(Object obj, long offset, int newValue)
    {
        try {
            _putOrderedInt.invoke(unsafe, obj, offset, newValue);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Gets a <code>long</code> field from the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @return the retrieved value
     */
    public long getLong(Object obj, long offset)
    {
        try {
            return (Long)_getLong.invoke(unsafe, obj, offset);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Stores a <code>long</code> field into the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @param newValue the value to store
     */
    public void putLong(Object obj, long offset, long newValue)
    {
        try {
            _putLong.invoke(unsafe, obj, offset, newValue);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Lazy set a long field.
     */
    public void putOrderedLong(Object obj, long offset, long newValue)
    {
        try {
            _putOrderedLong.invoke(unsafe, obj, offset, newValue);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Gets an <code>Object</code> field from the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @return the retrieved value
     */
    public Object getObject(Object obj, long offset)
    {
        try {
            return (Object)_getObject.invoke(unsafe, obj, offset);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Stores an <code>Object</code> field into the given object.
     *
     * @param obj non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @param newValue the value to store
     */
    public void putObject(Object obj, long offset, Object newValue)
    {
        try {
            _putObject.invoke(unsafe, obj, offset, newValue);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Lazy set an object field.
     */
    public void putOrderedObject(Object obj, long offset, Object newValue)
    {
        try {
            _putOrderedObject.invoke(unsafe, obj, offset, newValue);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Parks the calling thread for the specified amount of time,
     * unless the "permit" for the thread is already available (due to
     * a previous call to {@link #unpark}. This method may also return
     * spuriously (that is, without the thread being told to unpark
     * and without the indicated amount of time elapsing).
     *
     * <p>See {@link java.util.concurrent.locks.LockSupport} for more
     * in-depth information of the behavior of this method.</p>
     *
     * @param absolute whether the given time value is absolute
     * milliseconds-since-the-epoch (<code>true</code>) or relative
     * nanoseconds-from-now (<code>false</code>)
     * @param time the (absolute millis or relative nanos) time value
     */
    public void park(boolean absolute, long time) {
        try {
            _park.invoke(unsafe, absolute, time);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Unparks the given object, which must be a {@link Thread}.
     *
     * <p>See {@link java.util.concurrent.locks.LockSupport} for more
     * in-depth information of the behavior of this method.</p>
     *
     * @param obj non-null; the object to unpark
     */
    public void unpark(Object obj) {
        try {
            _unpark.invoke(unsafe, obj);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Allocates an instance of the given class without running the constructor.
     * The class' <clinit> will be run, if necessary.
     */
    public Object allocateInstance(Class<?> c)
    {
        try {
            return _allocateInstance.invoke(unsafe, c);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
