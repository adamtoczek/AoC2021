import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Bingo {

    List<BingoBoard> boards = new ArrayList<>();
    List<Integer> hits = new ArrayList<>();

    public Bingo(List<String> in) {
        Arrays.stream(in.get(0).split(",")).forEach(x -> hits.add(Integer.parseInt(x)));
        in.remove(0);
        while (in.size() > 0) {
            BingoBoard b = new BingoBoard();
            b.fillRow(in.get(1));
            b.fillRow(in.get(2));
            b.fillRow(in.get(3));
            b.fillRow(in.get(4));
            b.fillRow(in.get(5));
            boards.add(b);
            in.remove(0);
            in.remove(0);
            in.remove(0);
            in.remove(0);
            in.remove(0);
            in.remove(0);
        }
    }

    public int proceedHits() {

        AtomicBoolean win = new AtomicBoolean(false);
        AtomicInteger winCalc = new AtomicInteger();
        while (!win.get() && hits.size() > 0) {
            int hit = hits.get(0);
            System.out.println("Hit with " + hit);
            hits.remove(0);
            boards.stream().forEach(b -> {
                b.markHit(hit);
                if (b.win) {
                    win.set(true);
                    winCalc.set(b.winCalc);
                }
            });

        }
        return winCalc.intValue();
    }

    public int proceedHitsPart2() {
        while (hits.size() > 0) {
            int hit = hits.get(0);
            hits.remove(0);
            System.out.print("Hit with " + hit +"\t");
            //boards.stream().forEach(b->b.getWin());

            for (BingoBoard b : boards) {
                b.markHit(hit);
                b.getWin();
                if (b.win){
                    if ((int) boards.stream().filter(p->p.win).count() == boards.size())
                        return b.winCalc;
                }
            }
            System.out.println("");
        }
        return 0;
    }
}
