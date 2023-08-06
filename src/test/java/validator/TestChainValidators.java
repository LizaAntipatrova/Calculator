package validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import validator.exeption.BracketSyntaxException;
import validator.exeption.OperatorClashException;

import java.util.List;

public class TestChainValidators {
    @Test
    void testAbstractExpressionValidatorOK() {
        var exm = List.of("(", "(", "1", "+", "2", ")", ")");

        Assertions.assertDoesNotThrow(() -> AbstractExpressionValidator.chain(
                new BracketExpressionValidator(), new MultipleOperatorExpressionValidator()
        ).evaluate(exm));
    }

    @Test
    void testAbstractExpressionValidatorNotOKBracket() {
        var exm = List.of("(", "(", "1", "+", "2", ")");

        Assertions.assertThrows(BracketSyntaxException.class, () -> AbstractExpressionValidator.chain(
                new BracketExpressionValidator(), new MultipleOperatorExpressionValidator()
        ).evaluate(exm));
    }

    @Test
    void testAbstractExpressionValidatorNotOKOperators() {
        var exm = List.of("(", "(", "1", "+", "-", ")", ")");

        Assertions.assertThrows(OperatorClashException.class, () -> AbstractExpressionValidator.chain(
                new BracketExpressionValidator(), new MultipleOperatorExpressionValidator()
        ).evaluate(exm));
    }

    @Test
    void testBracketExpressionValidatorOK() {
        var exm = List.of("(", "(", ")", "(", ")", ")");

        Assertions.assertDoesNotThrow(() -> AbstractExpressionValidator.chain(
                new BracketExpressionValidator()
        ).evaluate(exm));
    }

    @Test
    void testBracketExpressionValidatorNotOK() {
        var exm = List.of(")", "(", "(");

        Assertions.assertThrows(BracketSyntaxException.class, () -> AbstractExpressionValidator.chain(
                new BracketExpressionValidator()
        ).evaluate(exm));
    }

    @Test
    void testMultipleOperatorExpressionValidatorOK() {
        var exm = List.of("2", "+", "3", "*", "2");

        Assertions.assertDoesNotThrow(() -> AbstractExpressionValidator.chain(
                new MultipleOperatorExpressionValidator()
        ).evaluate(exm));
    }

    @Test
    void testMultipleOperatorExpressionValidatorNotOK() {
        var exm = List.of("+", "-");

        Assertions.assertThrows(OperatorClashException.class, () -> AbstractExpressionValidator.chain(
                new MultipleOperatorExpressionValidator()
        ).evaluate(exm));
    }
}
