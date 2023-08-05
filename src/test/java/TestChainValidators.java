import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import validator.AbstractTokenValidator;
import validator.LowerCaseTokenValidator;
import validator.SpaceTokenValidator;

import java.util.List;

public class TestChainValidators {

    @Test
    void whenStartValidatorChainThenWork() {
        var exm = List.of("A", "a", " ", "B", " ", " ");
        var result = AbstractTokenValidator.chain(
                new SpaceTokenValidator(), new LowerCaseTokenValidator()
        ).evaluate(exm);
        Assertions.assertEquals(List.of("a", "a", "b"), result);

    }
}
