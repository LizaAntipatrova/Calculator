package validation.factory;

import validation.validator.AbstractExpressionValidator;
import validation.validator.impl.BracketAndOperatorExpressionValidator;
import validation.validator.impl.BracketExpressionValidator;
import validation.validator.impl.FirstAndLastExpressionValidator;
import validation.validator.impl.MultipleOperatorExpressionValidator;

import java.util.Objects;

public class ExpressionValidatorFactory {

    public static AbstractExpressionValidator createExpressionValidator(ExpressionValidatorType type) {
        AbstractExpressionValidator validator = null;
        if (Objects.requireNonNull(type) == ExpressionValidatorType.SIMPLE) {
            validator = AbstractExpressionValidator.chain(
                    new BracketExpressionValidator(),
                    new MultipleOperatorExpressionValidator(),
                    new FirstAndLastExpressionValidator(),
                    new BracketAndOperatorExpressionValidator()
            );
        }
        return validator;
    }

    public static AbstractExpressionValidator createSimpleExpressionValidator() {
        return createExpressionValidator(ExpressionValidatorType.SIMPLE);
    }

}
