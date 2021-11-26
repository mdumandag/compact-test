package org.example.objects;

import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import org.example.serialization.PortableObjectFactory;

import java.io.IOException;

public class LargePortable implements Portable, TestObject {

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
    private Portable objectField;
    private Portable[] objectArrayField;

    public LargePortable() {

    }

    public LargePortable(boolean booleanField, boolean[] booleanArrayField, byte byteField, byte[] byteArrayField, short shortField, short[] shortArrayField, int intField, int[] intArrayField, long longField, long[] longArrayField, float floatField, float[] floatArrayField, double doubleField, double[] doubleArrayField, String stringField, String[] stringArrayField, Portable objectField, Portable[] objectArrayField) {
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
        return PortableObjectFactory.FACTORY_ID;
    }

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public void writePortable(PortableWriter writer) throws IOException {
        writer.writeBoolean(FieldNames.BOOLEAN, booleanField);
        writer.writeBooleanArray(FieldNames.BOOLEAN_ARRAY, booleanArrayField);
        writer.writeByte(FieldNames.BYTE, byteField);
        writer.writeByteArray(FieldNames.BYTE_ARRAY, byteArrayField);
        writer.writeShort(FieldNames.SHORT, shortField);
        writer.writeShortArray(FieldNames.SHORT_ARRAY, shortArrayField);
        writer.writeInt(FieldNames.INT, intField);
        writer.writeIntArray(FieldNames.INT_ARRAY, intArrayField);
        writer.writeLong(FieldNames.LONG, longField);
        writer.writeLongArray(FieldNames.LONG_ARRAY, longArrayField);
        writer.writeFloat(FieldNames.FLOAT, floatField);
        writer.writeFloatArray(FieldNames.FLOAT_ARRAY, floatArrayField);
        writer.writeDouble(FieldNames.DOUBLE, doubleField);
        writer.writeDoubleArray(FieldNames.DOUBLE_ARRAY, doubleArrayField);
        writer.writeString(FieldNames.STRING, stringField);
        writer.writeStringArray(FieldNames.STRING_ARRAY, stringArrayField);
        writer.writePortable(FieldNames.OBJECT, objectField);
        writer.writePortableArray(FieldNames.OBJECT_ARRAY, objectArrayField);
    }

    @Override
    public void readPortable(PortableReader reader) throws IOException {
        booleanField = reader.readBoolean(FieldNames.BOOLEAN);
        booleanArrayField = reader.readBooleanArray(FieldNames.BOOLEAN_ARRAY);
        byteField = reader.readByte(FieldNames.BYTE);
        byteArrayField = reader.readByteArray(FieldNames.BYTE_ARRAY);
        shortField = reader.readShort(FieldNames.SHORT);
        shortArrayField = reader.readShortArray(FieldNames.SHORT_ARRAY);
        intField = reader.readInt(FieldNames.INT);
        intArrayField = reader.readIntArray(FieldNames.INT_ARRAY);
        longField = reader.readLong(FieldNames.LONG);
        longArrayField = reader.readLongArray(FieldNames.LONG_ARRAY);
        floatField = reader.readFloat(FieldNames.FLOAT);
        floatArrayField = reader.readFloatArray(FieldNames.FLOAT_ARRAY);
        doubleField = reader.readDouble(FieldNames.DOUBLE);
        doubleArrayField = reader.readDoubleArray(FieldNames.DOUBLE_ARRAY);
        stringField = reader.readString(FieldNames.STRING);
        stringArrayField = reader.readStringArray(FieldNames.STRING_ARRAY);
        objectField = reader.readPortable(FieldNames.OBJECT);
        objectArrayField = reader.readPortableArray(FieldNames.OBJECT_ARRAY);
    }

    @Override
    public String getCreateMappingStatement(String mapName) {
        return String.format(
                "CREATE MAPPING \"%s\" (\n"
                + "  \"booleanArrayField\" OBJECT,\n"
                + "  \"booleanField\" BOOLEAN,\n"
                + "  \"byteArrayField\" OBJECT,\n"
                + "  \"byteField\" TINYINT,\n"
                + "  \"doubleArrayField\" OBJECT,\n"
                + "  \"doubleField\" DOUBLE,\n"
                + "  \"floatArrayField\" OBJECT,\n"
                + "  \"floatField\" REAL,\n"
                + "  \"intArrayField\" OBJECT,\n"
                + "  \"intField\" INTEGER,\n"
                + "  \"longArrayField\" OBJECT,\n"
                + "  \"longField\" BIGINT,\n"
                + "  \"objectArrayField\" OBJECT,\n"
                + "  \"objectField\" OBJECT,\n"
                + "  \"shortArrayField\" OBJECT,\n"
                + "  \"shortField\" SMALLINT,\n"
                + "  \"stringArrayField\" OBJECT,\n"
                + "  \"stringField\" VARCHAR\n"
                + ")\n"
                + "TYPE IMap\n"
                + "OPTIONS (\n"
                + "  'keyFormat' = 'java',\n"
                + "  'keyJavaClass' = 'java.lang.Integer',\n"
                + "  'valueFormat' = 'portable',\n"
                + "  'valuePortableFactoryId' = '1',\n"
                + "  'valuePortableClassId' = '2',\n"
                + "  'valuePortableClassVersion' = '0'\n"
                + ")",
                mapName
        );
    }

}
