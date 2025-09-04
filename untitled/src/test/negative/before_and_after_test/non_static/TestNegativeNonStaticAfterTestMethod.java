package test.negative.before_and_after_test.non_static;

import annotations.AfterTest;

public class TestNegativeNonStaticAfterTestMethod {

    @AfterTest
    public void tearDown() {
        System.out.println("After test");
    }
}
