package datasource;

import jarcul.*;
import jarcul.RowComparator;
import jarcul.datasource.SqlDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class SqlDataSourceTest {
    @Test
    public void demo() throws Exception {
        // this example works fine
        RowSetup rowSetup = new RowSetup(
                new NumberFieldSetup("id"),
                new TextFieldSetup("name")
        );


        Connection connection = DriverManager.getConnection("jdbc:h2:mem:");
        connection.createStatement().execute("create table user (id int unsigned, name varchar(255))");
        connection.createStatement().execute("insert into user (id, name) values (1, 'Pepe'), (2, 'Pedro')");

        SqlDataSource sqlDataSource = new SqlDataSource(rowSetup, connection, "select id, name from user");

        Row row = new Row(1, "Pedro");

        RowComparator rowComparator = new RowComparator(rowSetup);
        List<ComparisonRowResult> result = rowComparator.compare(sqlDataSource, row);

    }
}
