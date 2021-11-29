package org.example.objects;

public class SmallCompact {

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

}

