package jarcul.comparator;

import static java.lang.Math.abs;

public class TriangularNumberFieldComparator implements NumberFieldComparator {
    private static final double DEFAULT_MAGNITUDE = 100.0;
    private double magnitude;

    public TriangularNumberFieldComparator() {
        this(DEFAULT_MAGNITUDE);
    }

    /**
     *
     * @param magnitude non negative value that specify the validity of the magnitude of the number being compared against
     */
    public TriangularNumberFieldComparator(double magnitude) {
        this.magnitude = magnitude;
    }

    @Override
    public double compare(Object a, Object b) {
        if (a == null && b == null) return 1;
        if (a == null || b == null)  return 0;

        double av = (Double)a;
        double bv = (Double)b;

        double result = 1.0 - abs(av - bv) / magnitude;
        return result < 0 ? 0 : result;
    }
}
