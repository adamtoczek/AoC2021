import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day17_test {
    @Test
    public void day17_p1() throws IOException {
        TrickShot ts = new TrickShot(Utils.readFile("day17.txt"));
        System.out.println(ts.getPartOne());
    }
    @Test
    public void day17_p2() throws IOException {
        TrickShot ts = new TrickShot(Utils.readFile("day17.txt"));
        System.out.println(ts.getPartTwo());
    }
    @Test
    public void day17_p1_t1() throws IOException {
        TrickShot ts = new TrickShot(Arrays.asList(new String[]{"target area: x=20..30, y=-10..-5"}));
        assertEquals(45, ts.getPartOne());
    }

    @Test
    public void day17_p1_t2() throws IOException {
        TrickShot ts = new TrickShot(Arrays.asList(new String[]{"target area: x=20..30, y=-10..-5"}));
        assertEquals(112, ts.getPartTwo());
    }
}
