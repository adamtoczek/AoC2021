import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day14_test {

    @Test
    public void day14_p1_test() throws IOException {
        Polymer p = new Polymer(Utils.readFile("day14-test.txt"));
        p.cycle(10);
        assertEquals(1588L, p.substract());
    }

    @Test
    public void day14_p1() throws IOException {
        Polymer p = new Polymer(Utils.readFile("day14.txt"));
        p.cycle(10);
        System.out.println(p.substract());
    }

    @Test
    public void day14_p2_test() throws IOException {
        Polymer p = new Polymer(Utils.readFile("day14-test.txt"));
        p.doPolymerization2nTimes(40);
        p.displayCounts2();

    }

    @Test
    public void day14_p2() throws IOException {
        Polymer p = new Polymer(Utils.readFile("day14.txt"));
        p.doPolymerization2nTimes(40);
        p.displayCounts2();
    }
}
