package aston.first_stage_project;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        List<Bus> list;
        boolean isActive = true;
        try(Scanner scanner = new Scanner(System.in)) {
            while (isActive) {
                System.out.println("""
                        Выберите источник ввода данных для сортировки:
                        1 - из файла,
                        2 - вручную из консоли,
                        3 - рандомный список.
                        Q - для выхода,
                        и нажмите Enter.""");
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("Q")) {
                    System.out.println("Выход из программы.");
                    return;
                } else {
                    switch (userInput) {
                        case "1" -> {
                            list = new FromFileStrategy().getBusList();
                            System.out.println("Unsorted list:");
                            list.forEach(System.out::println);
                            System.out.println("Sorted list:");
                            SortUtils.quickSort(list);
                            list.forEach(System.out::println);
                            isActive = false;
                        }
                        case "2" -> {
                            list = new ManuallyStrategy().getBusList();
                            System.out.println("Unsorted list:");
                            list.forEach(System.out::println);
                            System.out.println("Sorted list:");
                            SortUtils.quickSort(list);
                            list.forEach(System.out::println);
                            isActive = false;
                        }
                        case "3" -> {
                            list = new RandomlyStrategy().getBusList();
                            System.out.println("Unsorted list:");
                            list.forEach(System.out::println);
                            System.out.println("Sorted list:");
                            SortUtils.quickSort(list);
                            list.forEach(System.out::println);
                            isActive = false;
                        }
                        default -> System.out.println("Неверный ввод, попробуйте еще раз.");
                    }
                }
            }
        }
    }
}
