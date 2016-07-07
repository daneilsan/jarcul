package jarcul;

import jarcul.comparator.NumberFieldComparator;
import jarcul.comparator.SameNumberFieldComparator;
import jarcul.exception.FieldSetupException;

public class NumberFieldSetup extends FieldSetup {
    private final static NumberFieldComparator DEFAULT_COMPARATOR = new SameNumberFieldComparator();

    public NumberFieldSetup(String name) throws FieldSetupException {
        super(name, DEFAULT_WEIGHT, DEFAULT_COMPARATOR, DEFAULT_IS_MANDATORY);
    }

    public NumberFieldSetup(String name, double weight) throws FieldSetupException {
        super(name, weight, DEFAULT_COMPARATOR, DEFAULT_IS_MANDATORY);
    }

    public NumberFieldSetup(String name, double weight, NumberFieldComparator comparator) throws FieldSetupException {
        super(name, weight, comparator, DEFAULT_IS_MANDATORY);
    }

    public NumberFieldSetup(String name, double weight, NumberFieldComparator comparator, boolean isMandatory) throws FieldSetupException {
        super(name, weight, comparator, isMandatory);
    }
}
