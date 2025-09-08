package test.negative.before_and_after_suite.non_static;

import annotations.BeforeSuite;

public class TestNegativeNonStaticBeforeSuiteMethod {

    @BeforeSuite
    public void setUp() {
        System.out.println("Before suite");
    }
}
