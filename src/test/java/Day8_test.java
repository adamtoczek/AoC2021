import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day8_test {

    @Test
    public void day8_p1_test() throws IOException {
        Digits d = new Digits(Utils.readFile("day8-test.txt"));
        assertEquals(26, d.countValveDigits());
        System.out.println(d.countValveDigits());
    }
    @Test
    public void day8_p1() throws IOException {
        Digits d = new Digits(Utils.readFile("day8.txt"));
        System.out.println(d.countValveDigits());
    }

    @Test
    public void day8_p2_test() throws IOException {
        Digits d = new Digits(Utils.readFile("day8-test.txt"));
        assertEquals(61229, d.sumControls());
        System.out.println(d.sumControls());
    }
    @Test
    public void day8_p2() throws IOException {
        Digits d = new Digits(Utils.readFile("day8.txt"));
        System.out.println(d.sumControls());
    }

    @Test
    public void verifyIdentifyG() {
        String two = "dfecg";
        String three = "degfb";
        String five = "daebg";
        String four = "aefb";

        String one = "fb";
        String a = "d";
        List<String> inputs = new ArrayList<>();

        inputs.add(two);
        inputs.add(three);
        inputs.add(five);
        assertEquals("g", Digits.identifyG(inputs, four,one,a));
    }
    @Test
    public void verifyIdentifyTwo() {
        String two = "dfecg";
        String three = "degfb";
        String five = "daebg";
        String four = "aefb";

        String one = "fb";
        String a = "d";
        List<String> inputs = new ArrayList<>();

        inputs.add(two);
        inputs.add(three);
        inputs.add(five);
        assertEquals("dfecg", Digits.identifyTwo(inputs, four,one,a));
    }
    @Test
    public void verifyIdentifyThree() {
        String two = "dfecg";
        String three = "degfb";
        String five = "daebg";
        String one = "fb";
        List<String> inputs = new ArrayList<>();

        inputs.add(two);
        inputs.add(three);
        inputs.add(five);
        assertEquals("degfb", Digits.identifyThree(inputs,one));
    }
    @Test
    public void verifyIdentifyFive() {
        String two = "dfecg";
        String three = "degfb";
        String five = "daebg";
        String one = "fb";
        List<String> inputs = new ArrayList<>();

        inputs.add(two);
        inputs.add(three);
        inputs.add(five);
        assertEquals("daebg", Digits.identifyFive(inputs,two, three));
    }

    @Test
    public void verifyPlus() {
        assertEquals("daebgf", Digits.plus("daebg", "fb"));
    }
    @Test
    public void VerifyMinus() {
        assertEquals("ab", Digits.minus("eagb", "eg"));
    }

    @Test
    public void verifyDecodeNumber() {
        String line = "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe";
        String[] p = line.split("\\|");
        List<String> d = Arrays.asList(p[0].trim().split(" "));
        List<String> c = Arrays.asList(p[1].trim().split(" "));
        System.out.println(Digits.decodeNumber(Digits.createMap(d),c));

    }
}
