package jarcul.comparator;

public class SameBooleanFieldComparator implements BooleanFieldComparator {
    public final double compare(Object a, Object b) {
        if (a == null && b == null) return 1;
        if (a == null || b == null)  return 0;

        boolean av = (Boolean)a;
        boolean bv = (Boolean)b;

        return av == bv ? 1.0 : 0.0;
    }
}
