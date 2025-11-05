package aston.first_stage_project;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ManuallyStrategy implements DataSource{
    @Override
    public List<Bus> getBusList() {
        List<Bus> buses = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество автобусов для ввода:");
        int count = 0;
        while (true) {
            String line = scanner.nextLine();
            try {
                count = Integer.parseInt(line);
                if (count <= 0) {
                    System.out.println("Количество должно быть положительным числом. Попробуйте снова:");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число, например: 3");
            }
        }

        System.out.println("Введите данные автобусов в формате: Номер;Модель;Пробег");
        System.out.println("Пример: A56KW;Volvo;156000");

        int entered = 0;
        while (entered < count) {
            System.out.printf("Автобус %d из %d:%n", entered + 1, count);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Q")) {
                System.out.println("Ввод прерван пользователем.");
                break;
            }

            String[] parts = input.split(";");
            if (parts.length != 3) {
                System.out.println("Ошибка: ожидалось 3 поля, разделённые ';'. Попробуйте снова.");
                continue;
            }

            String number = parts[0].trim();
            String model = parts[1].trim();
            String mileageStr = parts[2].trim();

            // Валидация
            if (number.isEmpty() || model.isEmpty()) {
                System.out.println("Ошибка: номер и модель не могут быть пустыми. Повторите ввод.");
                continue;
            }

            int mileage;
            try {
                mileage = Integer.parseInt(mileageStr);
                if (mileage < 0) {
                    System.out.println("Ошибка: пробег не может быть отрицательным. Повторите ввод.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: пробег должен быть числом. Повторите ввод.");
                continue;
            }

            // Создание через Builder
            Bus bus = new Bus.BusBuilder()
                    .setNumber(number)
                    .setModel(model)
                    .setRun(mileage)
                    .build();
            buses.add(bus);
            entered++;
        }

        System.out.println("Ввод завершён. Получено " + buses.size() + " автобусов.");
        return buses;
    }
}
