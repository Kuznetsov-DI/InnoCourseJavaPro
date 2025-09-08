package test.negative.before_and_after_test.without_tests;

import annotations.BeforeTest;

public class TestNegativeBeforeTestMethodWithoutTests {

    @BeforeTest
    public static void setUp() {
        System.out.println("Before test");
    }
}
