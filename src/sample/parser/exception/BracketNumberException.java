package sample.parser.exception;

public class BracketNumberException extends Exception{

    public BracketNumberException(String message) {
        super(message);
    }

    public BracketNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public BracketNumberException(Throwable cause) {
        super(cause);
    }
}
