package jarcul.comparator;

import org.apache.commons.lang3.StringUtils;

public class SameTextFieldComparator implements TextFieldComparator {
    private final static boolean DEFAULT_TO_LOWERCASE = false;
    private final static boolean DEFAULT_TO_REMOVE_ACCENTS = false;
    private final static boolean DEFAULT_TO_TRIM = false;
    private final static boolean DEFAULT_EMPTY_EQUALS_NULL = true;

    private final boolean toLowerCase;
    private final boolean removeAccents;
    private final boolean trimSpaces;
    private final boolean emptyEqualsNull;

    public SameTextFieldComparator() {
        this(DEFAULT_TO_LOWERCASE, DEFAULT_TO_REMOVE_ACCENTS, DEFAULT_TO_TRIM, DEFAULT_EMPTY_EQUALS_NULL);
    }

    public SameTextFieldComparator(boolean toLowerCase, boolean removeAccents, boolean trimSpaces, boolean emptyEqualsNull) {
        this.toLowerCase = toLowerCase;
        this.removeAccents = removeAccents;
        this.trimSpaces = trimSpaces;
        this.emptyEqualsNull = emptyEqualsNull;
    }

    @Override
    public double compare(Object a, Object b) {
        if (a == null && b == null) return 1;

        if (!emptyEqualsNull) {
            if (a == null || b == null) return 0;
        }

        String av = (String)a;
        String bv = (String)b;


        if (emptyEqualsNull && ((av == null && "".equals(bv)) || (bv == null && "".equals(av)))) {
            return 1.0;
        }

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

        return av.equals(bv) ? 1 : 0;
    }
}
