package jarcul;

import java.util.ArrayList;
import java.util.List;

public final class Row {
    private List<Object> fieldList;

    public Row (Object... fields) {
        fieldList = new ArrayList<>(fields.length);

        for (Object f : fields) {
            if (f == null) {
                addNullField();
                continue;
            }

            if (f instanceof Byte) {
                addField((Byte)f);
            } else if (f instanceof Short) {
                addField((Short) f);
            } else if (f instanceof Integer) {
                addField((Integer) f);
            } else if (f instanceof Long) {
                addField((Long) f);
            } else if (f instanceof Boolean) {
                addField((Boolean) f);
            } else if (f instanceof Float) {
                addField((Float) f);
            } else if (f instanceof Double) {
                addField((Double) f);
            } else if (f instanceof String) {
                addField((String) f);
            } // else {
                // is an unknown type
                // do nothing
            // }

        }
    }

    public Row(int capacity) {
        fieldList = new ArrayList<>(capacity);
    }

    public Row() {
        this(1);
    }

    public final int getFieldsCount() {
        return fieldList.size();
    }

    public final void addField(String value) {
        fieldList.add(value);
    }

    public final void addField(boolean value) {
        fieldList.add(value);
    }

    public final void addField(double value) { fieldList.add(value); }

    public final void addNullField() { fieldList.add(null); }

    public final Object getField(int j) {
        return fieldList.get(j);
    }
}
