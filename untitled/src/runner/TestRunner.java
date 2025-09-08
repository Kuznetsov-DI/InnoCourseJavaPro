package runner;

import annotations.*;
import exception.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Method beforeSuiteMethod = null;
        Method afterSuiteMethod = null;
        List<Method> beforeTestMethods = new ArrayList<>();
        List<Method> afterTestMethods = new ArrayList<>();
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
                beforeSuiteMethod = validateBeforeSuiteAndAfterSuiteMethods(method, beforeSuiteMethod, c, "BeforeSuite");
            }

            // AfterSuite проверки аннотации
            if (method.isAnnotationPresent(AfterSuite.class)) {
                afterSuiteMethod = validateBeforeSuiteAndAfterSuiteMethods(method, afterSuiteMethod, c, "AfterSuite");
            }

            // BeforeTest проверки аннотации
            if (method.isAnnotationPresent(BeforeTest.class)) {
                validateNonStaticMethod(method, c);
                beforeTestMethods.add(method);
            }

            // AfterTest проверки аннотации
            if (method.isAnnotationPresent(AfterTest.class)) {
                validateNonStaticMethod(method, c);
                afterTestMethods.add(method);
            }

            //Test проверки аннотации
            if (method.isAnnotationPresent(Test.class)) {
                validateNonStaticMethod(method, c);

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
            invokeMethod(beforeSuiteMethod, c, null);
        } else if (!testMethods.isEmpty()) {
            invokeParentsBeforeSuiteAndAfterSuiteIfHave(c, BeforeSuite.class);
        } else {
            throw new InvokingMethodException("You have no tests in " + c.getSimpleName() + " class");
        }

        //Вызов тестовых методов в порядке приоритетов
        if (!testMethods.isEmpty()) {
            var prioritizedMethods = testMethods.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).toList();

            for (Method method : prioritizedMethods) {

                //Вызов метода BeforeTest, если он есть в классе и если есть тестовые методы (Если метода BeforeTest нет в классе метод ищется у родительского класса)
                if (!beforeTestMethods.isEmpty() && !testMethods.isEmpty()) {
                    beforeTestMethods.forEach(el -> invokeMethod(el, c, instance));
                } else if (!testMethods.isEmpty()) {
                    invokeParentBeforeTestAndAfterTestIfHave(c, BeforeTest.class, instance);
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
                if (!afterTestMethods.isEmpty() && !testMethods.isEmpty()) {
                    afterTestMethods.forEach(el -> invokeMethod(el, c, instance));
                } else if (!testMethods.isEmpty()) {
                    invokeParentBeforeTestAndAfterTestIfHave(c, AfterTest.class, instance);
                } else {
                    throw new InvokingMethodException("You have no tests in " + c.getSimpleName() + " class");
                }
            }
        }

        //Вызов метода AfterSuite, если он есть в классе и если есть тестовые методы (Если метода AfterSuit нет в классе метод ищется у родительского класса)
        if (afterSuiteMethod != null && !testMethods.isEmpty()) {
            invokeMethod(afterSuiteMethod, c, null);
        } else if (!testMethods.isEmpty()) {
            invokeParentsBeforeSuiteAndAfterSuiteIfHave(c, AfterSuite.class);
        } else {
            throw new InvokingMethodException("You have no tests in " + c.getSimpleName() + " class");
        }
    }

    /**
     * Метод вызова BeforeSuite и AfterSuite методов родительского класса, вызывается метод первого же класса, в котором он описан
     *
     * @param c          класс, в котором запускаем тесты
     * @param annotation конкретная аннотация Before или After
     */
    private static void invokeParentsBeforeSuiteAndAfterSuiteIfHave(Class<?> c, Class<? extends Annotation> annotation) {
        var superClass = c.getSuperclass();
        Method methodForInvoke = null;

        while (!superClass.equals(Object.class)) {
            for (Method method : superClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(annotation)) {
                    methodForInvoke = validateBeforeSuiteAndAfterSuiteMethods(method, methodForInvoke, superClass, annotation.getName());
                }
            }

            if (methodForInvoke != null) {
                invokeMethod(methodForInvoke, c, null);
                break;
            } else {
                superClass = superClass.getSuperclass();
            }
        }
    }

    private static void invokeParentBeforeTestAndAfterTestIfHave(Class<?> c, Class<? extends Annotation> annotation, Object instance) {
        var superClass = c.getSuperclass();
        List<Method> methodsForInvoke = new ArrayList<>();

        while (!superClass.equals(Object.class)) {
            for (Method method : superClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(annotation)) {
                    validateNonStaticMethod(method, c);
                    methodsForInvoke.add(method);
                }
            }

            if (!methodsForInvoke.isEmpty()) {
                methodsForInvoke.forEach(method -> invokeMethod(method, c, instance));
                break;
            } else {
                superClass = superClass.getSuperclass();
            }
        }
    }

    /**
     * Метод валидации BeforeSuite и AfterSuite методов
     *
     * @param method         метод, который валидируется
     * @param currentMethod  текущий метод BeforeSuite, для проверки повторно созданного метода
     * @param c              класс, в котором запускаем тесты
     * @param annotationName конкретная аннотация Before или After
     * @return возвращается метод, которых необходимо запустить
     */
    private static Method validateBeforeSuiteAndAfterSuiteMethods(Method method, Method currentMethod, Class<?> c, String annotationName) {
        if (!Modifier.isStatic(method.getModifiers())) {
            throw new WrongModifiersMethodException("Method - " + c.getSimpleName() + "." + method.getName() +
                    " is not a static for " + annotationName + " annotation");
        }
        if (currentMethod != null) {
            throw new TooMuchMethodsException("Method  " + annotationName + "  can only be one is class - " + c.getSimpleName());
        }

        return method;
    }

    private static void validateNonStaticMethod(Method method, Class<?> c) {
        if (Modifier.isStatic(method.getModifiers())) {
            throw new WrongModifiersMethodException("Method - " + c.getSimpleName() + "." + method.getName() +
                    " must be not a static for this annotation");
        }
    }

    /**
     * Метод для запуска методов
     *
     * @param method   метод, который необходимо выполнить
     * @param c        класс, в котором запускаем тесты
     * @param instance объект класса, если метод не статический
     */
    private static void invokeMethod(Method method, Class<?> c, Object instance) {
        try {
            method.setAccessible(true);
            method.invoke(instance);
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
