import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day7_test {

    @Test
    public void day7_p1() throws IOException {
        Day7 align = new Day7(Utils.readFile("day7.txt"));
//        assertEquals(37, align.computeMinimumFuel());
        System.out.println(align.computeMinimumFuel());
    }

    @Test
    public void day7_p2() throws IOException {
        Day7 align = new Day7(Utils.readFile("day7.txt"));
//        assertEquals(168, align.computeMinimumFuelpart2());
        System.out.println(align.computeMinimumFuelpart2());
    }
}
