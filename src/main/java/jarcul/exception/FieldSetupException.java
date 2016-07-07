package jarcul.exception;

/**
 * Created by daniel.garcia on 22/06/2016.
 */
public class FieldSetupException extends Exception {
    public FieldSetupException(String message, Throwable t) {
        super(message, t);
    }

    public FieldSetupException(String message) {
        super(message);
    }
}
