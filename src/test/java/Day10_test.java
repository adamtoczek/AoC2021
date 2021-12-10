import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10_test {

    @Test
    public void day10_p1_test() throws IOException {
        SyntaxChecker sc = new SyntaxChecker(Utils.readFile("day10-test.txt"));
        assertEquals(26397, sc.checkInput());
    }

    @Test
    public void day10_p1() throws IOException {
        SyntaxChecker sc = new SyntaxChecker(Utils.readFile("day10.txt"));
        System.out.println(sc.checkInput());
    }

    @Test
    public void day10_p2_test() throws IOException {
        SyntaxChecker sc = new SyntaxChecker(Utils.readFile("day10-test.txt"));
        assertEquals(288957, sc.middleScoreCompletition());
    }
    @Test
    public void day10_p2() throws IOException {
        SyntaxChecker sc = new SyntaxChecker(Utils.readFile("day10.txt"));
        System.out.println(sc.middleScoreCompletition());
    }

    @Test
    public void verifyLineScoring() {
        assertEquals(288957, SyntaxChecker.getLineScore("}}]])})]"));
        assertEquals(5566, SyntaxChecker.getLineScore(")}>]})"));
        assertEquals(1480781, SyntaxChecker.getLineScore("}}>}>))))"));
    }

}
