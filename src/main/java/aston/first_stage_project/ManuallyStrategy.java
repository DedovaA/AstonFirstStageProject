package aston.first_stage_project;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ManuallyStrategy implements DataSource{
    @Override
    public List<Bus> getBusList() {
        List<Bus> buses = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int count = askBusCount(scanner);
        if (count == 0) {
            System.out.println("Возврат в главное меню.");
            return null;
        }

        System.out.println("Введите данные автобусов в формате: Номер,Модель,Пробег");
        System.out.println("Пример: A456KW,Volvo,156000");
        System.out.println("Для выхода из ввода в любой момент введите 'Q' и нажмите Enter.");


        int entered = 0;
        while (entered < count) {
            System.out.printf("Автобус %d из %d:%n", entered + 1, count);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Q")) {
                System.out.println("Ввод прерван пользователем. Возврат в главное меню.");
                return null;
            }

            String[] parts = input.split(",");
            if (parts.length != 3) {
                System.out.println("Ошибка: ожидалось 3 поля, разделённые ','. Попробуйте снова.");
                continue;
            }

            String number = parts[0].trim();
            String model = parts[1].trim();
            String mileageStr = parts[2].trim();

            // Проверка номера (ровно 6 символов)
            if (number.length() != 6) {
                System.out.println("Ошибка: номер должен содержать ровно 6 символов. Повторите ввод.");
                continue;
            }

            // Проверка модели (не более 30 символов)
            if (model.isEmpty() || model.length() > 30) {
                System.out.println("Ошибка: модель должна содержать не более 30 символов. Повторите ввод.");
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

            Bus bus = new Bus.BusBuilder()
                    .setNumber(number)
                    .setModel(model)
                    .setRun(mileage)
                    .build();
            buses.add(bus);
            entered++;
        }

        System.out.println("Ввод завершён. Получено " + buses.size() + " автобусов.");
        scanner.close();
        return buses;
    }

    private int askBusCount(Scanner scanner) {
        System.out.println("Введите количество автобусов для ввода (или 'Q' для выхода):");
        int count;
        while (true) {
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("Q")) {
                return 0;
            }

            try {
                count = Integer.parseInt(line);
                if (count <= 0) {
                    System.out.println("Количество должно быть положительным числом. Попробуйте снова:");
                    continue;
                }
                // Ограничение на количество автобусов (не более 15)
                if (count > 15) {
                    System.out.println("Ошибка: количество автобусов не может превышать 15. Введите число от 1 до 15:");
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
