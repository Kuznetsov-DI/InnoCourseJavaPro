package test.positive.with_parents_class.parents;

import annotations.AfterSuite;
import annotations.AfterTest;
import annotations.BeforeSuite;
import annotations.BeforeTest;

public class ParentClassWithBeforeAndAfterMethodsOneMore extends ParentClassWithBeforeAndAfterMethods{

    @BeforeSuite
    public static void setUp() {
        System.out.println("Before suite first parent");
    }

    @BeforeTest
    public static void setUpTest() {
        System.out.println("Before test first parent");
    }

    @AfterTest
    public static void tearDownTest() {
        System.out.println("After test first parent");
    }

    @AfterSuite
    public static void tearDown() {
        System.out.println("After suite first parent");
    }
}
