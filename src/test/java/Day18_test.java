import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day18_test {

    @Test
    public void day18_p1_t1() {
        List<String> input = new ArrayList<>();
        input.add("[1,1]");
        input.add("[2,2]");
        input.add("[3,3]");
        input.add("[4,4]");
        input.add("[5,5]");
        input.add("[6,6]");
        Snailfish s = new Snailfish(input);
//        s.add();
        System.out.println(s.s);
        s.calculate();
    }

    @Test
    public void day18_p1_t2() throws IOException {
        Snailfish s = new Snailfish(Utils.readFile("day18-test.txt"));
        s.addAll();
        System.out.println(s.s);

        assertEquals(4140, s.calculate());
    }

    @Test
    public void verifyAddLeft() {
        assertEquals("[[234,534],45][345,23]]]],", Snailfish.addLeft("[[234,534],45][345,10]]]],", 13));

    }

    @Test
    public void verifyAddRight() {
        assertEquals("],]]],],18],", Snailfish.addRight("],]]],],5],", 13));
        assertEquals("],]]],],18]", Snailfish.addRight("],]]],],5]", 13));
    }

    @Test
    public void a() {
        String s = "[[35,5]]";
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(s);
        matcher.find();
        System.out.println(matcher.start());
        System.out.println(matcher.end());
        System.out.println(s.substring(matcher.start(), matcher.end()));
        System.out.println(s.substring(0, matcher.start()));
        System.out.println(s.substring(matcher.end()));
    }

    @Test
    public void day18_p1() throws IOException {
        Snailfish s = new Snailfish(Utils.readFile("day18.txt"));
        s.addAll();
        System.out.println(s.calculate());
    }
    @Test
    public void day18_p2_test() throws IOException {
        Snailfish s = new Snailfish(Utils.readFile("day18-test.txt"));
        System.out.println(s.calculatePart2());
    }

    @Test
    public void day18_p2() throws IOException {
        Snailfish s = new Snailfish(Utils.readFile("day18.txt"));
        System.out.println(s.calculatePart2());
    }
}
