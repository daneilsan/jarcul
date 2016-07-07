package comparator;

import jarcul.comparator.TriangularNumberFieldComparator;
import jarcul.comparator.FieldComparator;
import org.junit.Test;
import static org.junit.Assert.*;

public class TriangularNumberFieldComparatorTest {
    @Test
    public void sameNumberReturnsOne() {
        FieldComparator comparator = new TriangularNumberFieldComparator();
        double result = comparator.compare(1.0, 1.0);

        assertTrue(result == 1.0);
    }

    @Test
    public void differentNumberDoesntReturnOne() {
        FieldComparator comparator = new TriangularNumberFieldComparator();
        double result = comparator.compare(1.0, 235.0);

        assertTrue(result != 1.0);
    }
}
