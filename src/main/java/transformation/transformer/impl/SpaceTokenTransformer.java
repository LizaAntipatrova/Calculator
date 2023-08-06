package transformation.transformer.impl;

import transformation.transformer.AbstractTokenTransformer;

import java.util.List;
import java.util.stream.Collectors;

public class SpaceTokenTransformer extends AbstractTokenTransformer {
    @Override
    public List<String> transform(List<String> tokens) {
        return tokens.stream()
                .filter((token) -> !token.equals(" "))
                .collect(Collectors.toList());
    }
}
