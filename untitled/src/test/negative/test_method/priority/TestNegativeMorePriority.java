package test.negative.test_method.priority;

import annotations.Test;

public class TestNegativeMorePriority {


    @Test(priority = 11)
    public void test() {
        System.out.println("Test, 11 priority");
    }
}
