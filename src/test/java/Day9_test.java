import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day9_test {

    @Test
    public void day9_p1_test() throws IOException {
        SmokeBasin sb = new SmokeBasin(Utils.readFile("day9-test.txt"));
        assertEquals(15, sb.calculateLowSpots());
    }

    @Test
    public void day9_p1() throws IOException {
        SmokeBasin sb = new SmokeBasin(Utils.readFile("day9.txt"));
        System.out.println(sb.calculateLowSpots());
    }

    @Test
    public void day9_p2_test() throws IOException {
        SmokeBasin sb = new SmokeBasin(Utils.readFile("day9-test.txt"));
        sb.calculateLowSpots();
        assertEquals(1134 , sb.identifyBasins());
    }
    @Test
    public void day9_p2() throws IOException {
        SmokeBasin sb = new SmokeBasin(Utils.readFile("day9.txt"));
        sb.calculateLowSpots();
        System.out.println(sb.identifyBasins());
    }
}
