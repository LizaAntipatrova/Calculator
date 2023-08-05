import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import validator.AbstractTokenTransformer;
import validator.LowerCaseTokenTransformer;
import validator.SpaceTokenTransformer;

import java.util.List;

public class TestChainValidators {

    @Test
    void whenStartValidatorChainThenWork() {
        var exm = List.of("A", "a", " ", "B", " ", " ");
        var result = AbstractTokenTransformer.chain(
                new SpaceTokenTransformer(), new LowerCaseTokenTransformer()
        ).evaluate(exm);
        Assertions.assertEquals(List.of("a", "a", "b"), result);

    }
}
