package org.example.serialization;

import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import org.example.objects.LargeIdentified;
import org.example.objects.SmallIdentified;

public class IdentifiedObjectFactory implements DataSerializableFactory {

    public static final int FACTORY_ID = 2;

    @Override
    public IdentifiedDataSerializable create(int typeId) {
        if (typeId == SmallIdentified.CLASS_ID) {
            return new SmallIdentified();
        } else if (typeId == LargeIdentified.CLASS_ID) {
            return new LargeIdentified();
        }
        return null;
    }

}
