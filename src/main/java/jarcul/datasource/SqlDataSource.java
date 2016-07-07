package jarcul.datasource;

import jarcul.*;

import java.sql.*;

public class SqlDataSource implements RowIterator {
    private final static int SELECT_ROW_BUFFER_SIZE = 1000;

    private RowSetup rowSetup;
    private ResultSet result;
    private PreparedStatement preparedStatement;
    private Connection connection;
    private boolean mustCloseConnection = false;

    public SqlDataSource(RowSetup rowSetup, String jdbcConString, String query) throws SQLException {
        this(rowSetup, jdbcConString, query, SELECT_ROW_BUFFER_SIZE);
    }

    public SqlDataSource(RowSetup rowSetup, String jdbcConString, String query, int bufferSize) throws SQLException {
        this.rowSetup = rowSetup;
        connection = DriverManager.getConnection(jdbcConString);
        preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setFetchSize(bufferSize);
        result = preparedStatement.executeQuery();

        mustCloseConnection = true;
    }

    public SqlDataSource(RowSetup rowSetup, Connection connection, String query) throws SQLException {
        this(rowSetup, connection, query, SELECT_ROW_BUFFER_SIZE);
    }

    public SqlDataSource(RowSetup rowSetup, Connection connection, String query, int bufferSize) throws SQLException {
        this(rowSetup, connection.prepareStatement(query), bufferSize);
    }

    public SqlDataSource(RowSetup rowSetup, PreparedStatement preparedStatement) throws  SQLException {
        this(rowSetup, preparedStatement, SELECT_ROW_BUFFER_SIZE);
    }

    public SqlDataSource(RowSetup rowSetup, PreparedStatement preparedStatement, int bufferSize) throws SQLException {
        this.rowSetup = rowSetup;
        this.preparedStatement = preparedStatement;
        this.preparedStatement.setFetchSize(bufferSize);
        result = preparedStatement.executeQuery();
    }

    @Override
    public boolean hasNext() throws SQLException {
        boolean r = result.next();

        if (!r) {
            result.close();
            preparedStatement.close();

            if (mustCloseConnection) {
                connection.close();
            }
        }

        return r;
    }

    @Override
    public Row next() throws SQLException {
        Row row = new Row(this.rowSetup.getFieldsNumber());

            for (int j = 0; j < this.rowSetup.getFieldsNumber(); j++) {
                FieldSetup fieldSetup = rowSetup.getFieldSetup(j);

                if (BooleanFieldSetup.class.equals(fieldSetup.getClass())) {
                    boolean field = result.getBoolean(j + 1);

                    if (result.wasNull()) {
                        row.addNullField();
                    } else {
                        row.addField(field);
                    }
                } else if (TextFieldSetup.class.equals(fieldSetup.getClass())) {
                    String field = result.getString(j + 1);

                    if (result.wasNull()) {
                        row.addNullField();
                    } else {
                        row.addField(field);
                    }
                } else if (NumberFieldSetup.class.equals(fieldSetup.getClass())) {
                    double field = result.getDouble(j + 1);

                    if (result.wasNull()) {
                        row.addNullField();
                    } else {
                        row.addField(field);
                    }
                }
            }

        return row;
    }
}
