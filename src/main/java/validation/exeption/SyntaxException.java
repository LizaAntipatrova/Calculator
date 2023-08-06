package validation.exeption;

public class SyntaxException extends RuntimeException {
    public SyntaxException() {
        super("Incorrect syntax");
    }
}
