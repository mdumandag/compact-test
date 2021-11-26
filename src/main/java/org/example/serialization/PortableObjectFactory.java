package org.example.serialization;

import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableFactory;
import org.example.objects.LargePortable;
import org.example.objects.SmallPortable;

public class PortableObjectFactory implements PortableFactory {

    public static final int FACTORY_ID = 1;

    @Override
    public Portable create(int classId) {
        if (classId == SmallPortable.CLASS_ID) {
            return new SmallPortable();
        } else if (classId == LargePortable.CLASS_ID) {
            return new LargePortable();
        }
        return null;
    }

}
