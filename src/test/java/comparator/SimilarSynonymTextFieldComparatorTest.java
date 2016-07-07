package comparator;

import jarcul.comparator.SimilarSynonymTextFieldComparator;
import jarcul.comparator.SynonymCollection;
import org.junit.Test;
import static org.junit.Assert.*;

public class SimilarSynonymTextFieldComparatorTest {
    @Test
    public void sameTextAreEquals() {
        SynonymCollection synonymCollection = new SynonymCollection();
        SimilarSynonymTextFieldComparator comparator = new SimilarSynonymTextFieldComparator(synonymCollection);

        assertTrue(comparator.compare("some text", "some text") == 1.0);
        assertTrue(comparator.compare("murciélago", "murcielago") == 1.0);
        assertTrue(comparator.compare("cosa", "COSA") == 1.0);
        assertTrue(comparator.compare("random stuff", "   random stuff   ") == 1.0);
        assertTrue(comparator.compare("capitoné", "   Capitone   ") == 1.0);
    }

    @Test
    public void similarTextIsAlmostEquals() {
        SynonymCollection synonymCollection = new SynonymCollection()
                .addSynonym("unrelated text", "unrelated")
                .addSynonym("more unrelated text", "unrelated");
        SimilarSynonymTextFieldComparator comparator = new SimilarSynonymTextFieldComparator(synonymCollection);

        assertTrue(comparator.compare("some words", "some wrods") > 0.9);
    }

    @Test
    public void equalsAfterSynonymsReplaced() {
        SynonymCollection synonymCollection = new SynonymCollection()
                .addSynonym("tfsi", "tdi")
                .addSynonym("1200", "1.2");
        SimilarSynonymTextFieldComparator comparator = new SimilarSynonymTextFieldComparator(synonymCollection);

        assertTrue(comparator.compare("car 1.2 tdi", "Car 1200 TFSI") == 1.0);
    }

    @Test
    public void similarAfterSynonymsReplaced() {
        SynonymCollection synonymCollection = new SynonymCollection()
                .addSynonym("tfsi", "tdi")
                .addSynonym("1200", "1.2");
        SimilarSynonymTextFieldComparator comparator = new SimilarSynonymTextFieldComparator(synonymCollection);

        assertTrue(comparator.compare("car 1.2 tdi", "Car 1200 TFSI sport") > 0.5);
    }
}
