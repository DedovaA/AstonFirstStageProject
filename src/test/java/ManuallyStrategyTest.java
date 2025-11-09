import aston.first_stage_project.Bus;
import aston.first_stage_project.ManuallyStrategy;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class ManuallyStrategyTest {
    @Test
    public void testGetBusList_SuccessfulInput() {
        String input = "2\nA56KW,Volvo,156000\nB77TR,Mercedes,89000\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ManuallyStrategy strategy = new ManuallyStrategy();

        try {
            // Выполнение
            List<Bus> result = strategy.getBusList();

            // Проверка
            assertNotNull(result);
            assertEquals(2, result.size());

            Bus firstBus = result.get(0);
            assertEquals("A56KW", firstBus.getNumber());
            assertEquals("Volvo", firstBus.getModel());
            assertEquals(156000, firstBus.getRun());

            Bus secondBus = result.get(1);
            assertEquals("B77TR", secondBus.getNumber());
            assertEquals("Mercedes", secondBus.getModel());
            assertEquals(89000, secondBus.getRun());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void testGetBusList_InvalidCountThenValid() {
        // Неверное количество, затем верное
        String input = "abc\n-1\n0\n2\nA56KW,Volvo,156000\nB77TR,Mercedes,89000\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ManuallyStrategy strategy = new ManuallyStrategy();

        try {
            List<Bus> result = strategy.getBusList();

            assertNotNull(result);
            assertEquals(2, result.size());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void testGetBusList_InvalidBusDataThenValid() {
        // Неверные данные автобуса, затем верные
        String input = "2\n" +
                "A56KW,Volvo,abc\n" +      // неверный пробег
                "A56KW,Volvo,156000\n" +   // верные данные
                ";;\n" +                   // неверный разделитель
                "B77TR,Mercedes,89000\n";  // верные данные

        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ManuallyStrategy strategy = new ManuallyStrategy();

        try {
            List<Bus> result = strategy.getBusList();

            assertNotNull(result);
            assertEquals(2, result.size());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void testGetBusList_NegativeMileageThenValid() {
        // Отрицательный пробег, затем верный
        String input = "1\nA56KW,Volvo,-100\nA56KW,Volvo,156000\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ManuallyStrategy strategy = new ManuallyStrategy();

        try {
            List<Bus> result = strategy.getBusList();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(156000, result.get(0).getRun());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void testGetBusList_EmptyFieldsThenValid() {
        // Пустые поля, затем верные данные
        String input = "1\n,Volvo,156000\nA56KW,Volvo,156000\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ManuallyStrategy strategy = new ManuallyStrategy();

        try {
            List<Bus> result = strategy.getBusList();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("A56KW", result.get(0).getNumber());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void testGetBusList_IncorrectFieldCountThenValid() {
        // Неверное количество полей
        String input = "1\nA56KW,Volvo,156000,ExtraField\nA56KW,Volvo,156000\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ManuallyStrategy strategy = new ManuallyStrategy();

        try {
            List<Bus> result = strategy.getBusList();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("A56KW", result.get(0).getNumber());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void testGetBusList_MultipleBusesWithSpaces() {
        // Данные с пробелами
        String input = "2\n  A56KW  ,  Volvo  ,  156000  \nB77TR  ,Mercedes  ,  89000  \n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ManuallyStrategy strategy = new ManuallyStrategy();

        try {
            List<Bus> result = strategy.getBusList();

            assertNotNull(result);
            assertEquals(2, result.size());

            // Проверяем, что пробелы обрезаются
            assertEquals("A56KW", result.get(0).getNumber());
            assertEquals("Volvo", result.get(0).getModel());
            assertEquals("B77TR", result.get(1).getNumber());
            assertEquals("Mercedes", result.get(1).getModel());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void testGetBusList_ZeroCount() {
        // Нулевое количество автобусов
        String input = "0\n1\nA56KW,Volvo,156000\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ManuallyStrategy strategy = new ManuallyStrategy();

        try {
            List<Bus> result = strategy.getBusList();

            assertNotNull(result);
            assertEquals(1, result.size());
        } finally {
            System.setIn(originalIn);
        }
    }
}
