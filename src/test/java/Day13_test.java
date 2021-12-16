import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13_test {
    @Test
    public void day13_p1_test() throws IOException {
        FoldingPaper fp = new FoldingPaper(Utils.readFile("day13-test.txt"));
        fp.fold();
        assertEquals(17, fp.countDots());
    }
    @Test
    public void day13_p1() throws IOException {
        FoldingPaper fp = new FoldingPaper(Utils.readFile("day13.txt"));
        fp.fold();
        System.out.println(fp.countDots());
    }
    @Test
    public void day13_p2() throws IOException {
        FoldingPaper fp = new FoldingPaper(Utils.readFile("day13.txt"));
        fp.foldAll();
        fp.showCode();
    }
}
