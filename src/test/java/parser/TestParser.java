package parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parser.imp.GraphParser;

import java.util.List;

public class TestParser {

    @Test
    void testGraphParse() {
        var exm = List.of("1", "+", "3", "*", "4", "-", "6", "*", "(", "2", "0", "+", "5", "/", "5", ")", "+", "4");
        Parsable parser = new GraphParser();
        double result = parser.parsingExpression(exm);
        double resultExm = 1d + 3d * 4d - 6d * (20d + 1.0) + 4d;
        Assertions.assertEquals(resultExm, result);
    }

    @Test
    void testGraphParseManyBrackets() {
        var exm = List.of("(", "1", "+", "(", "1", "4", "+", "(", "-", "6", ")", ")", "-", "(", "2", "0", "+", "5", "/", "10", ")", "+", "1", ")");
        Parsable parser = new GraphParser();
        double result = parser.parsingExpression(exm);
        double resultExm = 1d + 14d - 6d - (20d + 5d / 10d) + 1d;
        Assertions.assertEquals(resultExm, result);
    }

    @Test
    void testGraphParseNegativeNumbers() {
        var exm = List.of("1", "+", "3", "*", "(", "-", "2", "+", "1", ")");
        Parsable parser = new GraphParser();
        double result = parser.parsingExpression(exm);
        Assertions.assertEquals(-2d, result);
    }

    @Test
    void testGraphParseDivisionByZero() {
        var exm = List.of("1", "/", "(", "1", "-", "1", ")");
        Parsable parser = new GraphParser();
        Assertions.assertThrows(ArithmeticException.class, () -> parser.parsingExpression(exm));
    }

    @Test
    void testGraphParseDoubleNumber() {
        var exm = List.of("1", ".", "1", "1", "+", "1", ".", "2");
        Parsable parser = new GraphParser();
        Assertions.assertEquals(2.31, parser.parsingExpression(exm));
    }


}
