package transformation.transformer;

import java.util.List;

public abstract class AbstractTokenTransformer {
    protected AbstractTokenTransformer nextTransformer;

    public static AbstractTokenTransformer chain(AbstractTokenTransformer first, AbstractTokenTransformer... other) {
        AbstractTokenTransformer head = first;
        for (AbstractTokenTransformer transformer : other) {
            head.nextTransformer = transformer;
            head = transformer;
        }
        return first;
    }

    protected abstract List<String> transform(List<String> tokens);

    public List<String> evaluate(List<String> tokens) {
        return checkNextTransformer(transform(tokens));
    }

    private List<String> checkNextTransformer(List<String> tokens) {
        if (nextTransformer == null) {
            return tokens;
        } else {
            return nextTransformer.evaluate(tokens);
        }
    }

}
