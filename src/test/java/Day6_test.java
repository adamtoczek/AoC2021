import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6_test {
    @Test
    public void day6_p1() throws IOException {
        Fish f = new Fish(Utils.readFile("day6-test.txt"));
        assertEquals(5934l, f.cycleDays(80));
    }
    @Test
    public void day6_p2() throws IOException {
        Fish f = new Fish(Utils.readFile("day6.txt"));
        System.out.println(f.cycleDays(256));
    }
}
