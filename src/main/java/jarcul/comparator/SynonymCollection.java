package jarcul.comparator;

import java.util.*;

public class SynonymCollection {
    private Map<String, String> synonyms = new HashMap<>();

    // to gain performance, arrays are sorted when inserted
    private boolean arrayMustBeRecalculated = true;
    private String[] textToBeReplaced;
    private String[] replacement;

    public SynonymCollection addSynonym(String textToBeReplaced, String replacement) {

        synonyms.put(textToBeReplaced, replacement);
        arrayMustBeRecalculated = true;

        return this;
    }

    public String[] getTextToBeReplacedAsArray() {
        if (arrayMustBeRecalculated) {
            recalculateInternalArrays();
        }

        return this.textToBeReplaced;
    }

    public String[] getReplacementAsArray() {
        if (arrayMustBeRecalculated) {
            recalculateInternalArrays();
        }

        return this.replacement;
    }

    private synchronized void recalculateInternalArrays() {
        textToBeReplaced = new String[synonyms.size()];

        synonyms.keySet().toArray(textToBeReplaced);

        /*
            the strings must be sort from larger ones to small in order to avoid wrong substitutions like for example
            replacement = gasoline
            synonyms = benzin, benzina

            (benzin)a -> (gasoline)a   WRONG!!!

            Then we do

            synonyms = sort (benzina, benzin)
            synonyms = benzina, benzin

            (benzina) -> (gasoline)
        */

        Arrays.sort(textToBeReplaced, Collections.reverseOrder());

        replacement = new String[textToBeReplaced.length];

        // then we will retrieve each replacement and create an array
        for (int j = 0; j < textToBeReplaced.length; j++) {
            replacement[j] = synonyms.get(textToBeReplaced[j]);
        }

        arrayMustBeRecalculated = false;
    }
}
