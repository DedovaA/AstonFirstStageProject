package aston.first_stage_project;

import aston.first_stage_project.exceptions.CustomException;
import aston.first_stage_project.exceptions.IncorrectRunException;
import aston.first_stage_project.exceptions.ParametersNumberException;

public class ParseStringToBus {
    public static Bus parseStringToBus(String str) {

        //Читает строку формата <модель>,<номер>,<пробег> и возвращает объект Bus

        String[] busString = str.split(","); //Массив строк из поданой на вход строки
        if (busString.length != 3) {
            throw new ParametersNumberException("Передано неверное количество параметров для создания автобуса");
        }
        String busModel = busString[0].trim();
        String busNumber = busString[1].trim();
        int busRun = 0;
        try {
            busRun = Integer.parseInt(busString[2].trim());
            if (busRun < 0) {
                throw new IncorrectRunException("В качестве пробега передано отрицательное число");
            }
        }
        catch (NumberFormatException e) {
            throw new IncorrectRunException("В качестве пробега передано число, не приводимое к int");
        }
        return new Bus.BusBuilder().
                setModel(busModel).
                setRun(busRun).
                setNumber(busNumber)
                .build();
    }
}
