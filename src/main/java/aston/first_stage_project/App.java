package aston.first_stage_project;

import java.util.List;

import java.util.Map;
import java.util.Scanner;

public class App {
    private static final DataSource[] strategyArr = {new FromFileStrategy(),new ManuallyStrategy(),new RandomlyStrategy()};

    public static void main(String[] args) {
        Map<String, DataSource> strategyMap = new StrategyMap(strategyArr).getMap();
        String mainMenuMessage = new MainMenu(strategyArr).getMenu();
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
                if (strategyMap.containsKey(input)) {
                    list = strategyMap.get(input).getBusList();
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
