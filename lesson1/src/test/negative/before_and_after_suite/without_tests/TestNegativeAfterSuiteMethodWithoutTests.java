package test.negative.before_and_after_suite.without_tests;

import annotations.AfterSuite;

public class TestNegativeAfterSuiteMethodWithoutTests {

    @AfterSuite
    public static void tearDown() {
        System.out.println("After suite");
    }
}
