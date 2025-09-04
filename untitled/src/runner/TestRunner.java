package runner;

import annotations.*;
import exception.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс для запуска тестов
 */
public class TestRunner {

    /**
     * Основной метод запуска тестов в классе
     *
     * @param c класс, в котором запускаем тесты
     */
    public static void runTests(Class<?> c) {
        AtomicInteger beforeSuitCount = new AtomicInteger();
        AtomicInteger afterSuitCount = new AtomicInteger();
        AtomicInteger beforeTestCount = new AtomicInteger();
        AtomicInteger afterTestCount = new AtomicInteger();
        Method beforeSuiteMethod = null;
        Method afterSuiteMethod = null;
        Method beforeTestMethod = null;
        Method afterTestMethod = null;
        Map<Method, Integer> testMethods = new HashMap<>();
        Object instance;

        // Получение инстанса для дальнейшего запуска методов с тестами
        try {
            instance = c.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new CouldNotCreateInstanceException("Could not create instance of " + c.getSimpleName());
        }

        // Цикл для обработки всех методов в классе, в котором выполняются все проверки и получение нужных методов
        for (Method method : c.getDeclaredMethods()) {

            // BeforeSuite проверки аннотации
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                beforeSuiteMethod = validateBeforeAndAfterMethods(method, beforeSuitCount, c, "BeforeSuite");
            }

            // AfterSuite проверки аннотации
            if (method.isAnnotationPresent(AfterSuite.class)) {
                afterSuiteMethod = validateBeforeAndAfterMethods(method, afterSuitCount, c, "AfterSuite");
            }

            // BeforeTest проверки аннотации
            if (method.isAnnotationPresent(BeforeTest.class)) {
                beforeTestMethod = validateBeforeAndAfterMethods(method, beforeTestCount, c, "BeforeTest");
            }

            // AfterTest проверки аннотации
            if (method.isAnnotationPresent(AfterTest.class)) {
                afterTestMethod = validateBeforeAndAfterMethods(method, afterTestCount, c, "AfterTest");
            }

            //Test проверки аннотации
            if (method.isAnnotationPresent(Test.class)) {
                if (Modifier.isStatic(method.getModifiers())) {
                    throw new WrongModifiersMethodException("Method - " + c.getSimpleName() + "." + method.getName() +
                            " must be not a static for Test annotation");
                }

                Test testAnnotation = method.getAnnotation(Test.class);
                if (testAnnotation.priority() < 1 || testAnnotation.priority() > 10) {
                    throw new IllegalArgumentException(" For method - " + c.getSimpleName() + "." + method.getName() +
                            " priority can't be less 1 and more 10, now priority is " + testAnnotation.priority());
                }

                testMethods.put(method, testAnnotation.priority());
            }
        }

        //Вызов метода BeforeSuite, если он есть в классе и если есть тестовые методы (Если метода BeforeSuit нет в классе метод ищется у родительского класса)
        if (beforeSuiteMethod != null && !testMethods.isEmpty()) {
            invokeBeforeAndAfterMethods(beforeSuiteMethod, c);
        } else if (!testMethods.isEmpty()) {
            invokeParentsBeforeAndAfterIfHave(c, BeforeSuite.class);
        } else {
            throw new InvokingMethodException("You have no tests in " + c.getSimpleName() + " class");
        }

        //Вызов тестовых методов в порядке приоритетов
        if (!testMethods.isEmpty()) {
            var prioritizedMethods = testMethods.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).toList();

            for (Method method : prioritizedMethods) {

                //Вызов метода BeforeTest, если он есть в классе и если есть тестовые методы (Если метода BeforeTest нет в классе метод ищется у родительского класса)
                if (beforeTestMethod != null && !testMethods.isEmpty()) {
                    invokeBeforeAndAfterMethods(beforeTestMethod, c);
                } else if (!testMethods.isEmpty()) {
                    invokeParentsBeforeAndAfterIfHave(c, BeforeTest.class);
                } else {
                    throw new InvokingMethodException("You have no tests in " + c.getSimpleName() + " class");
                }

                try {
                    // Вызов тестового метода, если присутствует аннотация CsvSource
                    if (method.isAnnotationPresent(CsvSource.class)) {
                        CsvSource csvSource = method.getAnnotation(CsvSource.class);
                        if (!csvSource.argsForMethod().isEmpty()) {
                            var parametersString = csvSource.argsForMethod();
                            var parametersArray = parametersString.split(",");
                            var paramTypes = method.getParameterTypes();
                            if (paramTypes.length != parametersArray.length) {
                                throw new ParameterCountDoesNotMatchException("Parameters count doesn't match in method signature - " + paramTypes.length +
                                        " in CsvSource annotation - " + parametersArray.length + " with string - " + parametersString);
                            }
                            try {
                                Object[] convertedParams = new Object[parametersArray.length];
                                for (int i = 0; i < parametersArray.length; i++) {
                                    convertedParams[i] = convertParameter(parametersArray[i], paramTypes[i]);
                                }

                                method.setAccessible(true);
                                method.invoke(instance, convertedParams);
                            } catch (Exception e) {
                                throw new InvokingMethodException("Error invoking method " + c.getSimpleName() + "." + method.getName() + ": " + e.getMessage());
                            }

                        } else {
                            throw new IllegalArgumentException("Parameter argsForMethod CsvSourceAnnotation can't be empty");
                        }
                    } else {
                        method.setAccessible(true);
                        method.invoke(instance);
                    }
                } catch (Exception e) {
                    throw new InvokingMethodException("Error invoking method " + c.getSimpleName() + "." + method.getName() + ": " + e.getMessage());
                }

                //Вызов метода AfterTest, если он есть в классе и если есть тестовые методы (Если метода AfterTest нет в классе метод ищется у родительского класса)
                if (afterTestMethod != null && !testMethods.isEmpty()) {
                    invokeBeforeAndAfterMethods(afterTestMethod, c);
                } else if (!testMethods.isEmpty()) {
                    invokeParentsBeforeAndAfterIfHave(c, AfterTest.class);
                } else {
                    throw new InvokingMethodException("You have no tests in " + c.getSimpleName() + " class");
                }
            }
        }

        //Вызов метода AfterSuite, если он есть в классе и если есть тестовые методы (Если метода AfterSuit нет в классе метод ищется у родительского класса)
        if (afterSuiteMethod != null && !testMethods.isEmpty()) {
            invokeBeforeAndAfterMethods(afterSuiteMethod, c);
        } else if (!testMethods.isEmpty()) {
            invokeParentsBeforeAndAfterIfHave(c, AfterSuite.class);
        } else {
            throw new InvokingMethodException("You have no tests in " + c.getSimpleName() + " class");
        }
    }

    /**
     * Метод вызова Before и After методов родительского класса, вызывается метод первого же класса, в котором он описан
     *
     * @param c          класс, в котором запускаем тесты
     * @param annotation конкретная аннотация Before или After
     */
    private static void invokeParentsBeforeAndAfterIfHave(Class<?> c, Class<? extends Annotation> annotation) {
        var superClass = c.getSuperclass();
        Method methodForInvoke = null;

        while (!superClass.equals(Object.class)) {
            AtomicInteger count = new AtomicInteger();
            for (Method method : superClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(annotation)) {
                    methodForInvoke = validateBeforeAndAfterMethods(method, count, superClass, annotation.getName());
                }
            }

            if (methodForInvoke != null) {
                invokeBeforeAndAfterMethods(methodForInvoke, c);
                break;
            } else {
                superClass = superClass.getSuperclass();
            }
        }
    }

    /**
     * Метод валидации Before и After методов
     *
     * @param method         метод, который валидируется
     * @param count          текущее количество данных методов в классе
     * @param c              класс, в котором запускаем тесты
     * @param annotationName конкретная аннотация Before или After
     * @return возвращается метод, которых необходимо запустить
     */
    private static Method validateBeforeAndAfterMethods(Method method, AtomicInteger count, Class<?> c, String annotationName) {
        if (!Modifier.isStatic(method.getModifiers())) {
            throw new WrongModifiersMethodException("Method - " + c.getSimpleName() + "." + method.getName() +
                    " is not a static for " + annotationName + " annotation");
        }
        if (count.incrementAndGet() > 1) {
            throw new TooMuchMethodsException("Method  " + annotationName + "  can only be one is class - " + c.getSimpleName());
        }

        return method;
    }

    /**
     * Метод для запуска методов Before и After
     *
     * @param method метод, который необходимо выполнить
     * @param c      класс, в котором запускаем тесты
     */
    private static void invokeBeforeAndAfterMethods(Method method, Class<?> c) {
        try {
            method.setAccessible(true);
            method.invoke(null);
        } catch (Exception e) {
            throw new InvokingMethodException("Error invoking method " + c.getSimpleName() + "." + method.getName() +
                    ": " + e.getMessage());
        }
    }

    /**
     * Метод преобразования строковых параметров в нужные типы
     *
     * @param param      параметр
     * @param targetType тип для преобразования параметра
     * @return параметр нужного типа
     */
    private static Object convertParameter(String param, Class<?> targetType) {
        if (targetType == String.class) {
            return param;
        } else if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(param);
        } else if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(param);
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(param);
        } else {
            throw new IllegalArgumentException("Unsupported parameter type: " + targetType.getName());
        }
    }
}
