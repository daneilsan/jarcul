package datasource;

import jarcul.*;
import jarcul.RowComparator;
import jarcul.comparator.NoOpFieldComparator;
import jarcul.datasource.MemoryTableDataSource;
import jarcul.exception.DifferentRowTypeException;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class MemoryTableDataSourceTest {
    @Test
    public void addFields() throws Exception {
        // this example works fine
        RowSetup rowSetup = new RowSetup(
                new NumberFieldSetup("size"),
                new NumberFieldSetup("weight")
        );

        MemoryTableDataSource memoryTableDataSource = new MemoryTableDataSource(rowSetup);

        Row row = new Row(170, 60);

        memoryTableDataSource.addRow(row);
    }

    @Test(expected = DifferentRowTypeException.class)
    public void addWrongFieldNumberThrowsException() throws Exception {
        RowSetup rowSetup = new RowSetup(
                new NumberFieldSetup("size"),
                new NumberFieldSetup("weight")
        );

        MemoryTableDataSource memoryTableDataSource = new MemoryTableDataSource(rowSetup);

        Row row = new Row(170); // <-- only one field added. One remaining

        memoryTableDataSource.addRow(row); // DifferentRowTypeException
    }

    @Test(expected = DifferentRowTypeException.class)
    public void addWrongFieldTypeThrowsException() throws Exception {
        RowSetup rowSetup = new RowSetup(
                new NumberFieldSetup("size"),
                new NumberFieldSetup("weight")
        );

        MemoryTableDataSource memoryTableDataSource = new MemoryTableDataSource(rowSetup);

        Row row = new Row("some", "text"); // <-- adding strings instead of integer

        memoryTableDataSource.addRow(row); // DifferentRowTypeException
    }

    @Test
    public void checkRow() throws Exception {

        // create the row setup

        RowSetup rowSetup = new RowSetup(
                new NumberFieldSetup("height"),
                new TextFieldSetup("name")
        );

        // create the memorytable

        MemoryTableDataSource memoryTableDataSource = new MemoryTableDataSource(rowSetup);


        // populate the memory table

        memoryTableDataSource
                .addRow(new Row(170, "Pedro"))
                .addRow(new Row(145, "Pepe"))
                .addRow(new Row(120, "Paco"))
                .addRow(new Row(150, "José"))
                .addRow(new Row(180, "Juan"));

        // define the row to be tested against the memorytable

        Row searchRow = new Row(150, "Pepe");


        // create the calculator object

        RowComparator rowComparator = new RowComparator(rowSetup);


        // calculate the best matches

        List<ComparisonRowResult> results = rowComparator.compare(memoryTableDataSource, searchRow);
    }

    @Test
    public void checkRowWithSomeNulls() throws Exception {

        // create the row setup

        RowSetup rowSetup = new RowSetup(
                new TextFieldSetup("id", 1, new NoOpFieldComparator()),
                new NumberFieldSetup("height"),
                new TextFieldSetup("name")
        );

        // create and populate the memorytable

        MemoryTableDataSource memoryTableDataSource = new MemoryTableDataSource(rowSetup)
                .addRow(new Row("ABC", 170, "Pedro"))
                .addRow(new Row("DEF", 145, "Pepe"))
                .addRow(new Row("GHI", 120, "Paco"))
                .addRow(new Row("JKL", 150, "José"))
                .addRow(new Row("MNÑ", 180, "Juan"));

        // define the row to be tested against the memorytable

        Row searchRow = new Row(null, 150, "Pepito");


        // create the calculator object

        RowComparator rowComparator = new RowComparator(rowSetup);

        // calculate the best matches

        List<ComparisonRowResult> results = rowComparator.compare(memoryTableDataSource, searchRow);

        assertTrue("First element has more points", results.get(0).points() > results.get(results.size() - 1).points());
    }

    @Test
    public void checkRowWithSomeNullsWithLimitedResult() throws Exception {

        // create the row setup

        RowSetup rowSetup = new RowSetup(
                new TextFieldSetup("id", 1, new NoOpFieldComparator()),
                new NumberFieldSetup("height"),
                new TextFieldSetup("name")
        );

        // create and populate the memorytable

        MemoryTableDataSource memoryTableDataSource = new MemoryTableDataSource(rowSetup)
                .addRow(new Row("ABC", 170, "Pedro"))
                .addRow(new Row("DEF", 145, "Pepe"))
                .addRow(new Row("GHI", 120, "Paco"))
                .addRow(new Row("JKL", 150, "José"))
                .addRow(new Row("MNÑ", 180, "Juan"));

        // define the row to be tested against the memorytable

        Row searchRow = new Row(null, 150, "Pepito");


        // create the calculator object

        RowComparator rowComparator = new RowComparator(rowSetup);

        // calculate the best matches

        int limit = 3;
        List<ComparisonRowResult> results = rowComparator.compare(memoryTableDataSource, searchRow, limit);

        assertTrue("Results has limited elements", results.size() == limit);
        assertTrue("First element has more points", results.get(0).points() > results.get(results.size() - 1).points());
    }
}
