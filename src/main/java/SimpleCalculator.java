import parser.Parsable;
import parser.factory.ParseFactory;
import spliter.Splitable;
import spliter.impl.SimpleSpliterImpl;
import transformation.factory.TokenTransformerFactory;
import transformation.transformer.AbstractTokenTransformer;
import validation.factory.ExpressionValidatorFactory;
import validation.validator.AbstractExpressionValidator;

public class SimpleCalculator extends AbstractCalculatorFactory {
    @Override
    public Splitable getSplitter() {
        return new SimpleSpliterImpl();
    }

    @Override
    public AbstractTokenTransformer getTransformer() {
        return TokenTransformerFactory.createSimpleTokenTransformer();
    }

    @Override
    public AbstractExpressionValidator getValidator() {
        return ExpressionValidatorFactory.createSimpleExpressionValidator();
    }

    @Override
    public Parsable getParser() {
        return ParseFactory.createSimpleParser();
    }
}
