package jarcul.comparator;

import info.debatty.java.stringsimilarity.JaroWinkler;
import org.apache.commons.lang3.StringUtils;

public class SimilarTextFieldComparator implements TextFieldComparator {
    private final static JaroWinkler comparator = new JaroWinkler();

    private final static boolean DEFAULT_TO_LOWERCASE = true;
    private final static boolean DEFAULT_TO_REMOVE_ACCENTS = true;
    private final static boolean DEFAULT_TO_TRIM = true;

    private final boolean toLowerCase;
    private final boolean removeAccents;
    private final boolean trimSpaces;

    public SimilarTextFieldComparator() {
        this(DEFAULT_TO_LOWERCASE, DEFAULT_TO_REMOVE_ACCENTS, DEFAULT_TO_TRIM);
    }

    public SimilarTextFieldComparator(boolean toLowerCase, boolean removeAccents, boolean trimSpaces) {
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

        return comparator.similarity(av, bv);
    }
}
