package jarcul.comparator;


public class NoOpFieldComparator implements TextFieldComparator, NumberFieldComparator, BooleanFieldComparator {
    @Override
    public double compare(Object a, Object b) {
        return 0.0;
    }
}
