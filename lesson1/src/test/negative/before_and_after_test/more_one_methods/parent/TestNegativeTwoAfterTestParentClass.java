package test.negative.before_and_after_test.more_one_methods.parent;

import annotations.AfterTest;

public class TestNegativeTwoAfterTestParentClass {

    @AfterTest
    public static void tearDown() {
        System.out.println("First after test");
    }

    @AfterTest
    public static void tearDown2() {
        System.out.println("Second after test");
    }
}
