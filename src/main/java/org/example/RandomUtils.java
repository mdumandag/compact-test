package org.example;

import com.hazelcast.nio.serialization.Portable;
import org.example.objects.SmallCompact;
import org.example.objects.SmallIdentified;
import org.example.objects.SmallPortable;

import java.util.Random;

public class RandomUtils {

    private static final String SYMBOLS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private final Random random;

    public RandomUtils(long seed) {
        this.random = new Random(seed);
    }

    public boolean randomBoolean() {
        return random.nextBoolean();
    }

    public boolean[] randomBooleanArray() {
        int length = 3;
        boolean[] arr = new boolean[length];
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextBoolean();
        }
        return arr;
    }

    public byte randomByte() {
        return (byte) random.nextInt();
    }

    public byte[] randomByteArray() {
        int length = 20;
        return randomByteArray(length);
    }

    public byte[] randomByteArray(int length) {
        byte[] arr = new byte[length];
        random.nextBytes(arr);
        return arr;
    }

    public short randomShort() {
        return (short) random.nextInt();
    }

    public short[] randomShortArray() {
        int length = 6;
        short[] arr = new short[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (short) random.nextInt();
        }
        return arr;
    }

    public int randomInt() {
        return random.nextInt();
    }

    public int[] randomIntArray() {
        int length = 2;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }

    public long randomLong() {
        return random.nextLong();
    }

    public long[] randomLongArray() {
        int length = 3;
        long[] arr = new long[length];
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextLong();
        }
        return arr;
    }

    public float randomFloat() {
        return random.nextFloat();
    }

    public float[] randomFloatArray() {
        int length = 5;
        float[] arr = new float[length];
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextFloat();
        }
        return arr;
    }

    public double randomDouble() {
        return random.nextDouble();
    }

    public double[] randomDoubleArray() {
        int length = 4;
        double[] arr = new double[length];
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextDouble();
        }
        return arr;
    }

    public String randomString() {
        int length = 15;
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));
        }
        return builder.toString();
    }

    public String[] randomStringArray() {
        int length = 4;
        String[] arr = new String[length];
        for (int i = 0; i < length; i++) {
            arr[i] = randomString();
        }
        return arr;
    }

    public SmallCompact randomSmallCompact() {
        return new SmallCompact(
                randomInt(),
                randomDouble(),
                randomLong(),
                randomString()
        );
    }

    public SmallCompact[] randomSmallCompactArray() {
        int length = 2;
        SmallCompact[] arr = new SmallCompact[length];
        for (int i = 0; i < length; i++) {
            arr[i] = randomSmallCompact();
        }
        return arr;
    }

    public SmallIdentified randomSmallIdentified() {
        return new SmallIdentified(
                randomInt(),
                randomDouble(),
                randomLong(),
                randomString()
        );
    }

    public SmallIdentified[] randomSmallIdentifiedArray() {
        int length = 2;
        SmallIdentified[] arr = new SmallIdentified[length];
        for (int i = 0; i < length; i++) {
            arr[i] = randomSmallIdentified();
        }
        return arr;
    }

    public Portable randomSmallPortable() {
        return new SmallPortable(
                randomInt(),
                randomDouble(),
                randomLong(),
                randomString()
        );
    }

    public Portable[] randomSmallPortableArray() {
        int length = 2;
        Portable[] arr = new Portable[length];
        for (int i = 0; i < length; i++) {
            arr[i] = randomSmallPortable();
        }
        return arr;
    }

}
