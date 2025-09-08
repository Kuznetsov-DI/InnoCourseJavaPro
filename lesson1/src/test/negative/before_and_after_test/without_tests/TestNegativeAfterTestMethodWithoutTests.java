package test.negative.before_and_after_test.without_tests;

import annotations.AfterTest;

public class TestNegativeAfterTestMethodWithoutTests {

    @AfterTest
    public static void tearDown() {
        System.out.println("After test");
    }
}
