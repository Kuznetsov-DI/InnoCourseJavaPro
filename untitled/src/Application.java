import runner.TestRunner;
import test.negative.before_and_after_suite.more_one_methods.TestNegativeTwoAfterSuite;
import test.negative.before_and_after_suite.more_one_methods.TestNegativeTwoAfterSuiteInParent;
import test.negative.before_and_after_suite.more_one_methods.TestNegativeTwoBeforeSuite;
import test.negative.before_and_after_suite.more_one_methods.TestNegativeTwoBeforeSuiteInParent;
import test.negative.before_and_after_suite.non_static.TestNegativeNonStaticAfterSuiteMethod;
import test.negative.before_and_after_suite.non_static.TestNegativeNonStaticAfterSuiteMethodInParent;
import test.negative.before_and_after_suite.non_static.TestNegativeNonStaticBeforeSuiteMethod;
import test.negative.before_and_after_suite.non_static.TestNegativeNonStaticBeforeSuiteMethodInParent;
import test.negative.before_and_after_suite.without_tests.TestNegativeAfterSuiteMethodWithoutTests;
import test.negative.before_and_after_suite.without_tests.TestNegativeBeforeSuiteMethodWithoutTests;
import test.negative.before_and_after_test.more_one_methods.TestNegativeTwoAfterTest;
import test.negative.before_and_after_test.more_one_methods.TestNegativeTwoAfterTestInParent;
import test.negative.before_and_after_test.more_one_methods.TestNegativeTwoBeforeTest;
import test.negative.before_and_after_test.more_one_methods.TestNegativeTwoBeforeTestInParent;
import test.negative.before_and_after_test.non_static.TestNegativeNonStaticAfterTestMethod;
import test.negative.before_and_after_test.non_static.TestNegativeNonStaticAfterTestMethodInParent;
import test.negative.before_and_after_test.non_static.TestNegativeNonStaticBeforeTestMethod;
import test.negative.before_and_after_test.non_static.TestNegativeNonStaticBeforeTestMethodInParent;
import test.negative.before_and_after_test.without_tests.TestNegativeAfterTestMethodWithoutTests;
import test.negative.before_and_after_test.without_tests.TestNegativeBeforeTestMethodWithoutTests;
import test.negative.test_method.priority.TestNegativeLessPriority;
import test.negative.test_method.priority.TestNegativeMorePriority;
import test.negative.test_method.static_test.TestNegativeStaticTestMethod;
import test.positive.csv.TestCsv;
import test.positive.with_parents_class.TestWithParentWithBeforeAndAfterMethods;
import test.positive.with_parents_class.TestWithParentWithoutBeforeAndAfterMethods;
import test.positive.with_parents_class.TestWithParentWithoutBeforeAndAfterMethodsButHaveTwoParents;
import test.positive.with_parents_class.TestWithParentWithoutBeforeAndAfterMethodsButHaveTwoParentsWithBeforeAbdAfter;
import test.positive.without_parents_class.TestPositive;
import test.positive.without_parents_class.TestPositiveOnlyTestMethods;

public class Application {

    public static void main(String[] args) {

        // Позитивный тест (без родительского класса, все методы есть)
//        TestRunner.runTests(TestPositive.class);

        // Позитивный тест, только тестовые методы (без before и after методов)
//        TestRunner.runTests(TestPositiveOnlyBeforeSuitTestMethods.class);

        // Позитивный тест, есть родитеский класс (методы Before и After есть и в родительском и в тестововом классе)
//        TestRunner.runTests(TestWithParentWithBeforeAndAfterMethods.class);

        // Позитивный тест, есть родитеский класс (методы Before и After только в родительском)
//        TestRunner.runTests(TestWithParentWithoutBeforeAndAfterMethods.class);

        // Позитивный тест, есть родитеский класс (методы Before и After только в прародительском)
//        TestRunner.runTests(TestWithParentWithoutBeforeAndAfterMethodsButHaveTwoParents.class);

        // Позитивный тест, двойное наследование (методы Before и After только в родительском и прародительском)
//        TestRunner.runTests(TestWithParentWithoutBeforeAndAfterMethodsButHaveTwoParentsWithBeforeAbdAfter.class);

        // Негативный тест, приоритет ниже 1
//        TestRunner.runTests(TestNegativeLessPriority.class);

        // Негативный тест, приоритет выше 10
//        TestRunner.runTests(TestNegativeMorePriority.class);

        // Негативный тест, не статический метод AfterSuite
//        TestRunner.runTests(TestNegativeNonStaticAfterSuiteMethod.class);

        // Негативный тест, не статический метод AfterTest
//        TestRunner.runTests(TestNegativeNonStaticAfterTestMethod.class);

        // Негативный тест, не статический метод BeforeSuite
//        TestRunner.runTests(TestNegativeNonStaticBeforeSuiteMethod.class);

        // Негативный тест, не статический метод BeforeSuite
//        TestRunner.runTests(TestNegativeNonStaticBeforeTestMethod.class);

        // Негативный тест, не статический метод AfterSuite в родительском классе
//        TestRunner.runTests(TestNegativeNonStaticAfterSuiteMethodInParent.class);

        // Негативный тест, не статический метод AfterTest в родительском классе
//        TestRunner.runTests(TestNegativeNonStaticAfterTestMethodInParent.class);

        // Негативный тест, не статический метод BeforeSuite в родительском классе
//        TestRunner.runTests(TestNegativeNonStaticBeforeSuiteMethodInParent.class);

        // Негативный тест, не статический метод BeforeTest в родительском классе
//        TestRunner.runTests(TestNegativeNonStaticBeforeTestMethodInParent.class);

        // Негативный тест, статический метод Test
//        TestRunner.runTests(TestNegativeStaticTestMethod.class);

        // Негативный тест, более одного AfterSuite метода
//        TestRunner.runTests(TestNegativeTwoAfterSuite.class);

        // Негативный тест, более одного AfterTest метода
//        TestRunner.runTests(TestNegativeTwoAfterTest.class);

        // Негативный тест, более одного BeforeSuite метода
//        TestRunner.runTests(TestNegativeTwoBeforeSuite.class);

        // Негативный тест, более одного BeforeSuite метода
//        TestRunner.runTests(TestNegativeTwoBeforeTest.class);

        // Негативный тест, более одного AfterSuite метода в родительском классе
//        TestRunner.runTests(TestNegativeTwoAfterSuiteInParent.class);

        // Негативный тест, более одного AfterTest метода в родительском классе
//        TestRunner.runTests(TestNegativeTwoAfterTestInParent.class);

        // Негативный тест, более одного BeforeSuite метода в родительском классе
//        TestRunner.runTests(TestNegativeTwoBeforeSuiteInParent.class);

        // Негативный тест, более одного BeforeTest метода в родительском классе
//        TestRunner.runTests(TestNegativeTwoBeforeTestInParent.class);

        // Негативный тест, AfterSuite без тестовых методов
//        TestRunner.runTests(TestNegativeAfterSuiteMethodWithoutTests.class);

        // Негативный тест, AfterTest без тестовых методов
//        TestRunner.runTests(TestNegativeAfterTestMethodWithoutTests.class);

        // Негативный тест, BeforeSuite без тестовых методов
//        TestRunner.runTests(TestNegativeBeforeSuiteMethodWithoutTests.class);

        // Негативный тест, BeforeTest без тестовых методов
//        TestRunner.runTests(TestNegativeBeforeTestMethodWithoutTests.class);

        // Позитивный тест csv
        TestRunner.runTests(TestCsv.class);
    }
}
