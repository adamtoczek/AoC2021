import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day3_test {

    @Test
    public void day3_p1() throws IOException {
        Day3 day3 = new Day3(Utils.readFile("day3.txt"));

        System.out.println(day3.compute());
    }

    @Test
    public void day3_p2 () throws IOException {
        Day3 day3 = new Day3(Utils.readFile("day3.txt"));
        System.out.println(day3.computeOxygen() * day3.computeCO2());

    }
}
