package jarcul.datasource;

import jarcul.*;
import jarcul.exception.DifferentRowTypeException;

import java.io.*;


public class CsvDataSource implements RowIterator {
    private final static String DEFAULT_SEPARATOR = ",";
    private RowSetup rowSetup;
    private String separator;
    private BufferedReader reader;
    private String lastLine = null;

    public CsvDataSource(RowSetup rowSetup, String path) throws IOException {
        this(rowSetup, new FileInputStream(path), DEFAULT_SEPARATOR);
    }

    public CsvDataSource(RowSetup rowSetup, String path, String separator) throws IOException {
        this(rowSetup, new FileInputStream(path), separator);
    }

    public CsvDataSource(RowSetup rowSetup, InputStream input) throws IOException {
        this(rowSetup, input, DEFAULT_SEPARATOR);
    }

    public CsvDataSource(RowSetup rowSetup, InputStream input, String separator) throws IOException {
        this.rowSetup = rowSetup;
        this.separator = separator;
        reader = new BufferedReader(new InputStreamReader(input));

        updateLastLine();
    }

    private void updateLastLine() throws IOException {
        lastLine = reader.readLine();

        if (lastLine == null) {
            reader.close();
        }
    }

    @Override
    public boolean hasNext() throws Exception {
        return lastLine != null;
    }

    @Override
    public Row next() throws Exception {
        Row row = new Row(rowSetup.getFieldsNumber());

        String[] fields = lastLine.split(separator);
        updateLastLine();

        if (fields.length != rowSetup.getFieldsNumber()) {
            throw new DifferentRowTypeException("Row sizes are different");
        }


        for (int j = 0; j < this.rowSetup.getFieldsNumber(); j++) {
            FieldSetup fieldSetup = rowSetup.getFieldSetup(j);

            if (BooleanFieldSetup.class.equals(fieldSetup.getClass())) {
                String rawField = fields[j];

                if ("".equals(rawField.trim())) {
                    row.addNullField();
                } else {
                    try {
                        row.addField(Boolean.parseBoolean(rawField));
                    } catch (Exception e) {
                        throw new DifferentRowTypeException("Field types are different", e);
                    }
                }
            } else if (TextFieldSetup.class.equals(fieldSetup.getClass())) {
                String rawField = fields[j];

                if ("".equals(rawField.trim())) {
                    row.addNullField();
                } else {
                    row.addField(rawField);
                }
            } else if (NumberFieldSetup.class.equals(fieldSetup.getClass())) {
                String rawField = fields[j];

                if ("".equals(rawField.trim())) {
                    row.addNullField();
                } else {
                    try {
                        row.addField(Double.parseDouble(rawField));
                    } catch (Exception e) {
                        throw new DifferentRowTypeException("Field types are different", e);
                    }
                }
            }
        }

        return row;
    }
}
