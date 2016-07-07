package comparator;

import jarcul.comparator.SimilarTextFieldComparator;
import jarcul.comparator.FieldComparator;
import org.junit.Test;
import static org.junit.Assert.*;

public class SimilarTextFieldComparatorTest {
    @Test
    public void similarTextIsEqual() {
        FieldComparator comparator = new SimilarTextFieldComparator();

        double result = comparator.compare("Qué", "que");

        assertTrue(result == 1.0);
    }

    @Test
    public void similarTextPunctuatesHigh() {
        FieldComparator comparator = new SimilarTextFieldComparator();

        double resultA = comparator.compare("Palabra", "Palabro");
        double resultB = comparator.compare("Palabra", "Palbara");
        double resultC = comparator.compare("Hatchback", "hachtbakc");

        assertTrue(resultA > 0.9);
        assertTrue(resultB > 0.9);
        assertTrue(resultC > 0.9);
    }

    @Test
    public void differentTextPunctuatesLow() {
        FieldComparator comparator = new SimilarTextFieldComparator();

        double resultA = comparator.compare("Palabra", "xxxxxx");
        double resultB = comparator.compare("Palabra", "");
        double resultC = comparator.compare("Hatchback", "Sport");

        assertTrue(resultA < 0.1);
        assertTrue(resultB < 0.1);
        assertTrue(resultC < 0.5);
    }
}
