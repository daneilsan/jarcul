package jarcul;

import jarcul.datasource.RowIterator;
import static java.lang.Math.abs;

import java.util.*;

public final class RowComparator {
    private final static Comparator<ComparisonRowResult> descendingPonderatedRowComparator;
    private final static int ARRAY_LIST_MAX_ITEMS = Integer.MAX_VALUE - 8;

    private final RowSetup rowSetup;

    static {
        descendingPonderatedRowComparator = (a, b) -> {
            if (a.points() > b.points()) {
                return -1;
            } else if (b.points() > a.points()) {
                return 1;
            } else {
                return 0;
            }
        };
    }

    public RowComparator(RowSetup rowSetup) {
        this.rowSetup = rowSetup;
    }

    public final List<ComparisonRowResult> compare(RowIterator rowIterator, Row row) throws Exception {
        return compare(rowIterator, row, ARRAY_LIST_MAX_ITEMS);
    }

    public final List<ComparisonRowResult> compare(RowIterator rowIterator, Row row, int limit) throws Exception {
        // todo check row is valid (same as especified in rowsetup)

        if (limit > ARRAY_LIST_MAX_ITEMS || limit < 1) {
            throw new IllegalAccessException(String.format("limit value must be between 1 and %d", ARRAY_LIST_MAX_ITEMS));
        }

        List<ComparisonRowResult> result = new ArrayList<>();;

        while (rowIterator.hasNext()) {
            Row currentRow = rowIterator.next();

            ComparisonRowResult rowResult = new ComparisonRowResult(rowSetup.getFieldsNumber());

            boolean anyMandatoryFieldWithZeroPoints = false;

            for (int j = 0; j < rowSetup.getFieldsNumber(); j++) {
                FieldSetup fs = rowSetup.getFieldSetup(j);

                double fieldPoints = fs.comparator().compare(row.getField(j), currentRow.getField(j));

                if (fs.isMandatory() && fieldPoints == 0.0) {
                    anyMandatoryFieldWithZeroPoints = true;
                }

                rowResult.addFieldResult(j, new ComparisonFieldResult(currentRow.getField(j), fieldPoints * fs.weight()));
            }

            // if any of the fields in the row is a discarding field, the whole points are reset to 0

            if (anyMandatoryFieldWithZeroPoints) {
                rowResult.resetPoints();
            }

            /*
                If there is no limit in the list size, we add the elements unsorted. at the end they will be sorted

                But, if there is a limit set, the insertion must be at the right position. For that we have to
                search fot the list index where the recently calculated rowResult.points have a preceding rowResult
                with more points and the index found must have less points than the one we are going to insert

             */

            if (limit == ARRAY_LIST_MAX_ITEMS) {
                result.add(rowResult);
            } else {
                int suggestedInsertionIndex = Collections.binarySearch(result, rowResult, descendingPonderatedRowComparator);
                int insertionIndex = suggestedInsertionIndex >= 0 ? suggestedInsertionIndex : abs(suggestedInsertionIndex + 1);

                result.add(insertionIndex, rowResult);

                if (result.size() > limit) {
                    result.remove(result.size() - 1);
                }
            }
        }


        /*
         If there is no limit, we will sort the results just once, after all the calculations
         Else, the collection is already sorted
          */

        if (limit == ARRAY_LIST_MAX_ITEMS) {
            Collections.sort(result,  descendingPonderatedRowComparator);
        }


        return result;
    }
}
