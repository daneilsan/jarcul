package jarcul.exception;

/**
 * Created by daniel.garcia on 22/06/2016.
 */
public class DifferentRowTypeException extends Exception {
    public DifferentRowTypeException(String message, Throwable t) {
        super(message, t);
    }

    public DifferentRowTypeException(String message) {
        super(message);
    }
}
