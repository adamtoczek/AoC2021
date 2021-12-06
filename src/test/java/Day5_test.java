import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day5_test {

    @Test
    public void day5_p1() throws IOException {
        Hydrothermal hydrothermal = new Hydrothermal(Utils.readFile("day5.txt"), false);
        System.out.println(hydrothermal.countIntersections(2));
    }

    @Test
    public void day5_p2() throws IOException {
        Hydrothermal hydrothermal = new Hydrothermal(Utils.readFile("day5.txt"), true);
        System.out.println(hydrothermal.countIntersections(2));
    }
}
