import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day4_test {

    @Test
    public void day4_p1_test () throws IOException {
        Bingo bingo = new Bingo(Utils.readFile("day4.txt"));
        System.out.println(bingo.proceedHits());
    }

    @Test
    public void day4_p2_test () throws IOException {
        Bingo bingo = new Bingo(Utils.readFile("day4.txt"));
        System.out.println(bingo.proceedHitsPart2());
    }
}
