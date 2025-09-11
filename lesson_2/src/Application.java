import model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        // 2.1 Задание
        var integerFirstList = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);

        var firstResult = integerFirstList.stream().sorted(Comparator.reverseOrder()).toList().get(2);

        System.out.println("Третье наибольшее число - " + firstResult);
        System.out.println("-----------------------------------------------------------------------------------");

        // 2.2 Задание
        var integerSecondList = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);

        var secondResult = new HashSet<>(integerSecondList).stream().sorted(Comparator.reverseOrder()).toList().get(2);

        System.out.println("Третье наибольшее уникальное число - " + secondResult);
        System.out.println("-----------------------------------------------------------------------------------");

        // 2.3 Задание
        var employeeList = List.of(
                new Employee("Дмитрий", 32, "Тестировщик"),
                new Employee("Игнат", 19, "Инженер"),
                new Employee("Светлана", 51, "Бухгалтер"),
                new Employee("Федор", 36, "Инженер"),
                new Employee("Юлия", 45, "Бухгалтер"),
                new Employee("Агнесса", 89, "Инженер"),
                new Employee("Агата", 26, "Ниндзя"),
                new Employee("Прохор", 44, "Дворник"),
                new Employee("Саймон", 19, "Инженер"),
                new Employee("Сплинтер", 41, "Пастух"),
                new Employee("Антон", 56, "Инженер")
        );

        var thirdResult = employeeList.stream().filter(el -> el.getPosition().equals("Инженер"))
                .sorted((el1, el2) -> el2.getAge() - el1.getAge()).limit(3)
                .map(Employee::getName).toList();

        System.out.println(thirdResult);
        System.out.println("-----------------------------------------------------------------------------------");

        // 2.4 Задание
        var fourthResult = employeeList.stream().filter(el -> el.getPosition().equals("Инженер"))
                .mapToInt(Employee::getAge).average().orElse(0.0);

        System.out.println(fourthResult);
        System.out.println("-----------------------------------------------------------------------------------");

        // 2.5 Задание
        var stringList = List.of("Воитловаили", "товадлдфадфштдфад", "роии", "лфтыафугшаишг");
        var fifthResult = stringList.stream().max(Comparator.comparingInt(String::length)).orElse(null);

        System.out.println(fifthResult);
        System.out.println("-----------------------------------------------------------------------------------");

        // 2.6 Задание
        var interString = "слово крокодил инокентий инокентий слово слово черепашка ниндзя черепашка слов слово";
        HashMap<String, Integer> sixthResult = new HashMap<>();
        Arrays.stream(interString.split(" ")).forEach(el -> {
            if (sixthResult.containsKey(el)) {
                sixthResult.put(el, sixthResult.get(el) + 1);
            } else {
                sixthResult.put(el, 1);
            }
        });

        sixthResult.forEach((key, value) -> System.out.println("Слово - " + key + " встречалось " + value + " раз(а)"));
        System.out.println("-----------------------------------------------------------------------------------");

        // 2.7 Задание
        var secondStringList = List.of("Аоитловаили", "товадлдфадфштдфад", "роии", "лфтыафугшаишг", "ноитловаили", "Повадлдфадфштдфад");
        var seventhResult = secondStringList.stream().sorted((el1, el2) -> {
            if (el1.length() == el2.length()) {
                return el1.compareToIgnoreCase(el2);
            } else {
                return el1.length() - el2.length();
            }
        }).toList();

        System.out.println(seventhResult);
        System.out.println("-----------------------------------------------------------------------------------");

        // 2.8 Задание
        var listOfString = List.of(
                "один два три четыре пять",
                "шесть два семь четыре пять",
                "один самоедлинноеслово три четыре пять",
                "один два три четыре пять",
                "один два три самоедлинноеопять пять"
        );

        List<String> wordsList = new ArrayList<>();
        listOfString.forEach(el -> wordsList.addAll(List.of(el.split(" "))));
        var eighthResult = wordsList.stream().max(Comparator.comparingInt(String::length)).orElse(null);

        System.out.println(eighthResult);
        System.out.println("-----------------------------------------------------------------------------------");
    }
}
