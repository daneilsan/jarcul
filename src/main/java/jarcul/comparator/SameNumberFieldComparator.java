package jarcul.comparator;

import static java.lang.Math.abs;

public class SameNumberFieldComparator implements NumberFieldComparator {
    protected static final double DEFAULT_EPSILON = 0.001;
    protected final double epsilon;

    public SameNumberFieldComparator() {
        this(DEFAULT_EPSILON);
    }

    public SameNumberFieldComparator(double epsilon) {
        this.epsilon = epsilon;
    }

    @Override
    public double compare(Object a, Object b) {
        if (a == null && b == null) return 1;
        if (a == null || b == null)  return 0;

        double av = (Double)a;
        double bv = (Double)b;

        double difference = av > bv ? av - bv : bv - av;

        return difference < epsilon ? 1 : 0;
    }
}
