package snownee.kiwi.shadowed.org.yaml.snakeyaml.introspector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import snownee.kiwi.shadowed.org.yaml.snakeyaml.error.YAMLException;
import snownee.kiwi.shadowed.org.yaml.snakeyaml.util.ArrayUtils;

public class FieldProperty extends GenericProperty {

    private final Field field;

    public FieldProperty(Field field) {
        super(field.getName(), field.getType(), field.getGenericType());
        this.field = field;
        field.setAccessible(true);
    }

    @Override
    public void set(Object object, Object value) throws Exception {
        this.field.set(object, value);
    }

    @Override
    public Object get(Object object) {
        try {
            return this.field.get(object);
        } catch (Exception var3) {
            throw new YAMLException("Unable to access field " + this.field.getName() + " on object " + object + " : " + var3);
        }
    }

    @Override
    public List<Annotation> getAnnotations() {
        return ArrayUtils.toUnmodifiableList(this.field.getAnnotations());
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
        return (A) this.field.getAnnotation(annotationType);
    }
}