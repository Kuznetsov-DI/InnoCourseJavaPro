package test.positive.without_parents_class;

import annotations.*;

public class TestPositive {

    @BeforeSuite
    public static void setUp() {
        System.out.println("Before suite");
    }

    @BeforeTest
    public static void setUpTest(){
        System.out.println("Before test");
    }

    @AfterTest
    public static void tearDownTest(){
        System.out.println("After test");
    }

    @AfterSuite
    public static void tearDown() {
        System.out.println("After suite");
    }

    @Test
    public void test1() {
        System.out.println("First test, default priority (second in output)");
    }

    @Test(priority = 10)
    public void test2() {
        System.out.println("Second test, 10 priority (third in output)");
    }

    @Test(priority = 1)
    public void test3() {
        System.out.println("Third test, 1 priority (first in output)");
    }
}
