package test.positive.csv;

import annotations.*;

public class TestCsv {

//    @Test
//    @CsvSource(argsForMethod = "10, Java, 20.5, true")
//    public void test1(int a, String b, double c, boolean d) {
//        System.out.println("Test with csv, a = " + a + " b = " + b + " c = " + c + "d = " + d);
//    }

//    @Test(priority = 1)
//    public void test2() {
//        System.out.println("Test without csv");
//    }
//
//    @Test(priority = 1)
//    @CsvSource(argsForMethod = "")
//    public void test3() {
//        System.out.println("Test with empty");
//    }
//

//    @Test
//    @CsvSource(argsForMethod = "10, Java, 20.5, true")
//    public void test4(boolean a, double c, String b) {
//        System.out.println("Test with different count params, a = " + a + " b = " + b + " c = " + c);
//    }

//    @Test
//    @CsvSource(argsForMethod = "10, Java, 20.5, true")
//    public void test5(boolean a, double c, String b, int d) {
//        System.out.println("Test with csv different signature, a = " + a + " b = " + b + " c = " + c + "d = " + d);
//    }

    @Test
    @CsvSource(argsForMethod = "28.5")
    public void test6(float c) {
        System.out.println("Unsupported parameters");
    }
}
