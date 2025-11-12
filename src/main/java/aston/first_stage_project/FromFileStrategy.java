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
                Строка должна иметь вид: Номер,Модель,Пробег
                Допускаются пробелы внутри значений.
                Чтобы выйти в главное меню, введите Q и нажмите Enter.""");

        Scanner fromFileScanner = new Scanner(System.in);
        inputPath = fromFileScanner.nextLine();
        if (inputPath.equalsIgnoreCase("Q")) {
            System.out.println("Ввод прерван пользователем. Возврат в главное меню \n ====================================");
            return null;
        }

        ArrayList<Bus> result = new ArrayList<>();
        int ExceptionIndex = 0; // Номер строки, на которой может возникнуть ошибка
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = reader.readLine()) != null) { //Может выбросить IOException
                ExceptionIndex += 1;
                try {
                    result.add(parseStringToBus(line));
                }
                catch (CustomException e) {
                    System.out.println("Ошибка при чтении строки " + ExceptionIndex + ". " + e.getMessage() +
                            "Строка пропущена.");
                }
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка при чтении файла " + inputPath + ". " + e.getMessage());
            System.out.println("Возврат в главное меню. \n ====================================");
            return null;
        }
        catch (Exception e) {
            System.out.println("Мы смогли получить необработанное исключение. " + e.getMessage());
            System.out.println("Возврат в главное меню. \n ====================================");
            return null;
        }
        System.out.println("Создан список автобусов с " + result.size() + " автобусами.");
//        fromFileScanner.close();
        return result;
    }

    @Override
    public String toString() {
        return "Из файла (FromFile)";
    }
}
