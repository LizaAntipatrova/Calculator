package parser;

import constant.AllowedSymbolConstant;

import java.util.List;

public class RecursionParse implements Parsable {

    private final List<String> expression;
    private int NumberOfElement = 0;

    public RecursionParse(List<String> expression) {
        this.expression = expression;
    }

    private boolean checkBound() {
        return NumberOfElement < expression.size();
    }

    private double parseNumber() {
        StringBuilder numberString = new StringBuilder();
        while (checkBound() && Character.isDigit(expression.get(NumberOfElement).toCharArray()[0])) {
            numberString.append(expression.get(NumberOfElement));
            NumberOfElement++;
        }
        return Double.parseDouble(numberString.toString());

    }

    private double parseFirstPriority() {
        double result = parseNumber();
        double operand;
        while (checkBound() && PerformedOperation.FIRST_PRIORITY_OPERATION.contains(expression.get(NumberOfElement))) {
            String operation = expression.get(NumberOfElement);
            NumberOfElement++;
            if (expression.get(NumberOfElement).equals(AllowedSymbolConstant.OPENED_BRACKET)) {
                operand = parsingExpression();
            } else {
                operand = parseNumber();
            }
            result = PerformedOperation.calculate(operation, result, operand);
        }
        return result;
    }

    private double parseSecondPriority() {
        double result = parseFirstPriority();
        double operand;
        while (checkBound() && PerformedOperation.SECOND_PRIORITY_OPERATION.contains(expression.get(NumberOfElement))) {
            String operation = expression.get(NumberOfElement);
            NumberOfElement++;
            if (expression.get(NumberOfElement).equals(AllowedSymbolConstant.OPENED_BRACKET)) {
                operand = parsingExpression();
            } else {
                operand = parseFirstPriority();
            }
            result = PerformedOperation.calculate(operation, result, operand);
        }
        return result;
    }


    @Override
    public double parsingExpression() {
        String operation;
        if (expression.get(NumberOfElement).equals(AllowedSymbolConstant.OPENED_BRACKET)) {
            NumberOfElement++;          // (
            double resultExpression1 = parsingExpression();
            operation = expression.get(NumberOfElement);
            double resultExpression2 = parsingExpression();
            NumberOfElement++;          // )
            return PerformedOperation.calculate(operation, resultExpression1, resultExpression2);
        } else {
            return parseSecondPriority();
        }

    }


}
