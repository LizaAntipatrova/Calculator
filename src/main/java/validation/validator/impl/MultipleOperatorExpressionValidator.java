package validation.validator.impl;

import constant.AllowedSymbolConstant;
import validation.exeption.OperatorClashException;
import validation.validator.AbstractExpressionValidator;

import java.util.List;

public class MultipleOperatorExpressionValidator extends AbstractExpressionValidator {
    @Override
    protected List<String> validate(List<String> tokens) {
        int countOfOperator = 0;
        for (String token : tokens) {
            if (AllowedSymbolConstant.ALLOWED_OPERATORS.contains(token)) {
                countOfOperator++;
            } else if (countOfOperator != 0) {
                countOfOperator = 0;
            }
            if (countOfOperator == 2) {
                throw new OperatorClashException();
            }
        }
        return tokens;
    }
}
