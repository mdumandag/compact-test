package org.example;

import com.hazelcast.config.CompactSerializationConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.internal.serialization.Data;
import com.hazelcast.internal.serialization.InternalSerializationService;
import com.hazelcast.internal.serialization.impl.DefaultSerializationServiceBuilder;
import com.hazelcast.internal.serialization.impl.compact.Schema;
import com.hazelcast.internal.serialization.impl.compact.SchemaService;
import org.example.objects.LargeCompact;
import org.example.objects.LargeIdentified;
import org.example.objects.SmallCompact;
import org.example.objects.TestObjectFactory;
import org.example.serialization.LargeCompactSerializer;
import org.example.serialization.SmallCompactSerializer;

import java.nio.charset.StandardCharsets;


public class Main {
    public static void main(String[] args) {
        Object o = TestObjectFactory.create(ObjectKind.BYTE_ARRAY, ObjectSize.LARGE);
        InternalSerializationService service = new DefaultSerializationServiceBuilder()
                .setConfig(new SerializationConfig().setCompactSerializationConfig(new CompactSerializationConfig()
                        .setEnabled(true)
                        .register(LargeCompact.class, "large", new LargeCompactSerializer())
                        .register(SmallCompact.class, "small", new SmallCompactSerializer())))
                .setSchemaService(new SchemaService() {
                    @Override
                    public Schema get(long l) {
                        return null;
                    }

                    @Override
                    public void put(Schema schema) {

                    }

                    @Override
                    public void putLocal(Schema schema) {

                    }
                })
                .build();
        Data data = service.toData(o);
        System.out.println(data.dataSize());
    }
}
