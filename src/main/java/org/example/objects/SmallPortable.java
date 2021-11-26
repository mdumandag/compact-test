package org.example.objects;

import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import org.example.serialization.PortableObjectFactory;

import java.io.IOException;

public class SmallPortable implements Portable, TestObject {

    public static final int CLASS_ID = 1;

    private int intField;
    private double doubleField;
    private long longField;
    private String stringField;

    public SmallPortable() {

    }

    public SmallPortable(int intField, double doubleField, long longField, String stringField) {
        this.intField = intField;
        this.doubleField = doubleField;
        this.longField = longField;
        this.stringField = stringField;
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
        writer.writeInt(FieldNames.INT, intField);
        writer.writeDouble(FieldNames.DOUBLE, doubleField);
        writer.writeLong(FieldNames.LONG, longField);
        writer.writeString(FieldNames.STRING, stringField);
    }

    @Override
    public void readPortable(PortableReader reader) throws IOException {
        intField = reader.readInt(FieldNames.INT);
        doubleField = reader.readDouble(FieldNames.DOUBLE);
        longField = reader.readLong(FieldNames.LONG);
        stringField = reader.readString(FieldNames.STRING);
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
                + "  'valueFormat' = 'portable',\n"
                + "  'valuePortableFactoryId' = '1',\n"
                + "  'valuePortableClassId' = '1',\n"
                + "  'valuePortableClassVersion' = '0'\n"
                + ")",
                mapName
        );
    }

}
