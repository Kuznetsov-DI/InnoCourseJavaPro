package test.negative.before_and_after_suite.more_one_methods;

import annotations.AfterSuite;

public class TestNegativeTwoAfterSuite {

    @AfterSuite
    public static void tearDown() {
        System.out.println("First after suite");
    }

    @AfterSuite
    public static void tearDown2() {
        System.out.println("Second after suite");
    }
}
