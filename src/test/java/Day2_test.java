import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class Day2_test {

    @Test
    public void day2_p1() throws IOException {
        Day2 day2 = new Day2();
        List<String> input = Utils.readFile("day2.txt");
        input.forEach(l -> {day2.parseLine(l);});

        System.out.println(day2.getResult());
    }

    @Test
    public void day2_p2() throws IOException {
        Day2 day2 = new Day2();
        List<String> input = Utils.readFile("day2.txt");
        input.forEach(l -> {day2.parseLinePart2(l);});

        System.out.println(day2.getResult());
    }
}
