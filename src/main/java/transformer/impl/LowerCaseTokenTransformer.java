package transformer.impl;

import transformer.AbstractTokenTransformer;

import java.util.List;
import java.util.stream.Collectors;

public class LowerCaseTokenTransformer extends AbstractTokenTransformer {
    @Override
    public List<String> transform(List<String> tokens) {
        return tokens.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }
}
