package test.negative.before_and_after_test.more_one_methods.parent;

import annotations.BeforeTest;

public class TestNegativeTwoBeforeTestParentClass {

    @BeforeTest
    public static void setUp() {
        System.out.println("First before test");
    }

    @BeforeTest
    public static void setUp2() {
        System.out.println("Second before test");
    }
}
