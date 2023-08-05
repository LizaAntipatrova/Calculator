package validator;

import chainer.Chainable;


public abstract class AbstractChain<T extends Chainable<T, V>, V> {
    protected T nextValidator;

    protected abstract V validate(V tokens);


    public T chain(T first, T... other) {
        T head = first;
        for (T validator : other) {
            head.setNext(validator);
            head = validator;
        }
        return first;
    }

    public V evaluate(V tokens) {
        return checkNextValidator(validate(tokens));
    }

    private V checkNextValidator(V tokens) {
        if (nextValidator == null) {
            return tokens;
        } else {
            return nextValidator.evaluate(tokens);
        }
    }

}