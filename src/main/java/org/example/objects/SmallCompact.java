package org.example.objects;

public class SmallCompact implements TestObject {

    private int intField;
    private double doubleField;
    private long longField;
    private String stringField;

    public SmallCompact(int intField, double doubleField, long longField, String stringField) {
        this.intField = intField;
        this.doubleField = doubleField;
        this.longField = longField;
        this.stringField = stringField;
    }

    public int getIntField() {
        return intField;
    }

    public double getDoubleField() {
        return doubleField;
    }

    public long getLongField() {
        return longField;
    }

    public String getStringField() {
        return stringField;
    }

    @Override
    public String getCreateMappingStatement(String mapName) {
        return String.format(
                "CREATE MAPPING \"%s\" (\n"
                + "  \"doubleField\" DOUBLE,\n"
                + "  \"intField\" INTEGER,\n"
                + "  \"longField\" BIGINT,\n"
                + "  \"stringField\" VARCHAR\n"
                + ")\n"
                + "TYPE IMap\n"
                + "OPTIONS (\n"
                + "  'keyFormat' = 'java',\n"
                + "  'keyJavaClass' = 'java.lang.Integer',\n"
                + "  'valueFormat' = 'compact',\n"
                + "  'valueCompactTypeName' = 'small'"
                + ")",
                mapName
        );
    }

}

