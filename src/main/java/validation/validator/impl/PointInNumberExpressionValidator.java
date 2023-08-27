package validation.validator.impl;

import constant.AllowedSymbolConstant;
import validation.exeption.SyntaxException;
import validation.validator.AbstractExpressionValidator;

import java.util.List;

public class PointInNumberExpressionValidator extends AbstractExpressionValidator {
    @Override
    protected List<String> validate(List<String> tokens) {

        if (tokens.get(0).equals(AllowedSymbolConstant.POINT)) {
            throw new SyntaxException();
        } else {
            int indexOfPoint = tokens.indexOf(AllowedSymbolConstant.POINT);
            for (int i = 0; i < tokens.size(); i++) {
                if (tokens.get(i).equals(AllowedSymbolConstant.POINT)
                        && !(Character.isDigit(tokens.get(i - 1).toCharArray()[0]))) {
                    throw new SyntaxException();
                }
                indexOfPoint = tokens.subList(indexOfPoint + 1, tokens.size()).indexOf(AllowedSymbolConstant.POINT);
            }
        }
        return tokens;
    }
}
