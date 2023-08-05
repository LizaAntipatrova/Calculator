package validator;

import java.util.List;

public abstract class AbstractTokenValidator {
    protected AbstractTokenValidator nextValidator;

    public static AbstractTokenValidator chain(AbstractTokenValidator first, AbstractTokenValidator... other) {
        AbstractTokenValidator head = first;
        for (AbstractTokenValidator validator : other) {
            head.nextValidator = validator;
            head = validator;
        }
        return first;
    }

    protected abstract List<String> validate(List<String> tokens);

    public List<String> evaluate(List<String> tokens) {
        return checkNextValidator(validate(tokens));
    }

    private List<String> checkNextValidator(List<String> tokens) {
        if (nextValidator == null) {
            return tokens;
        } else {
            return nextValidator.evaluate(tokens);
        }
    }

}
