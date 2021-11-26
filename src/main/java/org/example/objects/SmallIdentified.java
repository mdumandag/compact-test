package org.example.objects;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import org.example.serialization.IdentifiedObjectFactory;

import java.io.IOException;

public class SmallIdentified implements IdentifiedDataSerializable, TestObject {

    public static final int CLASS_ID = 1;

    private int intField;
    private double doubleField;
    private long longField;
    private String stringField;

    public SmallIdentified() {

    }

    public SmallIdentified(int intField, double doubleField, long longField, String stringField) {
        this.intField = intField;
        this.doubleField = doubleField;
        this.longField = longField;
        this.stringField = stringField;
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
        out.writeInt(intField);
        out.writeDouble(doubleField);
        out.writeLong(longField);
        out.writeString(stringField);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        intField = in.readInt();
        doubleField = in.readDouble();
        longField = in.readLong();
        stringField = in.readString();
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
                + "  'valueFormat' = 'java',\n"
                + "  'valueJavaClass' = 'org.example.objects.SmallIdentified'"
                + ")",
                mapName
        );
    }

}
