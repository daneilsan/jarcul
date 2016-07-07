package comparator;

import jarcul.comparator.SameTextFieldComparator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by daniel.garcia on 30/06/2016.
 */
public class SameTextFieldComparatorTest {
    @Test
    public void nullEqualsEmptyIfConfigured() {
        String a = "";
        String b = null;

        SameTextFieldComparator comparator = new SameTextFieldComparator(true, true, false, true);
        double result = comparator.compare(a, b);

        assertTrue("null equals empty", result == 1.0);
    }

    @Test
    public void nullDistinctEmpty() {
        String a = "";
        String b = null;

        SameTextFieldComparator comparator = new SameTextFieldComparator(true, true, false, false);
        double result = comparator.compare(a, b);

        assertTrue("null equals empty", result != 1.0);
    }
}
