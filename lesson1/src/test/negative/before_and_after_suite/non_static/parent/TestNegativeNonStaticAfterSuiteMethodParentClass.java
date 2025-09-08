package test.negative.before_and_after_suite.non_static.parent;

import annotations.AfterSuite;

public class TestNegativeNonStaticAfterSuiteMethodParentClass {

    @AfterSuite
    public void tearDown() {
        System.out.println("After suite");
    }
}
