package datasource;

import jarcul.*;
import jarcul.RowComparator;
import jarcul.datasource.CsvDataSource;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

public class CsvDataSourceTest {
    @Test
    public void demo() throws Exception {
        RowSetup rowSetup = new RowSetup(
                new NumberFieldSetup("id"),
                new TextFieldSetup("name")
        );

        String content = "1,pepe\n2,juan";
        ByteArrayInputStream input = new ByteArrayInputStream(content.getBytes());
        CsvDataSource dataSource = new CsvDataSource(rowSetup, input);

        RowComparator rowComparator = new RowComparator(rowSetup);

        Row row = new Row(1, "Pedro");

        List<ComparisonRowResult> result = rowComparator.compare(dataSource, row);
    }

}
