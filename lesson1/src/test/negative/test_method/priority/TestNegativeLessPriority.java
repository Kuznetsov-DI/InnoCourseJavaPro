package test.negative.test_method.priority;

import annotations.Test;

public class TestNegativeLessPriority {


    @Test(priority = 0)
    public void test() {
        System.out.println("Test, 0 priority");
    }
}
