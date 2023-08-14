package parser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PerformedOperation {

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
