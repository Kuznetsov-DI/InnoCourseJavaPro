package test.positive.with_parents_class;

import annotations.Test;
import test.positive.with_parents_class.parents.ParentClassWithoutBeforeAndAfterMethods;

public class TestWithParentWithoutBeforeAndAfterMethodsButHaveTwoParents extends ParentClassWithoutBeforeAndAfterMethods {

    @Test
    public void test1() {
        System.out.println("First test, default priority (second in output)");
    }

    @Test(priority = 10)
    public void test2() {
        System.out.println("Second test, 10 priority (third in output)");
    }
}
