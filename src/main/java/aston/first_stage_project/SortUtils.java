package aston.first_stage_project;

import java.util.List;

public class SortUtils {
    public static <T extends Comparable<T>> void quickSort(List<T> list) {
        if (list == null) {
            throw new NullPointerException("list must not be null");
        }

        quickSort(list, 0, list.size() - 1);
    }

    public static <T extends Comparable<T>> void quickSort(List<T> list, int l, int r) {
        int i = l;//первый
        int j = r;//последний

        T pivot = list.get((i + j) / 2);//из середины

        while (i < j) {
            while (list.get(i).compareTo(pivot) < 0) {//если первый элемент
                i++;
            }

            while (list.get(j).compareTo(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                T tmp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, tmp);
                i++;
                j--;
            }
        }

        if (l < j) {//базовое условие
            quickSort(list, l, j);
        }

        if (i < r) {//базовое условие
            quickSort(list, i, r);
        }
    }
}
