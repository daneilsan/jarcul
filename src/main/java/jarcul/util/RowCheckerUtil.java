package jarcul.util;

import jarcul.FieldSetup;
import jarcul.Row;
import jarcul.RowSetup;

public class RowCheckerUtil {
    public static boolean haveDifferentFieldNumbers(Row row, RowSetup rowSetup) {
        return row.getFieldsCount() != rowSetup.getFieldsNumber();
    }

    public static boolean haveDifferentFieldTypes(Row row, RowSetup rowSetup) {
        for (int j = 0; j < row.getFieldsCount(); j++) {
            Object f = row.getField(j);
            FieldSetup fs = rowSetup.getFieldSetup(j);

            if (!FieldTypeUtil.getClass(fs).equals(f.getClass())) {
                return true;
            }
        }

        return false;
    }
}
