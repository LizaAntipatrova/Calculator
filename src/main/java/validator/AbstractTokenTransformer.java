package validator;

import java.util.List;

public abstract class AbstractTokenTransformer {
    protected AbstractTokenTransformer nextTransformator;

    public static AbstractTokenTransformer chain(AbstractTokenTransformer first, AbstractTokenTransformer... other) {
        AbstractTokenTransformer head = first;
        for (AbstractTokenTransformer validator : other) {
            head.nextTransformator = validator;
            head = validator;
        }
        return first;
    }

    protected abstract List<String> transform(List<String> tokens);

    public List<String> evaluate(List<String> tokens) {
        return checkNextTransformator(transform(tokens));
    }

    private List<String> checkNextTransformator(List<String> tokens) {
        if (nextTransformator == null) {
            return tokens;
        } else {
            return nextTransformator.evaluate(tokens);
        }
    }

}
