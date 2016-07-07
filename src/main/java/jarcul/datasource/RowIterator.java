package jarcul.datasource;

import jarcul.Row;

public interface RowIterator {
    boolean hasNext() throws Exception;
    Row next() throws Exception;
}
