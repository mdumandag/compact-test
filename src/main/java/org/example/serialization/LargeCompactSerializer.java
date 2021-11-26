package org.example.serialization;

import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import org.example.objects.FieldNames;
import org.example.objects.LargeCompact;
import org.example.objects.SmallCompact;
import org.jetbrains.annotations.NotNull;

public class LargeCompactSerializer implements CompactSerializer<LargeCompact> {

    @NotNull
    @Override
    public LargeCompact read(@NotNull CompactReader in) {
        return new LargeCompact(
                in.readBoolean(FieldNames.BOOLEAN),
                in.readArrayOfBooleans(FieldNames.BOOLEAN_ARRAY),
                in.readByte(FieldNames.BYTE),
                in.readArrayOfBytes(FieldNames.BYTE_ARRAY),
                in.readShort(FieldNames.SHORT),
                in.readArrayOfShorts(FieldNames.SHORT_ARRAY),
                in.readInt(FieldNames.INT),
                in.readArrayOfInts(FieldNames.INT_ARRAY),
                in.readLong(FieldNames.LONG),
                in.readArrayOfLongs(FieldNames.LONG_ARRAY),
                in.readFloat(FieldNames.FLOAT),
                in.readArrayOfFloats(FieldNames.FLOAT_ARRAY),
                in.readDouble(FieldNames.DOUBLE),
                in.readArrayOfDoubles(FieldNames.DOUBLE_ARRAY),
                in.readString(FieldNames.STRING),
                in.readArrayOfStrings(FieldNames.STRING_ARRAY),
                in.readCompact(FieldNames.OBJECT),
                in.readArrayOfCompacts(FieldNames.OBJECT_ARRAY, SmallCompact.class)
        );
    }

    @Override
    public void write(@NotNull CompactWriter out, @NotNull LargeCompact object) {
        out.writeBoolean(FieldNames.BOOLEAN, object.getBooleanField());
        out.writeArrayOfBooleans(FieldNames.BOOLEAN_ARRAY, object.getBooleanArrayField());
        out.writeByte(FieldNames.BYTE, object.getByteField());
        out.writeArrayOfBytes(FieldNames.BYTE_ARRAY, object.getByteArrayField());
        out.writeShort(FieldNames.SHORT, object.getShortField());
        out.writeArrayOfShorts(FieldNames.SHORT_ARRAY, object.getShortArrayField());
        out.writeInt(FieldNames.INT, object.getIntField());
        out.writeArrayOfInts(FieldNames.INT_ARRAY, object.getIntArrayField());
        out.writeLong(FieldNames.LONG, object.getLongField());
        out.writeArrayOfLongs(FieldNames.LONG_ARRAY, object.getLongArrayField());
        out.writeFloat(FieldNames.FLOAT, object.getFloatField());
        out.writeArrayOfFloats(FieldNames.FLOAT_ARRAY, object.getFloatArrayField());
        out.writeDouble(FieldNames.DOUBLE, object.getDoubleField());
        out.writeArrayOfDoubles(FieldNames.DOUBLE_ARRAY, object.getDoubleArrayField());
        out.writeString(FieldNames.STRING, object.getStringField());
        out.writeArrayOfStrings(FieldNames.STRING_ARRAY, object.getStringArrayField());
        out.writeCompact(FieldNames.OBJECT, object.getObjectField());
        out.writeArrayOfCompacts(FieldNames.OBJECT_ARRAY, object.getObjectArrayField());
    }

}
