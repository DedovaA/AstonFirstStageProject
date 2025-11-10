package aston.first_stage_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomlyStrategy implements DataSource{
    public static final Random random = new Random();

    //Доступные модели
    public static final String[] MODELS = {"Volvo", "Hyundai", "MAN", "BMW"};

    //Буквы и цифры для генерации номеров
    public static final char[] LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

    //Генерация случайного номера
    public static String generateRandomNumber() {
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            number.append(LETTERS[random.nextInt(LETTERS.length)]);
        }

        return number.toString();
    }

    //Генерация случайной модели
    public static String generateRandomModel() {
        return MODELS[random.nextInt(MODELS.length)];
    }

    //Генерация случайного пробега от 0 до 500 000 км
    public static int generateRandomMileage() {
        return random.nextInt(501) * 1000;
    }

    //Генерация списка случайных автобусов
    public List<Bus> getBusList() {
        List<Bus> buses = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        //Ввод пользователем количества автобусов
        int count = askBusCount(scanner);
        if (count == 0) {
            System.out.println("Возврат в главное меню.");
            return null;
        }

        //Генерация автобусов
        for (int entered = 0; entered < count; entered++){
            Bus bus = new Bus.BusBuilder()
                    .setNumber(generateRandomNumber())
                    .setModel(generateRandomModel())
                    .setRun(generateRandomMileage())
                    .build();
            buses.add(bus);
        }
        return buses;
    }

    //Метод запроса количества автобусов у пользователя
    private int askBusCount(Scanner scanner) {
        System.out.println("Введите количество автобусов для ввода (или 'Q' для выхода):");
        int count;
        while (true) {
            String line = scanner.nextLine();

            //Если нажал Q, выход из программы
            if (line.equalsIgnoreCase("Q")) {
                return 0;
            }

            //Проверка правильности ввода
            try {
                count = Integer.parseInt(line);
                if (count <= 0) {
                    System.out.println("Количество должно быть положительным , больше 0. Попробуйте снова:");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число, например: 3");
            }
        }
        return count;
    }
}
