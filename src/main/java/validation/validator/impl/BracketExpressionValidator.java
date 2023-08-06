package validation.validator.impl;

import validation.exeption.BracketSyntaxException;
import validation.validator.AbstractExpressionValidator;

import java.util.List;

public class BracketExpressionValidator extends AbstractExpressionValidator {
    @Override
    protected List<String> validate(List<String> tokens) {
        int bracketCounter = 0;
        for (String token : tokens) {
            if (token.equals("(")) {
                bracketCounter++;
            } else if (token.equals(")")) {
                bracketCounter--;
            }
            if (bracketCounter < 0) {
                throw new BracketSyntaxException();
            }
        }
        if (bracketCounter != 0) {
            throw new BracketSyntaxException();
        }
        return tokens;
    }
}
