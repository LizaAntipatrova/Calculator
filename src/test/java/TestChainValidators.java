import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import validator.AbstractTokenTransformer;
import validator.AllowedTokenTransformer;
import validator.LowerCaseTokenTransformer;
import validator.SpaceTokenTransformer;

import java.util.List;

public class TestChainValidators {

    @Test
    void testAbstractTokenTransformer() {
        var exm = List.of("A", "a", " ", "2", " ", " ");
        var result = AbstractTokenTransformer.chain(
                new SpaceTokenTransformer(), new LowerCaseTokenTransformer(),
                new AllowedTokenTransformer()
        ).evaluate(exm);
        Assertions.assertEquals(List.of("2"), result);

    }

    @Test
    void testSpaceTokenTransformer() {
        var exm = List.of("A", " ", "2", " ", " ");
        var result = AbstractTokenTransformer.chain(
                new SpaceTokenTransformer()
        ).evaluate(exm);
        Assertions.assertEquals(List.of("A", "2"), result);

    }

    @Test
    void testLowerCaseTokenTransformer() {
        var exm = List.of("A", "a", " ", "2");
        var result = AbstractTokenTransformer.chain(
                new LowerCaseTokenTransformer()
        ).evaluate(exm);
        Assertions.assertEquals(List.of("a", "a", " ", "2"), result);

    }

    @Test
    void testAllowedTokenTransformer() {
        var exm = List.of("A", "+", "-", "=", "*", "/", "1");
        var result = AbstractTokenTransformer.chain(
                new AllowedTokenTransformer()
        ).evaluate(exm);
        Assertions.assertEquals(List.of("+", "-", "=", "*", "/", "1"), result);
    }
}
