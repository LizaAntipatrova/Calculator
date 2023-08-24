package validation.validator.impl;

import constant.AllowedSymbolConstant;
import constant.PerformedOperation;
import validation.exeption.BracketSyntaxException;
import validation.validator.AbstractExpressionValidator;

import java.util.List;

public class BracketAndOperatorExpressionValidator extends AbstractExpressionValidator {

    @Override
    protected List<String> validate(List<String> tokens) {
        for (int i = 0; i < tokens.size() - 1; i++) {
            if (tokens.get(i).equals(AllowedSymbolConstant.OPENED_BRACKET) &&
                    PerformedOperation.SUBTRACTION.equals(tokens.get(i + 1))
                    && !tokens.get(i + 2).equals(AllowedSymbolConstant.CLOSED_BRACKET)) {
                continue;
            }
            if (tokens.get(i).equals(AllowedSymbolConstant.OPENED_BRACKET) &&
                    AllowedSymbolConstant.ALLOWED_OPERATORS.contains(tokens.get(i + 1))) {
                throw new BracketSyntaxException();
            }
            if (tokens.get(i + 1).equals(AllowedSymbolConstant.CLOSED_BRACKET) &&
                    AllowedSymbolConstant.ALLOWED_OPERATORS.contains(tokens.get(i))) {
                throw new BracketSyntaxException();
            }
        }
        return tokens;
    }
}
