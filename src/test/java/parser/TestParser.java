package parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestParser {

    @Test
    void testRecursionParse() {
        var exm = List.of("5", "*", "4", "/", "2", "+", "(", "2", "+", "1", "0", ")", "*", "3");
        Parsable parser = new RecursionParse(exm);
        double result = parser.parsingExpression();
        Assertions.assertEquals(46, result);

    }
}
