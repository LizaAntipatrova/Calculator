import parser.Parsable;
import spliter.Splitable;
import transformation.transformer.AbstractTokenTransformer;
import validation.validator.AbstractExpressionValidator;

import java.util.List;

public abstract class AbstractCalculatorFactory {

    public abstract Splitable getSplitter();

    public abstract AbstractTokenTransformer getTransformer();

    public abstract AbstractExpressionValidator getValidator();

    public abstract Parsable getParser();

    public double process(String expression) {
        List<String> tokens = getTransformer().evaluate(getSplitter().split(expression));
        getValidator().evaluate(tokens);
        return getParser().parsingExpression(tokens);
    }

    public double processTransformed(String expression) {
        List<String> tokens = getSplitter().split(expression);
        getValidator().evaluate(tokens);
        return getParser().parsingExpression(tokens);
    }
}
