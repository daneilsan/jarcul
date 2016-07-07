package jarcul.datasource;

import jarcul.Row;
import jarcul.RowSetup;
import jarcul.exception.DifferentRowTypeException;

import java.util.ArrayList;
import java.util.List;

import static jarcul.util.RowCheckerUtil.haveDifferentFieldNumbers;
import static jarcul.util.RowCheckerUtil.haveDifferentFieldTypes;

public class MemoryTableDataSource implements RowIterator {
    private final RowSetup rowSetup;
    private final List<Row> rowList;
    private int iteratorPosition = 0;

    public MemoryTableDataSource(RowSetup rowSetup) {
        this(rowSetup, 10000);
    }

    public MemoryTableDataSource(RowSetup rowSetup, int rowBuffer) {
        this.rowSetup = rowSetup;
        this.rowList = new ArrayList<>(rowBuffer);
    }

    public final MemoryTableDataSource addRow(Row row) throws DifferentRowTypeException {
        if (haveDifferentFieldNumbers(row, rowSetup)) {
            throw new DifferentRowTypeException("Row sizes are different");
        }

        if (haveDifferentFieldTypes(row, rowSetup)) {
            throw new DifferentRowTypeException("Field types are different");
        }

        this.rowList.add(row);

        return this;
    }

    @Override
    public boolean hasNext() {
        return iteratorPosition < rowList.size();
    }

    @Override
    public Row next() {
        return rowList.get(iteratorPosition++);
    }
}
