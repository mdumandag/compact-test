package org.example.objects;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import org.example.serialization.IdentifiedObjectFactory;

import java.io.IOException;

public class LargeIdentified implements IdentifiedDataSerializable {

    public static final int CLASS_ID = 2;

    private boolean booleanField;
    private boolean[] booleanArrayField;
    private byte byteField;
    private byte[] byteArrayField;
    private short shortField;
    private short[] shortArrayField;
    private int intField;
    private int[] intArrayField;
    private long longField;
    private long[] longArrayField;
    private float floatField;
    private float[] floatArrayField;
    private double doubleField;
    private double[] doubleArrayField;
    private String stringField;
    private String[] stringArrayField;
    private SmallIdentified objectField;
    private SmallIdentified[] objectArrayField;

    public LargeIdentified() {

    }

    public LargeIdentified(boolean booleanField, boolean[] booleanArrayField, byte byteField, byte[] byteArrayField, short shortField, short[] shortArrayField, int intField, int[] intArrayField, long longField, long[] longArrayField, float floatField, float[] floatArrayField, double doubleField, double[] doubleArrayField, String stringField, String[] stringArrayField, SmallIdentified objectField, SmallIdentified[] objectArrayField) {
        this.booleanField = booleanField;
        this.booleanArrayField = booleanArrayField;
        this.byteField = byteField;
        this.byteArrayField = byteArrayField;
        this.shortField = shortField;
        this.shortArrayField = shortArrayField;
        this.intField = intField;
        this.intArrayField = intArrayField;
        this.longField = longField;
        this.longArrayField = longArrayField;
        this.floatField = floatField;
        this.floatArrayField = floatArrayField;
        this.doubleField = doubleField;
        this.doubleArrayField = doubleArrayField;
        this.stringField = stringField;
        this.stringArrayField = stringArrayField;
        this.objectField = objectField;
        this.objectArrayField = objectArrayField;
    }

    @Override
    public int getFactoryId() {
        return IdentifiedObjectFactory.FACTORY_ID;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeBoolean(booleanField);
        out.writeBooleanArray(booleanArrayField);
        out.writeByte(byteField);
        out.writeByteArray(byteArrayField);
        out.writeShort(shortField);
        out.writeShortArray(shortArrayField);
        out.writeInt(intField);
        out.writeIntArray(intArrayField);
        out.writeLong(longField);
        out.writeLongArray(longArrayField);
        out.writeFloat(floatField);
        out.writeFloatArray(floatArrayField);
        out.writeDouble(doubleField);
        out.writeDoubleArray(doubleArrayField);
        out.writeString(stringField);
        out.writeStringArray(stringArrayField);
        out.writeObject(objectField);
        int n = objectArrayField.length;
        out.writeInt(n);
        for (SmallIdentified smallIdentified : objectArrayField) {
            out.writeObject(smallIdentified);
        }
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        booleanField = in.readBoolean();
        booleanArrayField = in.readBooleanArray();
        byteField = in.readByte();
        byteArrayField = in.readByteArray();
        shortField = in.readShort();
        shortArrayField = in.readShortArray();
        intField = in.readInt();
        intArrayField = in.readIntArray();
        longField = in.readLong();
        longArrayField = in.readLongArray();
        floatField = in.readFloat();
        floatArrayField = in.readFloatArray();
        doubleField = in.readDouble();
        doubleArrayField = in.readDoubleArray();
        stringField = in.readString();
        stringArrayField = in.readStringArray();
        objectField = in.readObject();
        int n = in.readInt();
        SmallIdentified[] objects = new SmallIdentified[n];
        for (int i = 0; i < n; i++) {
            objects[i] = in.readObject();
        }
        objectArrayField = objects;
    }

    public boolean isBooleanField() {
        return booleanField;
    }

    public boolean[] getBooleanArrayField() {
        return booleanArrayField;
    }

    public byte getByteField() {
        return byteField;
    }

    public byte[] getByteArrayField() {
        return byteArrayField;
    }

    public short getShortField() {
        return shortField;
    }

    public short[] getShortArrayField() {
        return shortArrayField;
    }

    public int getIntField() {
        return intField;
    }

    public int[] getIntArrayField() {
        return intArrayField;
    }

    public long getLongField() {
        return longField;
    }

    public long[] getLongArrayField() {
        return longArrayField;
    }

    public float getFloatField() {
        return floatField;
    }

    public float[] getFloatArrayField() {
        return floatArrayField;
    }

    public double getDoubleField() {
        return doubleField;
    }

    public double[] getDoubleArrayField() {
        return doubleArrayField;
    }

    public String getStringField() {
        return stringField;
    }

    public String[] getStringArrayField() {
        return stringArrayField;
    }

    public SmallIdentified getObjectField() {
        return objectField;
    }

    public SmallIdentified[] getObjectArrayField() {
        return objectArrayField;
    }

}
