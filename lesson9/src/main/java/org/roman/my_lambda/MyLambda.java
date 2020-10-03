package org.roman.my_lambda;

@FunctionalInterface
public interface MyLambda<T> {
    T getSum(T lhs, T rhs);
}
