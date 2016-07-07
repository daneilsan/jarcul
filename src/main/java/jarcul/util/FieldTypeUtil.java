package jarcul.util;

import jarcul.*;

public final class FieldTypeUtil {
    public static Class getClass(FieldSetup setup) {
        if (setup instanceof BooleanFieldSetup) return Boolean.class;
        else if (setup instanceof NumberFieldSetup) return Double.class;
        else /* if (setup instanceof TextFieldSetup) */ return String.class;
    }
}
