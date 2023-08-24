import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AbstractCalculatorFactory calculator = CalculatorFactory.getInstance(CalculatorType.SIMPLE);
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (!line.equals("end")) {
            try {
                System.out.println(calculator.process(line));
            } catch (ArithmeticException e1) {
                System.out.println("Cannot be divided by zero");
            } catch (RuntimeException e2) {
                System.out.println(e2.getMessage());
            }
            line = scanner.nextLine();

        }


        scanner.close();
    }

}

