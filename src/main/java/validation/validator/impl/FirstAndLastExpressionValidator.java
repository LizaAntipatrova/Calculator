package validation.validator.impl;

import constant.AllowedSymbolConstant;
import validation.exeption.SyntaxException;
import validation.validator.AbstractExpressionValidator;

import java.util.List;

public class FirstAndLastExpressionValidator extends AbstractExpressionValidator {
    @Override
    protected List<String> validate(List<String> tokens) {
        if (AllowedSymbolConstant.ALLOWED_OPERATORS.contains(tokens.get(0)) ||
                AllowedSymbolConstant.ALLOWED_OPERATORS.contains(tokens.get(tokens.size() - 1))) {
            throw new SyntaxException();
        }
        return tokens;
    }
}
