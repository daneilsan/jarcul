package comparator;

import jarcul.comparator.NumberFieldComparator;
import jarcul.comparator.SameNumberFieldComparator;

import static org.junit.Assert.*;

import org.junit.Test;

public class SameNumberFieldComparatorTest {
    @Test
    public void numbersAreEquals() {
        NumberFieldComparator comparator = new SameNumberFieldComparator();

        double result = comparator.compare(1.0, 1.0);

        assertTrue(result == 1.0);
    }

    @Test
    public void ZeroesAreEquals() {
        NumberFieldComparator comparator = new SameNumberFieldComparator();

        double result = comparator.compare(0.0, 0.0);

        assertTrue(result == 1.0);
    }

    @Test
    public void SightlyDifferentNumbersAreEquals() {
        /*
         The comparator has an DEFAULT_EPSILON value that is like an error margin.
         If the error is less than this value, then the numbers are considered to be equal
          */

        NumberFieldComparator comparator = new SameNumberFieldComparator();

        double result = comparator.compare(1.0, 1.001);

        assertTrue(result == 1.0);
    }

    @Test
    public void DifferentNumbersAreNotEquals() {
        NumberFieldComparator comparator = new SameNumberFieldComparator();

        double result = comparator.compare(1.0, 2.0);

        assertTrue(result == 0.0);
    }
}
