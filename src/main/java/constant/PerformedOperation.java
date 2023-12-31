package constant;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PerformedOperation {

    public static final String ADDITION = "+";
    public static final String SUBTRACTION = "-";
    public static final String DIVISION = "/";
    public static final int NUMBER_PRIORITY = 0;
    public static final int OPENED_BRACKET_PRIORITY = -100;
    public static final int CLOSED_BRACKET_PRIORITY = -200;

    public static final Set<String> FIRST_PRIORITY_OPERATION = new HashSet<>(
            List.of(
                    "*",
                    "/"
            ));
    public static final Set<String> SECOND_PRIORITY_OPERATION = new HashSet<>(
            List.of(
                    "+",
                    "-"
            ));


    public static double calculate(String symbolOfOperating, double firstOperand, double secondOperand) {
        double result = 0;
        switch (symbolOfOperating) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "/":
                result = firstOperand / secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
        }
        return result;
    }
}
