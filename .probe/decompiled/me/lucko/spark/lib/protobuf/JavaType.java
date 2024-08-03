package me.lucko.spark.lib.protobuf;

public enum JavaType {

    VOID(Void.class, Void.class, null),
    INT(int.class, Integer.class, 0),
    LONG(long.class, Long.class, 0L),
    FLOAT(float.class, Float.class, 0.0F),
    DOUBLE(double.class, Double.class, 0.0),
    BOOLEAN(boolean.class, Boolean.class, false),
    STRING(String.class, String.class, ""),
    BYTE_STRING(ByteString.class, ByteString.class, ByteString.EMPTY),
    ENUM(int.class, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);

    private final Class<?> type;

    private final Class<?> boxedType;

    private final Object defaultDefault;

    private JavaType(Class<?> type, Class<?> boxedType, Object defaultDefault) {
        this.type = type;
        this.boxedType = boxedType;
        this.defaultDefault = defaultDefault;
    }

    public Object getDefaultDefault() {
        return this.defaultDefault;
    }

    public Class<?> getType() {
        return this.type;
    }

    public Class<?> getBoxedType() {
        return this.boxedType;
    }

    public boolean isValidType(Class<?> t) {
        return this.type.isAssignableFrom(t);
    }
}