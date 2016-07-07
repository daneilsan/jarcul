package jarcul;

import jarcul.exception.RowSetupException;

import java.util.ArrayList;
import java.util.List;

public final class RowSetup {
    private final List<FieldSetup> fieldSetupList = new ArrayList<>();
    private double maximumPossiblePoints = 0;

    public RowSetup() {}

    public RowSetup(FieldSetup... fieldSetupList) throws RowSetupException {
        for (FieldSetup fieldSetup : fieldSetupList) {
            addFieldSetup(fieldSetup);
        }
    }

    public final int getFieldsNumber() {
        return fieldSetupList.size();
    }

    public final FieldSetup getFieldSetup(int j) {
        return fieldSetupList.get(j);
    }

    public final RowSetup addFieldSetup(FieldSetup fieldSetup) throws RowSetupException {
        for (FieldSetup f : fieldSetupList) {
            if (f.name().equals(fieldSetup.name())) {
                throw new RowSetupException(String.format("A field with the name '%s' is already defined in this RowSetup", fieldSetup.name()));
            }
        }

        fieldSetupList.add(fieldSetup);

        maximumPossiblePoints = maximumPossiblePoints + fieldSetup.weight();

        return this;
    }

    public final double getMaximumPossiblePoints() {
        return maximumPossiblePoints;
    }

}
