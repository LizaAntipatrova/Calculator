package parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestParser {

    @Test
    void testGraphParse() {
        var exm = List.of("1", "+", "3", "*", "4", "-", "6", "*", "(", "2", "0", "+", "5", "/", "5", ")", "+", "4");
        Parsable parser = new GraphParser(exm);
        double result = parser.parsingExpression();
        double resultExm = 1d + 3d * 4d - 6d * (20d + 1.0) + 4d;
        Assertions.assertEquals(resultExm, result);
    }

    @Test
    void testGraphParseNegativeNumbers() {
        var exm = List.of("1", "+", "3", "*", "(", "-", "2", ")");
        Parsable parser = new GraphParser(exm);
        double result = parser.parsingExpression();
        Assertions.assertEquals(-5d, result);
    }

}
