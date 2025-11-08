package aston.first_stage_project;

import aston.first_stage_project.exceptions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static aston.first_stage_project.ParseStringToBus.parseStringToBus;

public class FromFileStrategy implements DataSource{
    @Override
    public List<Bus> getBusList() {
        String inputPath;
        System.out.println("""
                Укажите абсолютный путь к файлу с данными об автобусах.
                Одна строка может содержать данные только об одном автобусе.
                Строка должна иметь вид:
                <модель>,<номер>,<пробег>
                Допускаются пробелы внутри значений.""");
        Scanner scanner = new Scanner(System.in);
        inputPath = scanner.nextLine();

        ArrayList<Bus> result = new ArrayList<>();
        int ExceptionIndex = 0; // Номер строки, на которой может возникнуть ошибка
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = reader.readLine()) != null) { //Может выбросить IOException
                ExceptionIndex += 1;
                try {
                    result.add(parseStringToBus(line));
                }
                catch (ParametersNumberException e) {
                    System.out.println("В строке " + ExceptionIndex + " было получено неверное число параметров для создания автобуса. " +
                            "Строка пропущена.");
                }
                catch (IncorrectRunException e) {
                    System.out.println("В строке " + ExceptionIndex + " было получено значение пробега, не являющееся натуральным числом. " +
                            "Строка пропущена.");
                }
                catch (IncorrectNumberException e) {
                    System.out.println("В строке " + ExceptionIndex + " было получено пустое значение номера автобуса. " +
                            "Строка пропущена.");
                }
                catch (IncorrectModelException e) {
                    System.out.println("В строке " + ExceptionIndex + " было получено пустое значение модели автобуса. " +
                            "Строка пропущена.");
                }
            }
        }
        catch (IOException e) { //Оборачиваем исключение в кастомное unchecked
            throw new CustomException("Ошибка при чтении файла " + inputPath + ". " + e.getCause());
        }
        catch (Exception e) {
            System.out.println("Мы смогли получить необработанное исключение. " + e.getCause());
        }
        System.out.println("Создан список автобусов с " + result.size() + " автобусами.");
        return result;
    }
}
