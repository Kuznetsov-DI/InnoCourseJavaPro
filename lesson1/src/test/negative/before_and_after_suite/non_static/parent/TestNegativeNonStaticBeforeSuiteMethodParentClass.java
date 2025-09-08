package test.negative.before_and_after_suite.non_static.parent;

import annotations.BeforeSuite;

public class TestNegativeNonStaticBeforeSuiteMethodParentClass {

    @BeforeSuite
    public void setUp() {
        System.out.println("Before suite");
    }
}
