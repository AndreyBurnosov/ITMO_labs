package exceptions;

public class WrongValueException extends RuntimeException {
    public WrongValueException (String msg) {
        super(msg);
    }
}
