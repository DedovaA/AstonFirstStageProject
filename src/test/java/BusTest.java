import aston.first_stage_project.Bus;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertFalse;

public class BusTest {
    Bus b1 = new Bus.BusBuilder().setRun(545).build();
    Bus b2 = new Bus.BusBuilder().setNumber("123").setModel("ab").setRun(541).build();
    Bus b3 = new Bus.BusBuilder().setNumber("123").setModel("ab").setRun(542).build();
    Bus b4 = new Bus.BusBuilder().setNumber("123").setModel("abc").setRun(542).build();
    Bus b5 = new Bus.BusBuilder().setNumber("123").setModel("b").setRun(543).build();
    Bus b6 = new Bus.BusBuilder().setNumber("12a3").setRun(542).build();
    Bus b7 = new Bus.BusBuilder().setNumber("12a3").setModel("fd").setRun(542).build();
    Bus b8 = new Bus.BusBuilder().setNumber("12a4").build();
    Bus b9 = new Bus.BusBuilder().setNumber("1a").setRun(542).build();
    List<Bus> sortedBuses = new ArrayList<>();
    List<Bus> randomBuses = new ArrayList<>();

    @Before
    public void setUp() {
        sortedBuses.add(b1);
        sortedBuses.add(b2);
        sortedBuses.add(b3);
        sortedBuses.add(b4);
        sortedBuses.add(b5);
        sortedBuses.add(b6);
        sortedBuses.add(b7);
        sortedBuses.add(b8);
        sortedBuses.add(b9);
        randomBuses.add(b9);
        randomBuses.add(b1);
        randomBuses.add(b6);
        randomBuses.add(b3);
        randomBuses.add(b8);
        randomBuses.add(b2);
        randomBuses.add(b5);
        randomBuses.add(b7);
        randomBuses.add(b4);
    }

    @Test
    public void sort() {
        randomBuses.forEach(System.out::println);
        Collections.sort(randomBuses);
        boolean bug = false;
        for (int i = 0; i < sortedBuses.size(); i++) {
            if (!randomBuses.get(i).equals(sortedBuses.get(i)))
                bug = true;
        }
        randomBuses.forEach(System.out::println);
        assertFalse(bug);
    }

}
