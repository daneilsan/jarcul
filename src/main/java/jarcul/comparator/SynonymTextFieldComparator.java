package jarcul.comparator;

import org.apache.commons.lang3.StringUtils;

public class SynonymTextFieldComparator implements TextFieldComparator {
    private final static boolean DEFAULT_TO_LOWERCASE = true;
    private final static boolean DEFAULT_TO_REMOVE_ACCENTS = true;
    private final static boolean DEFAULT_TO_TRIM = true;

    private SynonymCollection synonymCollection;
    private final boolean toLowerCase;
    private final boolean removeAccents;
    private final boolean trimSpaces;

    public SynonymTextFieldComparator(SynonymCollection synonyms) {
        this(synonyms, DEFAULT_TO_LOWERCASE, DEFAULT_TO_REMOVE_ACCENTS, DEFAULT_TO_TRIM);
    }

    public SynonymTextFieldComparator(SynonymCollection synonyms, boolean toLowerCase, boolean removeAccents, boolean trimSpaces) {
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

        // if not equals, we transform the synonyms and try again

        bv = StringUtils.replaceEach(bv, synonymCollection.getTextToBeReplacedAsArray(), synonymCollection.getReplacementAsArray());

        return av.equals(bv) ? 1 : 0;
    }
}
