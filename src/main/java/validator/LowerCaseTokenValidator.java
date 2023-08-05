package validator;

import java.util.List;
import java.util.stream.Collectors;

public class LowerCaseTokenValidator extends AbstractTokenValidator {
    @Override
    public List<String> validate(List<String> tokens) {
        return tokens.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }
}
