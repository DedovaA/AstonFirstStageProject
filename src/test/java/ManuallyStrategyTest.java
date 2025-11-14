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
        String input = "2\nA456KW,Volvo,156000\nB477TR,Mercedes,89000\n";
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
            assertEquals("A456KW", firstBus.getNumber());
            assertEquals("Volvo", firstBus.getModel());
            assertEquals(156000, firstBus.getRun());

            Bus secondBus = result.get(1);
            assertEquals("B477TR", secondBus.getNumber());
            assertEquals("Mercedes", secondBus.getModel());
            assertEquals(89000, secondBus.getRun());
        }
        finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void testGetBusList_InvalidCountThenValid() {
        // Неверное количество, затем верное
        String input = "abc\n-1\n0\n2\nA456KW,Volvo,156000\nB477TR,Mercedes,89000\n";
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
                "A456KW,Volvo,abc\n" +      // неверный пробег
                "A456KW,Volvo,156000\n" +   // верные данные
                ";;\n" +                   // неверный разделитель
                "B477TR,Mercedes,89000\n";  // верные данные

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
        String input = "1\nA456KW,Volvo,-100\nA456KW,Volvo,156000\n";
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
        String input = "1\n,Volvo,156000\n"; // Должен работать без ошибок

        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ManuallyStrategy strategy = new ManuallyStrategy();

        try {
            List<Bus> result = strategy.getBusList();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("", result.get(0).getNumber()); // Проверяем пустой номер
            assertEquals("Volvo", result.get(0).getModel());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void testGetBusList_IncorrectFieldCountThenValid() {
        // Неверное количество полей
        String input = "1\nA456KW,Volvo,156000,ExtraField\nA456KW,Volvo,156000\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ManuallyStrategy strategy = new ManuallyStrategy();

        try {
            List<Bus> result = strategy.getBusList();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("A456KW", result.get(0).getNumber());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void testGetBusList_MultipleBusesWithSpaces() {
        // Данные с пробелами
        String input = "2\n  A456KW  ,  Volvo  ,  156000  \nB477TR  ,Mercedes  ,  89000  \n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ManuallyStrategy strategy = new ManuallyStrategy();

        try {
            List<Bus> result = strategy.getBusList();

            assertNotNull(result);
            assertEquals(2, result.size());

            // Проверяем, что пробелы обрезаются
            assertEquals("A456KW", result.get(0).getNumber());
            assertEquals("Volvo", result.get(0).getModel());
            assertEquals("B477TR", result.get(1).getNumber());
            assertEquals("Mercedes", result.get(1).getModel());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void testGetBusList_ZeroCount() {
        // Нулевое количество автобусов
        String input = "0\n1\nA456KW,Volvo,156000\n";
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

    @Test
    public void testGetBusList_MultipleValidationsInSequence() {
        // Комплексная проверка
        String input = "30\n1\n" +                // 30 автобусов - ошибка, потом верно
//                "A456,Volvo ,156000\n" +           // номер из 4 символов - ошибка
                "A456KWA456KW,Volvo,156000\n" +   // номер из 12 символов - ошибка
                "A456KW," + "A".repeat(31) + ",156000\n" + // модель из 31 символа - ошибка
                "A456KW,Volvo,156000\n";          // корректные данные - должно принять

        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ManuallyStrategy strategy = new ManuallyStrategy();

        try {
            List<Bus> result = strategy.getBusList();

            assertNotNull(result);
            assertEquals(1, result.size());

            Bus bus = result.get(0);
            assertEquals("A456KW", bus.getNumber());
            assertEquals("Volvo", bus.getModel());
            assertEquals(156000, bus.getRun());

        } finally {
            System.setIn(originalIn);
        }
    }
}
