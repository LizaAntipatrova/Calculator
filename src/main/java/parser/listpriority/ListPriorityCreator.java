package parser.listpriority;

import constant.AllowedSymbolConstant;
import constant.PerformedOperation;

import java.util.ArrayList;
import java.util.List;

public class ListPriorityCreator {
    private final List<Integer> priority = new ArrayList<>();
    private int addPriority = 1;
    private int subPriority = 2;
    private int multPriority = 3;


    public List<Integer> getPriority() {
        return priority;
    }


    public List<Integer> createListPriority(List<String> expression) {
        for (String currentToken : expression) {
            if (PerformedOperation.SUBTRACTION.equals(currentToken)
                    && priority.get(priority.size() - 1) == PerformedOperation.OPENED_BRACKET_PRIORITY) {
                priority.add(PerformedOperation.NUMBER_PRIORITY);

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
                priority.add(PerformedOperation.OPENED_BRACKET_PRIORITY);

            } else if (currentToken.equals(AllowedSymbolConstant.CLOSED_BRACKET)) {
                addPriority -= 3;
                subPriority -= 3;
                multPriority -= 3;
                priority.add(PerformedOperation.CLOSED_BRACKET_PRIORITY);

            } else {
                priority.add(PerformedOperation.NUMBER_PRIORITY);
            }
        }
        return priority;
    }
}
