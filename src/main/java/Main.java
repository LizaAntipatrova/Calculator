import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String CONNECT_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private static final String CONNECT_USER = "postgres";
    private static final String CONNECT_PASSWORD = "mysecretpassword";
    private static final String SQL_SELECT = "SELECT my_result FROM mycash WHERE my_expression =  ?";
    private static final String SQL_INSERT = "INSERT INTO mycash(my_expression, my_result) VALUES (?, ?)";
    private static final String END_PROGRAM_COMMAND = "end";

    public static void main(String[] args) {
        AbstractCalculatorFactory calculator = CalculatorFactory.getInstance(CalculatorType.SIMPLE);

        try (Scanner scanner = new Scanner(System.in);
             Connection connection = DriverManager.getConnection(CONNECT_URL, CONNECT_USER, CONNECT_PASSWORD);
             PreparedStatement statementSelect = connection.prepareStatement(SQL_SELECT);
             PreparedStatement statmentInsert = connection.prepareStatement(SQL_INSERT)) {

            String line = transform(scanner.nextLine(), calculator);


            while (!line.equals(END_PROGRAM_COMMAND)) {
                statementSelect.setString(1, line);
                try (ResultSet result = statementSelect.executeQuery()) {
                    if (result.next()) {
                        System.out.println(result.getString(1));
                    } else {
                        double expressionResult = calculator.processTransformed(line);
                        System.out.println(expressionResult);
                        statmentInsert.setString(1, line);
                        statmentInsert.setDouble(2, expressionResult);
                        statmentInsert.executeUpdate();
                    }
                } catch (ArithmeticException e1) {
                    System.out.println("Cannot be divided by zero");
                } catch (RuntimeException e2) {
                    System.out.println(e2.getMessage());
                }
                line = transform(scanner.nextLine(), calculator);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String transform(String expression, AbstractCalculatorFactory factory) {
        return expression.equals(END_PROGRAM_COMMAND)
                ? expression
                : String.join("", factory.getTransformer().evaluate(factory.getSplitter().split(expression)));

    }
}

