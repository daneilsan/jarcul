package jarcul;

import jarcul.comparator.FieldComparator;
import jarcul.exception.FieldSetupException;

public abstract class FieldSetup {
    protected final static boolean DEFAULT_IS_MANDATORY = false;
    protected final static double DEFAULT_WEIGHT = 1.0;
    protected String name;
    protected double weight;
    protected FieldComparator comparator;
    protected boolean isMandatory;

    public FieldSetup(String name, double weight, FieldComparator comparator, boolean isMandatory) throws FieldSetupException {
        if (name == null || name.trim().equals("")) {
            throw new FieldSetupException("The name must be set");
        }

        errorIfNegativeWeight(weight);

        if (comparator == null) {
            throw new FieldSetupException("The comparator must be set");
        }

        this.name = name;
        this.weight = weight;
        this.comparator = comparator;
        this.isMandatory = isMandatory;
    }

    protected static void errorIfNegativeWeight(double weight) throws FieldSetupException {
        if (weight < 0) {
            throw new FieldSetupException("The weight can not be negative");
        }
    }

    public final String name() {
        return name;
    }

    public final double weight() {
        return weight;
    }

    public final FieldComparator comparator() { return comparator; }

    public final boolean isMandatory() {
        return isMandatory;
    }
}
