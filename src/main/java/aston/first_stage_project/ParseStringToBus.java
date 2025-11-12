package aston.first_stage_project;

import aston.first_stage_project.exceptions.*;

public class ParseStringToBus {
    public static Bus parseStringToBus(String str) {

        //Читает строку формата <модель>,<номер>,<пробег> и возвращает объект Bus

        String[] busString = (str + " ").split(","); //Массив строк из поданой на вход строки
        if (busString.length != 3) {
            throw new CustomException("Передано неверное количество параметров для создания автобуса. ");
        }
        String busNumber = busString[0].trim();
        String busModel = busString[1].trim();;

        int busRun = 0;

        if (busModel.length() > 30) {
            throw new CustomException("Передано слишком длинное имя модели. ");
        }

        if (busNumber.length() > 6) {
            throw new CustomException("Передан слишком длинный номер автобуса. ");
        }

        String busRunString = busString[2].trim();
        if (!busRunString.isEmpty()) { //Если передан пустой пробег, то оставляем его нулевым
            try {
                busRun = Integer.parseInt(busString[2].trim());
                if (busRun < 0) {
                    throw new CustomException("В качестве пробега передано отрицательное число. ");
                }
            } catch (NumberFormatException e) {
                throw new CustomException("В качестве пробега передана строка, не приводимая к int. ");
            }
        }
        return new Bus.BusBuilder().
                setModel(busModel).
                setRun(busRun).
                setNumber(busNumber)
                .build();
    }
}
