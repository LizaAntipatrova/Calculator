public class CalculatorFactory {

    public static AbstractCalculatorFactory getInstance(CalculatorType type) {
        AbstractCalculatorFactory calculator = null;
        if (type == CalculatorType.SIMPLE) {
            calculator = new SimpleCalculator();
        }
        return calculator;
    }

}
