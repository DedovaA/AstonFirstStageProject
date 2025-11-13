package aston.first_stage_project;

import java.util.*;

public class App {

    public static void main(String[] args) {

        List<MenuEntry> menuEntryList = Arrays.asList(
                new MenuEntry("1", "Из файла", new FromFileStrategy()),
                new MenuEntry("2", "Консольный ввод", new ManuallyStrategy()),
                new MenuEntry("3", "Рандомная генерация", new RandomlyStrategy())
            );

        Map<String, MenuEntry> strategyMap = new HashMap<>();
        menuEntryList.forEach(element -> strategyMap.put(element.getKey(), element));

        String mainMenuMessage = new MainMenu(menuEntryList).getMenu();

        List<Bus> busList;
        try (Scanner scanner = new Scanner(System.in)) {
            String userInput;
            while (true) {
                System.out.println(mainMenuMessage);
                userInput = scanner.nextLine().trim();
                if (userInput.equalsIgnoreCase("Q")) {
                    System.out.println("Выход из программы.");
                    return;
                }
                if (strategyMap.containsKey(userInput)) {
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
        System.out.println("\nUnsorted list:");
        list.forEach(System.out::println);
        System.out.println("Sorted list:");
        SortUtils.quickSort(list);
        list.forEach(System.out::println);
    }
}
