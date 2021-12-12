import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12_test {
    @Test
    public void day12_p1_test() throws IOException {
        CaveSystem cs = new CaveSystem(Utils.readFile("day12-test.txt"));
        assertEquals(226, cs.countPaths());
    }

    @Test
    public void day12_p1() throws IOException {
        CaveSystem cs = new CaveSystem(Utils.readFile("day12.txt"));
        System.out.println(cs.countPaths());
    }

    @Test
    public void day12_p2_test() throws IOException {
        CaveSystem cs = new CaveSystem(Utils.readFile("day12-test.txt"));
        assertEquals(3509, cs.countPathsPart2());
    }
    @Test
    public void day12_p2_test_short() throws IOException {
        CaveSystem cs = new CaveSystem(Utils.readFile("day12-test-shot.txt"));
        assertEquals(36, cs.countPathsPart2());
    }
    @Test
    public void day12_p2() throws IOException {
        CaveSystem cs = new CaveSystem(Utils.readFile("day12.txt"));
        System.out.println(cs.countPathsPart2());
    }
}
