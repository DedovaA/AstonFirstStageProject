import aston.first_stage_project.Bus;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class BusTest {
    List<Bus> sortedBuses = new ArrayList<>();
    List<Bus> randomBuses = new ArrayList<>();

    @Test
    public void sortRun() {
        randomBuses.add(new Bus.BusBuilder().setRun(9).build());
        randomBuses.add(new Bus.BusBuilder().setRun(2).build());
        randomBuses.add(new Bus.BusBuilder().setRun(5).build());
        randomBuses.forEach(System.out::println);
        System.out.println();

        sortedBuses.add(new Bus.BusBuilder().setRun(2).build());
        sortedBuses.add(new Bus.BusBuilder().setRun(5).build());
        sortedBuses.add(new Bus.BusBuilder().setRun(9).build());

        Collections.sort(randomBuses);
        randomBuses.forEach(System.out::println);
        assertTrue(sortedBuses.equals(randomBuses));
    }

    @Test
    public void sortModel() {
        randomBuses.add(new Bus.BusBuilder().setModel("VOLVO").setRun(9).build());
        randomBuses.add(new Bus.BusBuilder().setModel("MAN").setRun(7).build());
        randomBuses.add(new Bus.BusBuilder().setModel("HYUNDAI").setRun(2).build());
        randomBuses.add(new Bus.BusBuilder().setModel("MAN").setRun(5).build());
        randomBuses.forEach(System.out::println);
        System.out.println();

        sortedBuses.add(new Bus.BusBuilder().setModel("HYUNDAI").setRun(2).build());
        sortedBuses.add(new Bus.BusBuilder().setModel("MAN").setRun(5).build());
        sortedBuses.add(new Bus.BusBuilder().setModel("MAN").setRun(7).build());
        sortedBuses.add(new Bus.BusBuilder().setModel("VOLVO").setRun(9).build());

        Collections.sort(randomBuses);
        randomBuses.forEach(System.out::println);
        assertTrue(sortedBuses.equals(randomBuses));
    }

    @Test
    public void sortNumber() {
        randomBuses.add(new Bus.BusBuilder().setNumber("Ab").setModel("VOLVO").setRun(5).build());
        randomBuses.add(new Bus.BusBuilder().setNumber("2D").setModel("MAN").setRun(7).build());
        randomBuses.add(new Bus.BusBuilder().setNumber("A3").setModel("HYUNDAI").setRun(2).build());
        randomBuses.add(new Bus.BusBuilder().setNumber("A3").setModel("MAN").setRun(5).build());
        randomBuses.add(new Bus.BusBuilder().setNumber("1S").setModel("VOLVO").setRun(9).build());
        randomBuses.add(new Bus.BusBuilder().setNumber("A3").setModel("HYUNDAI").setRun(3).build());
        randomBuses.forEach(System.out::println);
        System.out.println();

        sortedBuses.add(new Bus.BusBuilder().setNumber("1S").setModel("VOLVO").setRun(9).build());
        sortedBuses.add(new Bus.BusBuilder().setNumber("2D").setModel("MAN").setRun(7).build());
        sortedBuses.add(new Bus.BusBuilder().setNumber("A3").setModel("HYUNDAI").setRun(2).build());
        sortedBuses.add(new Bus.BusBuilder().setNumber("A3").setModel("HYUNDAI").setRun(3).build());
        sortedBuses.add(new Bus.BusBuilder().setNumber("A3").setModel("MAN").setRun(5).build());
        sortedBuses.add(new Bus.BusBuilder().setNumber("Ab").setModel("VOLVO").setRun(5).build());

        Collections.sort(randomBuses);
        randomBuses.forEach(System.out::println);
        assertTrue(sortedBuses.equals(randomBuses));
    }

    @Test
    public void shouldCompareEqualObjects() {
        Bus bus1 = new Bus.BusBuilder().setNumber("Ab").setModel("VOLVO").setRun(5).build();
        Bus bus2 = new Bus.BusBuilder().setNumber("Ab").setModel("VOLVO").setRun(5).build();
        assertTrue(bus1.compareTo(bus2) == 0);
    }
    @Test
    public void shouldCompareEqualNullableObjects() {
        Bus bus1 = new Bus.BusBuilder().build();
        Bus bus2 = new Bus.BusBuilder().build();
        assertTrue(bus1.compareTo(bus2) == 0);
    }
    @Test
    public void shouldCompareRunEqualsFields() {
        Bus bus1 = new Bus.BusBuilder().setRun(3).build();
        Bus bus2 = new Bus.BusBuilder().setRun(3).build();
        assertTrue(bus1.compareTo(bus2) == 0);
    }
    @Test
    public void shouldCompareRunField1() {
        Bus bus1 = new Bus.BusBuilder().setRun(2).build();
        Bus bus2 = new Bus.BusBuilder().setRun(3).build();
        assertTrue(bus1.compareTo(bus2) < 0);
    }
    @Test
    public void shouldCompareModelEqualsField() {
        Bus bus1 = new Bus.BusBuilder().setModel("VOLVO").setRun(5).build();
        Bus bus2 = new Bus.BusBuilder().setModel("VOLVO").setRun(5).build();
        assertTrue(bus1.compareTo(bus2) == 0);
    }
    @Test
    public void shouldCompareModelField2() {
        Bus bus1 = new Bus.BusBuilder().setModel("MAN").setRun(5).build();
        Bus bus2 = new Bus.BusBuilder().setModel("VOLVO").setRun(5).build();
        assertTrue(bus1.compareTo(bus2) < 0);
    }
    @Test
    public void shouldCompareNumberEqualsField() {
        Bus bus1 = new Bus.BusBuilder().setNumber("A1").setModel("MAN").setRun(5).build();
        Bus bus2 = new Bus.BusBuilder().setNumber("A1").setModel("VOLVO").setRun(5).build();
        assertTrue(bus1.compareTo(bus2) < 0);
    }
    @Test
    public void shouldCompareNumberField() {
        Bus bus1 = new Bus.BusBuilder().setNumber("A").setModel("MAN").setRun(5).build();
        Bus bus2 = new Bus.BusBuilder().setNumber("1").setModel("VOLVO").setRun(5).build();
        assertTrue(bus1.compareTo(bus2) > 0);
    }
    @Test
    public void shouldCompareNumberField2() {
        Bus bus1 = new Bus.BusBuilder().setNumber("1").setModel("MAN").setRun(5).build();
        Bus bus2 = new Bus.BusBuilder().setNumber("A").setModel("VOLVO").setRun(5).build();
        assertTrue(bus1.compareTo(bus2) < 0);
    }
    @Test
    public void shouldCompareNumberField3() {
        Bus bus1 = new Bus.BusBuilder().setNumber("1").setModel("MAN").setRun(5).build();
        Bus bus2 = new Bus.BusBuilder().setNumber("2").setModel("VOLVO").setRun(5).build();
        assertTrue(bus1.compareTo(bus2) < 0);
    }

}
