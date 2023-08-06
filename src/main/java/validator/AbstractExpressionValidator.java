package validator;


import java.util.List;


public abstract class AbstractExpressionValidator {
    protected AbstractExpressionValidator nextValidator;


    public static AbstractExpressionValidator chain(AbstractExpressionValidator first, AbstractExpressionValidator... other) {
        AbstractExpressionValidator head = first;
        for (AbstractExpressionValidator validator : other) {
            head.nextValidator = validator;
            head = validator;
        }
        return first;
    }

    protected abstract List<String> validate(List<String> tokens);

    public void evaluate(List<String> tokens) {
        checkNextValidator(validate(tokens));
    }

    private void checkNextValidator(List<String> tokens) {
        if (nextValidator != null) {
            nextValidator.evaluate(tokens);
        }
    }
}
