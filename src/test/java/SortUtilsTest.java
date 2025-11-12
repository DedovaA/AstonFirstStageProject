import aston.first_stage_project.Bus;
import aston.first_stage_project.SortUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class SortUtilsTest {
    private List<Integer> list;

    @Before
    public void init() {
        list = new ArrayList<>(Arrays.asList(5, 1, 4, 2, 3, 8, 9, 7, 6));
    }

    @Test
    public void testSortWithInteger() {
        List<Integer> result = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        SortUtils.quickSort(list);

        assertEquals(result, list);
    }

    @Test(expected = NullPointerException.class)
    public void testSortWithNullList() {
        SortUtils.quickSort(null);
    }

    @Test
    public void testSortWithBus() {
        Bus b1 = new Bus.BusBuilder()
                .setNumber("N456AS")
                .setModel("Volvo")
                .setRun(22134)
                .build();

        Bus b2 = new Bus.BusBuilder()
                .setNumber("B845CX")
                .setModel("Toyota")
                .setRun(12356)
                .build();

        Bus b3 = new Bus.BusBuilder()
                .setNumber("U154JH")
                .setModel("Honda")
                .setRun(84562)
                .build();

        Bus b4 = new Bus.BusBuilder()
                .setNumber("J781GH")
                .setModel("UAZ")
                .setRun(5623)
                .build();

        Bus b5 = new Bus.BusBuilder()
                .setNumber("N456AS")
                .setModel("Honda")
                .setRun(98752)
                .build();

        List<Bus> list = new ArrayList<>(Arrays.asList(b1, b2, b3, b4, b5));
        List<Bus> result = List.of(b2, b4, b5, b1, b3);

        SortUtils.quickSort(list);

        assertEquals(result, list);
    }
}
