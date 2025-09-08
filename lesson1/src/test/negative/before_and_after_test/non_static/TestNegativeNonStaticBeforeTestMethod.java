package test.negative.before_and_after_test.non_static;

import annotations.BeforeTest;

public class TestNegativeNonStaticBeforeTestMethod {

    @BeforeTest
    public void setUp() {
        System.out.println("Before test");
    }
}
