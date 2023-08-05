package chainer;

public interface Chainable<T, V> {

    void setNext(T next);

    V evaluate(V collection);
}
