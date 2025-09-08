package test.negative.before_and_after_suite.non_static;

import annotations.AfterSuite;

public class TestNegativeNonStaticAfterSuiteMethod {

    @AfterSuite
    public void tearDown() {
        System.out.println("After suite");
    }
}
