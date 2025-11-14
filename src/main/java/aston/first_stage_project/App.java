package aston.first_stage_project;

import java.util.*;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        int i = 1;

        List<MenuEntry> menuEntryList = Arrays.asList(
                new MenuEntry(String.valueOf(i++), "Из файла", new FromFileStrategy()),
                new MenuEntry(String.valueOf(i++), "Консольный ввод", new ManuallyStrategy()),
                new MenuEntry(String.valueOf(i++), "Рандомная генерация", new RandomlyStrategy())
            );

        Map<String, MenuEntry> strategyMap = menuEntryList.stream()
                .collect(Collectors.toMap(MenuEntry::getKey, entry -> entry));

        MainMenu menu = new MainMenu(menuEntryList, "Q");

        List<Bus> busList;
        try (Scanner scanner = new Scanner(System.in)) {
            String userInput;
            while (true) {
                System.out.println(menu.getText());
                userInput = scanner.nextLine().trim();
                if (menu.isQuitKey(userInput)) {
                    System.out.println("Выход из программы.");
                    return;
                }
                if (menu.isValidKey(userInput)) {
                    busList = strategyMap.get(userInput).getValue().getBusList();
                    if (busList != null && !busList.isEmpty()) {
                        printResult(busList);
                        break;
                    }
                } else {
                    System.out.println("Неверный ввод, попробуйте еще раз.\n");
                }
            }
        }
    }

    private static void printResult(List<Bus> list) {
        System.out.println("\n========= Unsorted list: =========");
        list.forEach(System.out::println);
        System.out.println("\n========= Sorted list: =========");
        SortUtils.quickSort(list);
        list.forEach(System.out::println);
    }
}
