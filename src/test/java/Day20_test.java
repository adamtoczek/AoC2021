import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day20_test {
    @Test
    public void day20_p1_t1() throws IOException {
        Day20 d = new Day20(Utils.readFile("day20-test.txt"));
        d.cycle(2);
        assertEquals(35, d.getPartOne());
    }
    @Test
    public void day20_p1_t2() throws IOException {
        Day20 d = new Day20(Utils.readFile("day20-test.txt"));
        d.cycle(50);
        assertEquals(3351, d.getPartOne());
    }
    @Test
    public void day20_p1() throws IOException {
        Day20 d = new Day20(Utils.readFile("day20.txt"));
        d.cycle(2);
        System.out.println(d.getPartOne());
    }

    @Test
    public void day20_p2() throws IOException {
        Day20 d = new Day20(Utils.readFile("day20.txt"));
        d.cycle(50);
        System.out.println(d.getPartOne());
    }
}
