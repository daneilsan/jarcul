package comparator;

import jarcul.comparator.SynonymCollection;
import jarcul.comparator.SynonymTextFieldComparator;
import org.junit.Test;
import static org.junit.Assert.*;

public class SynonymTextFieldComparatorTest {
    @Test
    public void test() {
        SynonymCollection synonyms = new SynonymCollection()
                .addSynonym("gasolina", "gasoline")
                .addSynonym("benzin", "gasoline")
                .addSynonym("essence", "gasoline")
                .addSynonym("benzina", "gasoline");

        SynonymTextFieldComparator comparator = new SynonymTextFieldComparator(synonyms);

        assertTrue(comparator.compare("gasoline", "benzin") == 1.0);
        assertTrue(comparator.compare("gasoline", "ESSENCE") == 1.0);
        assertTrue(comparator.compare("gasoline", "   benzina   ") == 1.0);
        assertTrue(comparator.compare("gasoline", "gasoline") == 1.0);
        assertTrue(comparator.compare("gasoline", "gasoliné") == 1.0);
        assertTrue(comparator.compare("gasoline", "gasoliné") == 1.0);
    }

    @Test
    public void test2() {
        SynonymCollection synonyms = new SynonymCollection()
                .addSynonym("acosa", "thing")
                .addSynonym("zautomatic acosa", "zautomatic thing");

        SynonymTextFieldComparator comparator = new SynonymTextFieldComparator(synonyms);

        assertTrue(comparator.compare("thing", "acosa") == 1.0);
        assertTrue(comparator.compare("zautomatic thing", "zautomatic thing") == 1.0);
    }
}
