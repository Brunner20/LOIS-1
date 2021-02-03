package sample.parser.exception;

public class SymbolNotCorrectException extends Exception{

    public SymbolNotCorrectException(String message) {
        super(message);
    }

    public SymbolNotCorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public SymbolNotCorrectException(Throwable cause) {
        super(cause);
    }
}
