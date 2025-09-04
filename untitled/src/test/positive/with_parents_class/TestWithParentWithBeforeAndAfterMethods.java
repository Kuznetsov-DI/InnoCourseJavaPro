package test.positive.with_parents_class;

import annotations.*;
import test.positive.with_parents_class.parents.ParentClassWithBeforeAndAfterMethods;

public class TestWithParentWithBeforeAndAfterMethods extends ParentClassWithBeforeAndAfterMethods {

    @BeforeSuite
    public static void setUpForClass() {
        System.out.println("Before suite in test class");
    }

    @BeforeTest
    public static void setUpTest() {
        System.out.println("Before test in test class");
    }

    @AfterTest
    public static void tearDownTest() {
        System.out.println("After test in test class");
    }

    @AfterSuite
    public static void tearDownForClass() {
        System.out.println("After suite in test class");
    }

    @Test
    public void test1() {
        System.out.println("First test, default priority (second in output)");
    }

    @Test(priority = 10)
    public void test2() {
        System.out.println("Second test, 10 priority (third in output)");
    }
}
