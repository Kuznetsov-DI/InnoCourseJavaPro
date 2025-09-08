package test.negative.before_and_after_test.non_static.parent;

import annotations.BeforeTest;

public class TestNegativeNonStaticBeforeTestMethodParentClass {

    @BeforeTest
    public void setUp() {
        System.out.println("Before test");
    }
}
