package jarcul;

import jarcul.comparator.BooleanFieldComparator;
import jarcul.comparator.SameBooleanFieldComparator;
import jarcul.exception.FieldSetupException;

public class BooleanFieldSetup extends FieldSetup {
    private final static BooleanFieldComparator DEFAULT_COMPARATOR = new SameBooleanFieldComparator();

    public BooleanFieldSetup(String name) throws FieldSetupException {
        super(name, DEFAULT_WEIGHT, DEFAULT_COMPARATOR, DEFAULT_IS_MANDATORY);
    }

    public BooleanFieldSetup(String name, double weight) throws FieldSetupException {
        super(name, weight, DEFAULT_COMPARATOR, DEFAULT_IS_MANDATORY);
    }

    public BooleanFieldSetup(String name, double weight, BooleanFieldComparator comparator) throws FieldSetupException {
        super(name, weight, comparator, DEFAULT_IS_MANDATORY);
    }

    public BooleanFieldSetup(String name, double weight, BooleanFieldComparator comparator, boolean isMandatory) throws FieldSetupException {
        super(name, weight, comparator, isMandatory);
    }
}
