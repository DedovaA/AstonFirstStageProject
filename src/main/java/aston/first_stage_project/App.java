package aston.first_stage_project;

import java.util.*;

public class App {

    public static void main(String[] args) {
        StrategyMap strategyMap = new StrategyMap();
        strategyMap.addStrategy("F", new MapEntry(new FromFileStrategy(), "Из файла"));
        strategyMap.addStrategy("M", new MapEntry(new ManuallyStrategy(), "Консольный ввод"));
        strategyMap.addStrategy("R", new MapEntry(new RandomlyStrategy(), "Рандомная генерация"));

        String mainMenuMessage = new MainMenu(strategyMap).getMenu();

        List<Bus> list;

        try (Scanner scanner = new Scanner(System.in)) {
            String input;
            while (true) {
                System.out.println(mainMenuMessage);
                input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("Q")) {
                    System.out.println("Выход из программы.");
                    return;
                }
                if (strategyMap.getStrategyMap().containsKey(input)) {
                    list = strategyMap.getStrategyMap().get(input).getStrategy().getBusList();
                    if (list != null && !list.isEmpty()) {
                        printResult(list);
                        break;
                    }
                } else {
                    System.out.println("Неверный ввод, попробуйте еще раз.\n");
                }
            }
        }
    }

    private static void printResult(List<Bus> list) {
        System.out.println("\nUnsorted list:");
        list.forEach(System.out::println);
        System.out.println("Sorted list:");
        SortUtils.quickSort(list);
        list.forEach(System.out::println);
    }
}
