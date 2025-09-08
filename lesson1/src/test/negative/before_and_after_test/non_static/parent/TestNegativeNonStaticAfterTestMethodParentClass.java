package test.negative.before_and_after_test.non_static.parent;

import annotations.AfterTest;

public class TestNegativeNonStaticAfterTestMethodParentClass {

    @AfterTest
    public void tearDown() {
        System.out.println("After test");
    }
}
