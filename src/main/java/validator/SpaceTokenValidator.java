package validator;

import java.util.List;
import java.util.stream.Collectors;

public class SpaceTokenValidator extends AbstractTokenValidator {
    @Override
    public List<String> validate(List<String> tokens) {
        return tokens.stream()
                .filter((token) -> !token.equals(" "))
                .collect(Collectors.toList());
    }
}
