package org.roman.my_lambda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyLambdaTest {

    @Test
    void testMyLambda() {

        MyLambda<String> myLambda1 = (a, b) -> a + b;
        MyLambda<Integer> myLambda2 = (a, b) -> a + b;

        String sumString = myLambda1.getSum("abc", "def");
        Integer sumInt = myLambda2.getSum(1, 1);

        assertEquals("abcdef", sumString);
        assertEquals(2, sumInt);
    }
}
