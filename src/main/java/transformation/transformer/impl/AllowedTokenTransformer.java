package transformation.transformer.impl;

import constant.AllowedSymbolConstant;
import transformation.transformer.AbstractTokenTransformer;

import java.util.List;
import java.util.stream.Collectors;

public class AllowedTokenTransformer extends AbstractTokenTransformer {
    @Override
    protected List<String> transform(List<String> tokens) {
        return tokens.stream()
                .filter((token) -> (Character.isDigit(token.toCharArray()[0]) ||
                        AllowedSymbolConstant.ALLOWED_SYMBOLS.contains(token)))
                .collect(Collectors.toList());

    }
}
