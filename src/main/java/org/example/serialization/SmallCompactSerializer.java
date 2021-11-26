package org.example.serialization;

import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import org.example.objects.FieldNames;
import org.example.objects.SmallCompact;
import org.jetbrains.annotations.NotNull;

public class SmallCompactSerializer implements CompactSerializer<SmallCompact> {

    @NotNull
    @Override
    public SmallCompact read(@NotNull CompactReader in) {
        return new SmallCompact(
                in.readInt(FieldNames.INT),
                in.readDouble(FieldNames.DOUBLE),
                in.readLong(FieldNames.LONG),
                in.readString(FieldNames.STRING)
        );
    }

    @Override
    public void write(@NotNull CompactWriter out, @NotNull SmallCompact object) {
        out.writeInt(FieldNames.INT, object.getIntField());
        out.writeDouble(FieldNames.DOUBLE, object.getDoubleField());
        out.writeLong(FieldNames.LONG, object.getLongField());
        out.writeString(FieldNames.STRING, object.getStringField());
    }

}
