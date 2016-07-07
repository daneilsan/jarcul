package jarcul.comparator;

import static java.lang.Math.abs;

public class RangeNumberFieldComparator implements NumberFieldComparator {
    private double percent;

    public RangeNumberFieldComparator(double percent) {
        this.percent = percent;
    }

    @Override
    public double compare(Object a, Object b) {
        if (a == null && b == null) return 1;
        if (a == null || b == null)  return 0;

        double av = (Double)a;
        double bv = (Double)b;

        double min = av * ((100.0 - percent) / 100.0);
        double max = av + av * (percent / 100.0);

        return bv >= min && bv <= max ? 1 : 0;
    }
}
