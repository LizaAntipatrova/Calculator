package validation.exeption;

public class BracketSyntaxException extends RuntimeException {

    public BracketSyntaxException() {
        super("Irregular placement or number of brackets");
    }
}
