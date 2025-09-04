package test.negative.before_and_after_suite.more_one_methods;

import annotations.BeforeSuite;

public class TestNegativeTwoBeforeSuite {

    @BeforeSuite
    public static void setUp() {
        System.out.println("First before suite");
    }

    @BeforeSuite
    public static void setUp2() {
        System.out.println("Second before suite");
    }
}
