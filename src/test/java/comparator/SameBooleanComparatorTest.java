package comparator;

import jarcul.comparator.SameBooleanFieldComparator;
import jarcul.comparator.FieldComparator;
import org.junit.Test;
import static org.junit.Assert.*;

public class SameBooleanComparatorTest {
    @Test
    public void differentValuesReturnsZero() {
        FieldComparator comparator = new SameBooleanFieldComparator();

        double resultA = comparator.compare(true, false);
        double resultB = comparator.compare(false, true);

        assertTrue(resultA == 0.0);
        assertTrue(resultB == 0.0);
    }

    @Test
    public void trueAgainstTrueReturnsOne() {
        FieldComparator comparator = new SameBooleanFieldComparator();

        double result = comparator.compare(true, true);

        assertTrue(result == 1.0);
    }

    @Test
    public void falseAgainstFalseReturnsZero() {
        FieldComparator comparator = new SameBooleanFieldComparator();

        double result = comparator.compare(false, false);

        assertTrue(result == 1.0);
    }
}
