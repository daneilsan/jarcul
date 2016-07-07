package jarcul.comparator;

import info.debatty.java.stringsimilarity.JaroWinkler;
import org.apache.commons.lang3.StringUtils;

public class SimilarSynonymTextFieldComparator implements TextFieldComparator {
    private final static JaroWinkler comparator = new JaroWinkler();
    private final static boolean DEFAULT_TO_LOWERCASE = true;
    private final static boolean DEFAULT_TO_REMOVE_ACCENTS = true;
    private final static boolean DEFAULT_TO_TRIM = true;

    private SynonymCollection synonymCollection;
    private final boolean toLowerCase;
    private final boolean removeAccents;
    private final boolean trimSpaces;

    public SimilarSynonymTextFieldComparator(SynonymCollection synonyms) {
        this(synonyms, DEFAULT_TO_LOWERCASE, DEFAULT_TO_REMOVE_ACCENTS, DEFAULT_TO_TRIM);
    }

    public SimilarSynonymTextFieldComparator(SynonymCollection synonyms, boolean toLowerCase, boolean removeAccents, boolean trimSpaces) {
        this.synonymCollection = synonyms;
        this.toLowerCase = toLowerCase;
        this.removeAccents = removeAccents;
        this.trimSpaces = trimSpaces;
    }

    @Override
    public double compare(Object a, Object b) {
        if (a == null && b == null) return 1;
        if (a == null || b == null)  return 0;

        String av = (String)a;
        String bv = (String)b;

        if (toLowerCase) {
            av = av.toLowerCase();
            bv = bv.toLowerCase();
        }

        if (removeAccents) {
            av = StringUtils.stripAccents(av);
            bv = StringUtils.stripAccents(bv);
        }

        if (trimSpaces) {
            av = av.trim();
            bv = bv.trim();
        }

        // firt, we try to check equality

        if (av.equals(bv)) {
            return 1;
        }

        //todo no me queda claro que nunca se use la primera distancia en algún caso de uso

        // then we check distance before we substitute synonyms
        double distanceWithoutSubstitutingSynonyms = comparator.similarity(av, bv);

        bv = StringUtils.replaceEach(bv, synonymCollection.getTextToBeReplacedAsArray(), synonymCollection.getReplacementAsArray());

        double distanceAfterSubstitugingSynonyms = comparator.similarity(av, bv);

        // finally we return the best result obtained

        return distanceWithoutSubstitutingSynonyms > distanceAfterSubstitugingSynonyms ?
                distanceWithoutSubstitutingSynonyms : distanceAfterSubstitugingSynonyms;
    }
}
