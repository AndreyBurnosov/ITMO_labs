package exceptions;

public class EmptyBottleException extends RuntimeException{
    public EmptyBottleException(String msg) {
        super(msg);
    }
}
