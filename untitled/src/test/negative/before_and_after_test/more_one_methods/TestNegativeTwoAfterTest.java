package test.negative.before_and_after_test.more_one_methods;

import annotations.AfterTest;
import annotations.Test;

public class TestNegativeTwoAfterTest {

    @AfterTest
    public void tearDown() {
        System.out.println("First after test");
    }

    @AfterTest
    public void tearDown2() {
        System.out.println("Second after test");
    }

    @Test
    void test(){
        System.out.println("My test");
    }
}
