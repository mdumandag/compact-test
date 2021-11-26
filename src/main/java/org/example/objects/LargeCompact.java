package org.example.objects;

public class LargeCompact implements TestObject {

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
    private SmallCompact objectField;
    private SmallCompact[] objectArrayField;

    public LargeCompact(boolean booleanField, boolean[] booleanArrayField, byte byteField, byte[] byteArrayField, short shortField, short[] shortArrayField, int intField, int[] intArrayField, long longField, long[] longArrayField, float floatField, float[] floatArrayField, double doubleField, double[] doubleArrayField, String stringField, String[] stringArrayField, SmallCompact objectField, SmallCompact[] objectArrayField) {
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

    public boolean getBooleanField() {
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

    public SmallCompact getObjectField() {
        return objectField;
    }

    public SmallCompact[] getObjectArrayField() {
        return objectArrayField;
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
                + "  'valueFormat' = 'compact',\n"
                + "  'valueCompactTypeName' = 'large'\n"
                + ")",
                mapName
        );
    }
}
