package validator.exeption;

public class OperatorClashException extends RuntimeException {

    public OperatorClashException() {
        super("Multiple operators in a row");
    }
}
