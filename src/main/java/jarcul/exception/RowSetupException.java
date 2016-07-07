package jarcul.exception;

/**
 * Created by daniel.garcia on 22/06/2016.
 */
public class RowSetupException extends Exception {
    public RowSetupException(String message, Throwable t) {
        super(message, t);
    }

    public RowSetupException(String message) {
        super(message);
    }
}
