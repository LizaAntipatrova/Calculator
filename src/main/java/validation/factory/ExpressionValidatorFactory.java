package validation.factory;

import validation.validator.AbstractExpressionValidator;
import validation.validator.impl.*;

import java.util.Objects;

public class ExpressionValidatorFactory {

    public static AbstractExpressionValidator createExpressionValidator(ExpressionValidatorType type) {
        AbstractExpressionValidator validator = null;
        if (Objects.requireNonNull(type) == ExpressionValidatorType.SIMPLE) {
            validator = AbstractExpressionValidator.chain(
                    new BracketExpressionValidator(),
                    new MultipleOperatorExpressionValidator(),
                    new FirstAndLastExpressionValidator(),
                    new BracketAndOperatorExpressionValidator(),
                    new PointInNumberExpressionValidator()
            );
        }
        return validator;
    }

    public static AbstractExpressionValidator createSimpleExpressionValidator() {
        return createExpressionValidator(ExpressionValidatorType.SIMPLE);
    }

}
