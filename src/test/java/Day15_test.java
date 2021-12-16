import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day15_test {
    @Test
    public void day15_p1_test() throws IOException {
        Chiton c = new Chiton(Utils.readFile("day15-test.txt"));
        c.drawDistanceMap();
        c.drawTable();
        assertEquals(40, c.getResultP1());
//        c.calculate();
    }

    @Test
    public void day15_p1() throws IOException {
        Chiton c = new Chiton(Utils.readFile("day15.txt"));
        c.drawDistanceMap();
        c.drawTable();
        System.out.println(c.getResultP1());
    }

    @Test
    public void day15_p2_test() throws IOException {
        Chiton c = new Chiton(Utils.readFile("day15-test.txt"));
        c.multiplyinput();
        c.drawDistanceMap();
//        c.drawTable();
        assertEquals(315, c.getResultP1());
//        c.calculate();
    }

    @Test
    public void day15_p2() throws IOException {
        Chiton c = new Chiton(Utils.readFile("day15-q.txt"));
        c.multiplyinput();
        c.drawDistanceMap();
//        c.drawTable();
        System.out.println(c.getResultP1());
    }
}
