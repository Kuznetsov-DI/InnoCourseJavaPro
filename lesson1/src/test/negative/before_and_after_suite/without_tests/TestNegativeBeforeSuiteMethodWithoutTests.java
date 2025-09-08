package test.negative.before_and_after_suite.without_tests;

import annotations.BeforeSuite;

public class TestNegativeBeforeSuiteMethodWithoutTests {

    @BeforeSuite
    public static void setUp() {
        System.out.println("Before suite");
    }
}
