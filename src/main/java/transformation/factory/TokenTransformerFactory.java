package transformation.factory;

import transformation.transformer.AbstractTokenTransformer;
import transformation.transformer.impl.AllowedTokenTransformer;
import transformation.transformer.impl.LowerCaseTokenTransformer;
import transformation.transformer.impl.SpaceTokenTransformer;

import java.util.Objects;

public class TokenTransformerFactory {

    public static AbstractTokenTransformer createTokenTransformer(TokenTransformerType type) {
        AbstractTokenTransformer transformer = null;
        if (Objects.requireNonNull(type) == TokenTransformerType.SIMPLE) {
            transformer = AbstractTokenTransformer.chain(
                    new SpaceTokenTransformer(),
                    new LowerCaseTokenTransformer(),
                    new AllowedTokenTransformer()
            );
        }
        return transformer;
    }

    public static AbstractTokenTransformer createSimpleTokenTransformer() {
        return createTokenTransformer(TokenTransformerType.SIMPLE);
    }

}
