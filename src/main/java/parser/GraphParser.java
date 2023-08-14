package parser;

import constant.AllowedSymbolConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GraphParser implements Parsable {

    private final List<String> expression;

    public GraphParser(List<String> expression) {
        this.expression = expression;
    }

    /**
     * Добавляет элемент в дерево математический операций
     * ищет центральную вершину, записывает в вершину, создает детей этой вершины
     * и отправляет в рекурсию
     *
     * @param currTop            - текущая вершина
     * @param indexesOfOperation - список индексов операций
     */

    private void addInMathOperationGraph(MathSyntaxTree.Node currTop, List<Integer> indexesOfOperation) {

        int topIndex = indexesOfOperation.size() / 2 + 1;

        currTop.setValue(expression.get(indexesOfOperation.get(topIndex)));
        currTop.setOperation(false);

        if (indexesOfOperation.size() > 2) {
            currTop.setLeftChild(new MathSyntaxTree.Node(null, null, null, false));
            currTop.setRightChild(new MathSyntaxTree.Node(null, null, null, false));
            addInMathOperationGraph(
                    currTop.getLeftChild(),
                    indexesOfOperation.subList(0, topIndex)
            );
            addInMathOperationGraph(
                    currTop.getRightChild(),
                    indexesOfOperation.subList(topIndex + 1, indexesOfOperation.size())
            );
        } else if (indexesOfOperation.size() == 2) {
            currTop.setLeftChild(new MathSyntaxTree.Node(null, null, null, false));
            addInMathOperationGraph(
                    currTop.getLeftChild(),
                    indexesOfOperation.subList(0, topIndex)
            );
        }
    }

    /**
     * создает дерево математический операций
     * выбирает приоритетные операции, ищет индексы всех приоритетных в данный момент операций,addInMathOperationGraph
     * записывает их в список, игнорируя скобки. Далее добавляет в дерево с помощью
     *
     * @param partExpressions - часть выражения над которой проводятся операции
     * @param priority        - приоритет(1,2,3 - скобки)
     */
    private void createMathOperationGraph(List<String> partExpressions, int priority) {

        if (priority == 1 || priority == 2) {
            Set<String> requiredOperations;
            if (priority == 1) {
                requiredOperations = PerformedOperation.FIRST_PRIORITY_OPERATION;
            } else {
                requiredOperations = PerformedOperation.SECOND_PRIORITY_OPERATION;
            }

            int counterBracket = 0;
            List<Integer> indexesOfOperation = new ArrayList<>();
            String currentElement;
            MathSyntaxTree mathSyntaxTree = new MathSyntaxTree();

            for (int i = 0; i < partExpressions.size(); i++) {
                currentElement = partExpressions.get(i);
                if (currentElement.equals(AllowedSymbolConstant.OPENED_BRACKET)) {
                    counterBracket++;
                } else if (currentElement.equals(AllowedSymbolConstant.CLOSED_BRACKET)) {
                    counterBracket--;
                } else if (counterBracket == 0 && requiredOperations.contains(currentElement)) {
                    indexesOfOperation.add(i);
                }
            }
        }

    }


    @Override
    public double parsingExpression() {
        return 0;
    }
}

