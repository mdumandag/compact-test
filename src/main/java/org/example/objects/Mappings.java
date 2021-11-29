package org.example.objects;

import org.example.ObjectKind;

public final class Mappings {

    public static String mappingFor(ObjectKind objectKind, String mapName) {
        switch (objectKind) {
            case COMPACT:
                return mappingForCompact(mapName);
            case PORTABLE:
                return mappingForPortable(mapName);
            case IDENTIFIED:
                return mappingForIdentified(mapName);
            default:
                throw new IllegalStateException("Unknown object kind: " + objectKind);
        }
    }

    private static String mappingForCompact(String mapName) {
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

    private static String mappingForPortable(String mapName) {
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

    private static String mappingForIdentified(String mapName) {
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
