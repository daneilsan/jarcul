package jarcul;

import jarcul.comparator.SameTextFieldComparator;
import jarcul.comparator.TextFieldComparator;
import jarcul.exception.FieldSetupException;

public class TextFieldSetup extends FieldSetup {
    private final static TextFieldComparator DEFAULT_COMPARATOR = new SameTextFieldComparator();

    public TextFieldSetup(String name) throws FieldSetupException {
        super(name, DEFAULT_WEIGHT, DEFAULT_COMPARATOR, DEFAULT_IS_MANDATORY);
    }

    public TextFieldSetup(String name, double weight) throws FieldSetupException {
        super(name, weight, DEFAULT_COMPARATOR, DEFAULT_IS_MANDATORY);
    }

    public TextFieldSetup(String name, double weight, TextFieldComparator comparator) throws FieldSetupException {
        super(name, weight, comparator, DEFAULT_IS_MANDATORY);
    }

    public TextFieldSetup(String name, double weight, TextFieldComparator comparator, boolean isMandatory) throws FieldSetupException {
        super(name, weight, comparator, isMandatory);
    }
}
