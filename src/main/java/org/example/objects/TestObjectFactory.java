package org.example.objects;

import com.hazelcast.internal.serialization.Data;
import com.hazelcast.internal.serialization.InternalSerializationService;
import com.hazelcast.internal.serialization.impl.DefaultSerializationServiceBuilder;
import com.hazelcast.nio.serialization.Portable;
import org.example.ObjectKind;
import org.example.ObjectSize;
import org.example.RandomUtils;

import java.nio.charset.StandardCharsets;

public class TestObjectFactory {

    private static final boolean aBoolean = true;
    private static final boolean[] aBooleanArray = new boolean[]{true, false, true};
    private static final byte aByte = -99;
    private static final byte[] aByteArray = "Lorem ipsum dolor sit amet.".getBytes(StandardCharsets.UTF_8);
    private static final short aShort = Short.MIN_VALUE;
    private static final short[] aShortArray = new short[]{0, 1, 2, 3, -5, Short.MAX_VALUE};
    private static final int anInt = 42;
    private static final int[] anIntArray = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
    private static final long aLong = 1234567890;
    private static final long[] aLongArray = new long[]{0, -1, 1};
    private static final float aFloat = 13.5f;
    private static final float[] aFloatArray = new float[0];
    private static final double aDouble = -420.42;
    private static final double[] aDoubleArray = new double[]{0, -0.1, Double.MAX_VALUE, Double.MIN_VALUE};
    private static final String aString = "Lorem ipsum dolor sit amet.";
    private static final String[] aStringArray = new String[]{"Lorem", "ipsum", "dolor", "sit", "amet", "\uD83D\uDE07"};
    private static final SmallCompact aSmallCompact = new SmallCompact(anInt, aDouble, aLong, aString);
    private static final SmallCompact[] aSmallCompactArray = new SmallCompact[]{aSmallCompact};
    private static final Portable aSmallPortable = new SmallPortable(anInt, aDouble, aLong, aString);
    private static final Portable[] aSmallPortableArray = new Portable[]{aSmallPortable};
    private static final SmallIdentified aSmallIdentified = new SmallIdentified(anInt, aDouble, aLong, aString);
    private static final SmallIdentified[] aSmallIdentifiedArray = new SmallIdentified[]{aSmallIdentified};

    private static final RandomUtils randomUtils = new RandomUtils(42);

    public static Object create(ObjectKind objectKind, ObjectSize objectSize) {
        switch (objectKind) {
            case COMPACT:
                switch (objectSize) {
                    case SMALL:
                        return new SmallCompact(
                                anInt,
                                aDouble,
                                aLong,
                                aString
                        );
                    case LARGE:
                        return new LargeCompact(
                                aBoolean,
                                aBooleanArray,
                                aByte,
                                aByteArray,
                                aShort,
                                aShortArray,
                                anInt,
                                anIntArray,
                                aLong,
                                aLongArray,
                                aFloat,
                                aFloatArray,
                                aDouble,
                                aDoubleArray,
                                aString,
                                aStringArray,
                                aSmallCompact,
                                aSmallCompactArray
                        );
                    default:
                        throwForUnknownObjectSize(objectSize);
                }
            case PORTABLE:
                switch (objectSize) {
                    case SMALL:
                        return new SmallPortable(
                                anInt,
                                aDouble,
                                aLong,
                                aString
                        );
                    case LARGE:
                        return new LargePortable(
                                aBoolean,
                                aBooleanArray,
                                aByte,
                                aByteArray,
                                aShort,
                                aShortArray,
                                anInt,
                                anIntArray,
                                aLong,
                                aLongArray,
                                aFloat,
                                aFloatArray,
                                aDouble,
                                aDoubleArray,
                                aString,
                                aStringArray,
                                aSmallPortable,
                                aSmallPortableArray
                        );
                    default:
                        throwForUnknownObjectSize(objectSize);
                }
            case IDENTIFIED:
                switch (objectSize) {
                    case SMALL:
                        return new SmallIdentified(
                                anInt,
                                aDouble,
                                aLong,
                                aString
                        );
                    case LARGE:
                        return new LargeIdentified(
                                aBoolean,
                                aBooleanArray,
                                aByte,
                                aByteArray,
                                aShort,
                                aShortArray,
                                anInt,
                                anIntArray,
                                aLong,
                                aLongArray,
                                aFloat,
                                aFloatArray,
                                aDouble,
                                aDoubleArray,
                                aString,
                                aStringArray,
                                aSmallIdentified,
                                aSmallIdentifiedArray
                        );
                    default:
                        throwForUnknownObjectSize(objectSize);
                }
            case BYTE_ARRAY:
                return createByteArray(objectSize, false);
            default:
                throw new IllegalStateException("Unknown object kind: " + objectKind);
        }
    }

    public static Object createRandomized(ObjectKind objectKind, ObjectSize objectSize) {
        switch (objectKind) {
            case COMPACT:
                switch (objectSize) {
                    case SMALL:
                        return randomUtils.randomSmallCompact();
                    case LARGE:
                        return new LargeCompact(
                                randomUtils.randomBoolean(),
                                randomUtils.randomBooleanArray(),
                                randomUtils.randomByte(),
                                randomUtils.randomByteArray(),
                                randomUtils.randomShort(),
                                randomUtils.randomShortArray(),
                                randomUtils.randomInt(),
                                randomUtils.randomIntArray(),
                                randomUtils.randomLong(),
                                randomUtils.randomLongArray(),
                                randomUtils.randomFloat(),
                                randomUtils.randomFloatArray(),
                                randomUtils.randomDouble(),
                                randomUtils.randomDoubleArray(),
                                randomUtils.randomString(),
                                randomUtils.randomStringArray(),
                                randomUtils.randomSmallCompact(),
                                randomUtils.randomSmallCompactArray()
                        );
                    default:
                        throwForUnknownObjectSize(objectSize);
                }
            case PORTABLE:
                switch (objectSize) {
                    case SMALL:
                        return randomUtils.randomSmallPortable();
                    case LARGE:
                        return new LargePortable(
                                randomUtils.randomBoolean(),
                                randomUtils.randomBooleanArray(),
                                randomUtils.randomByte(),
                                randomUtils.randomByteArray(),
                                randomUtils.randomShort(),
                                randomUtils.randomShortArray(),
                                randomUtils.randomInt(),
                                randomUtils.randomIntArray(),
                                randomUtils.randomLong(),
                                randomUtils.randomLongArray(),
                                randomUtils.randomFloat(),
                                randomUtils.randomFloatArray(),
                                randomUtils.randomDouble(),
                                randomUtils.randomDoubleArray(),
                                randomUtils.randomString(),
                                randomUtils.randomStringArray(),
                                randomUtils.randomSmallPortable(),
                                randomUtils.randomSmallPortableArray()
                        );
                    default:
                        throwForUnknownObjectSize(objectSize);
                }
            case IDENTIFIED:
                switch (objectSize) {
                    case SMALL:
                        return randomUtils.randomSmallIdentified();
                    case LARGE:
                        return new LargeIdentified(
                                randomUtils.randomBoolean(),
                                randomUtils.randomBooleanArray(),
                                randomUtils.randomByte(),
                                randomUtils.randomByteArray(),
                                randomUtils.randomShort(),
                                randomUtils.randomShortArray(),
                                randomUtils.randomInt(),
                                randomUtils.randomIntArray(),
                                randomUtils.randomLong(),
                                randomUtils.randomLongArray(),
                                randomUtils.randomFloat(),
                                randomUtils.randomFloatArray(),
                                randomUtils.randomDouble(),
                                randomUtils.randomDoubleArray(),
                                randomUtils.randomString(),
                                randomUtils.randomStringArray(),
                                randomUtils.randomSmallIdentified(),
                                randomUtils.randomSmallIdentifiedArray()
                        );
                    default:
                        throwForUnknownObjectSize(objectSize);
                }
            case BYTE_ARRAY:
                return createByteArray(objectSize, true);
            default:
                throw new IllegalStateException("Unknown object kind: " + objectKind);
        }
    }

    private static void throwForUnknownObjectSize(ObjectSize objectSize) {
        throw new IllegalStateException("Unknown object size: " + objectSize);
    }

    private static byte[] createByteArray(ObjectSize objectSize, boolean isRandomized) {
        int size = getSerializedSizeOfIdentified(objectSize, isRandomized);
        return randomUtils.randomByteArray(size);
    }

    private static int getSerializedSizeOfIdentified(ObjectSize objectSize, boolean isRandomized) {
        Object o;
        if (isRandomized) {
            o = createRandomized(ObjectKind.IDENTIFIED, objectSize);
        } else {
            o = create(ObjectKind.IDENTIFIED, objectSize);
        }

        InternalSerializationService service = new DefaultSerializationServiceBuilder().build();
        Data data = service.toData(o);
        return data.dataSize();
    }

}
