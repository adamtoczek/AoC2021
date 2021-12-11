import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11_test {
    @Test
    public void day11_p1_test() throws IOException {
        FlashingOctopuses fo = new FlashingOctopuses(Utils.readFile("day11-test.txt"));
        assertEquals(1656, fo.countFlashes(100));
    }

    @Test
    public void day11_p1() throws IOException {
        FlashingOctopuses fo = new FlashingOctopuses(Utils.readFile("day11.txt"));
        System.out.println(fo.countFlashes(100));
    }

    @Test void day11_p2_test() throws IOException {
        FlashingOctopuses fo = new FlashingOctopuses(Utils.readFile("day11-test.txt"));
        assertEquals(195, fo.stepsToFullFlash());
    }
    @Test
    public void day11_p2() throws IOException {
        FlashingOctopuses fo = new FlashingOctopuses(Utils.readFile("day11.txt"));
        System.out.println(fo.stepsToFullFlash());
    }
}
