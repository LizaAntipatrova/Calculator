package parser;

import constant.AllowedSymbolConstant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphParser implements Parsable {

    private final List<String> expression;

    private final int NUMBER_PRIORITY = 0;
    private final int BRACKET_PRIORITY = -100;

    public GraphParser(List<String> expression) {
        this.expression = expression;
    }


    private double parseNumber(List<String> stringsWithDigit) {
        return Integer.parseInt(String.join("", stringsWithDigit));
    }

    private List<Integer> createListPriority() {

        int addPriority = 1;
        int subPriority = 2;
        int multPriority = 3;
        List<Integer> priority = new ArrayList<>();

        for (String currentToken : expression) {
            if (PerformedOperation.SUBTRACTION.equals(currentToken)
                    && priority.get(priority.size() - 1) == BRACKET_PRIORITY) {
                priority.add(NUMBER_PRIORITY);

            } else if (PerformedOperation.ADDITION.equals(currentToken)) {
                priority.add(addPriority);

            } else if (PerformedOperation.SUBTRACTION.equals(currentToken)) {
                priority.add(subPriority);

            } else if (PerformedOperation.FIRST_PRIORITY_OPERATION.contains(currentToken)) {
                priority.add(multPriority);

            } else if (currentToken.equals(AllowedSymbolConstant.OPENED_BRACKET)) {
                addPriority += 3;
                subPriority += 3;
                multPriority += 3;
                priority.add(BRACKET_PRIORITY);

            } else if (currentToken.equals(AllowedSymbolConstant.CLOSED_BRACKET)) {
                addPriority -= 3;
                subPriority -= 3;
                multPriority -= 3;
                priority.add(BRACKET_PRIORITY);

            } else {
                priority.add(NUMBER_PRIORITY);
            }
        }
        return priority;
    }


    private void createMathTree(List<Integer> priority, MathSyntaxTree.Node currentNode, int startIndex, int endIndex) {

        int indexOfOperator;
        int maxPriority = Collections.max(priority);

        if (maxPriority > 0) {
            for (int i = 1; i <= Collections.max(priority); i++) {
                indexOfOperator = priority.indexOf(i);

                if (indexOfOperator != -1) {
                    currentNode.fillingNodeOperator(expression.get(startIndex + indexOfOperator));
                    createMathTree(
                            priority.subList(0, indexOfOperator),
                            currentNode.getLeftChild(),
                            startIndex,
                            startIndex + indexOfOperator
                    );
                    createMathTree(
                            priority.subList(indexOfOperator + 1, priority.size()),
                            currentNode.getRightChild(),
                            startIndex + indexOfOperator + 1,
                            endIndex
                    );
                    break;
                }
            }
        } else {
            if (priority.get(priority.size() - 1) == BRACKET_PRIORITY) {
                endIndex -= 1;
            }
            if (priority.get(0) == BRACKET_PRIORITY) {
                startIndex += 1;
            }
            currentNode.setNumber(parseNumber(expression.subList(startIndex, endIndex)));
        }

    }

    private double mathTreeTraversal(MathSyntaxTree.Node currentNode) {
        if (currentNode.isOperation()) {
            if (currentNode.getOperator().equals(PerformedOperation.DIVISION)) {
                double divisor = mathTreeTraversal(currentNode.getRightChild());
                if (divisor == 0) {
                    throw new ArithmeticException();
                } else {
                    return PerformedOperation.calculate(
                            currentNode.getOperator(),
                            mathTreeTraversal(currentNode.getLeftChild()),
                            divisor);
                }
            } else {
                return PerformedOperation.calculate(
                        currentNode.getOperator(),
                        mathTreeTraversal(currentNode.getLeftChild()),
                        mathTreeTraversal(currentNode.getRightChild()));
            }
        } else {
            return currentNode.getNumber();
        }
    }


    @Override
    public double parsingExpression() {
        MathSyntaxTree mathSyntaxTree = new MathSyntaxTree();
        createMathTree(createListPriority(), mathSyntaxTree.getTopTree(), 0, expression.size());
        return mathTreeTraversal(mathSyntaxTree.getTopTree());
    }
}

