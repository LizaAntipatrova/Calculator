package validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import validation.exeption.BracketSyntaxException;
import validation.exeption.OperatorClashException;
import validation.exeption.SyntaxException;
import validation.factory.ExpressionValidatorFactory;
import validation.validator.AbstractExpressionValidator;
import validation.validator.impl.*;

import java.util.List;

public class TestChainValidators {
    @Test
    void testAbstractExpressionValidatorOK() {
        var exm = List.of("(", "(", "1", "+", "2", ")", ")");

        Assertions.assertDoesNotThrow(
                () -> ExpressionValidatorFactory.createSimpleExpressionValidator().evaluate(exm)
        );
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

    @Test
    void testBracketAndOperatorExpressionValidatorOK() {
        var exm = List.of("*", "(", "-", "5", ")", "-");

        Assertions.assertDoesNotThrow(() -> AbstractExpressionValidator.chain(
                new BracketAndOperatorExpressionValidator()
        ).evaluate(exm));
    }

    @Test
    void testBracketAndOperatorExpressionValidatorNotOK() {
        var exm = List.of("(", "/", "+", ")");

        Assertions.assertThrows(BracketSyntaxException.class, () -> AbstractExpressionValidator.chain(
                new BracketAndOperatorExpressionValidator()
        ).evaluate(exm));
    }

    @Test
    void testFirstAndLastExpressionValidatorOK() {
        var exm = List.of("2", "+", "3", "*", "2");

        Assertions.assertDoesNotThrow(() -> AbstractExpressionValidator.chain(
                new FirstAndLastExpressionValidator()
        ).evaluate(exm));
    }

    @Test
    void testFirstAndLastExpressionValidatorNotOK() {
        var exm = List.of("+", "-");

        Assertions.assertThrows(SyntaxException.class, () -> AbstractExpressionValidator.chain(
                new FirstAndLastExpressionValidator()
        ).evaluate(exm));
    }

    @Test
    void testPointInNumberExpressionValidatorOK() {
        var exm = List.of("2", ".", "3", "*", "0", ".", "4");

        Assertions.assertDoesNotThrow(() -> AbstractExpressionValidator.chain(
                new PointInNumberExpressionValidator()
        ).evaluate(exm));
    }

    @Test
    void testPointInNumberExpressionValidatorNotOK() {
        var exm = List.of(".", "0", "3");

        Assertions.assertThrows(SyntaxException.class, () -> AbstractExpressionValidator.chain(
                new PointInNumberExpressionValidator()
        ).evaluate(exm));
    }

    @Test
    void testPointInNumberExpressionValidatorPointBeforeNumberNotOK() {
        var exm = List.of("+", ".", "0", "3");

        Assertions.assertThrows(SyntaxException.class, () -> AbstractExpressionValidator.chain(
                new PointInNumberExpressionValidator()
        ).evaluate(exm));
    }

    @Test
    void testPointInNumberExpressionValidatorPointAfterNumberOK() {
        var exm = List.of("0", "3", ".", "+");

        Assertions.assertDoesNotThrow(() -> AbstractExpressionValidator.chain(
                new PointInNumberExpressionValidator()
        ).evaluate(exm));
    }
}
