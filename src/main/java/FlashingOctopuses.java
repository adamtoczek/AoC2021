import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FlashingOctopuses {
    Map<Point, Octopus> octopuses = new HashMap<>();

    public FlashingOctopuses(List<String> input) {
        for (int y = 0; y< input.size(); y++) {
            String[] s = input.get(y).split("");
            for (int x =0; x<s.length; x++)
                octopuses.put(new Point(x,y), new Octopus(Integer.parseInt(s[x])));
        }
    }

    public int countFlashes(int i) {
        int sum = 0;
        for (int step = 1; step <= i; step++) {
            sum += calculateStep();
            System.out.println("Step "+step);
            printOctopuses();
            clearFlashes();
        }
        return sum;
    }

    private void  printOctopuses() {
        for (int y=0; y<10; y++) {
            for (int x = 0; x < 10; x++)
                System.out.print(octopuses.get(new Point(x, y)).level);
            System.out.println("");
        }
    }

    private void clearFlashes() {
        octopuses.forEach((Point p, Octopus o) ->o.resetFlash());
    }

    private int calculateStep() {
        int sum = 0;
        List<Point> inducted = new ArrayList<>();
        octopuses.forEach((Point p, Octopus o) -> inducted.addAll(o.increase(p)));
        while (inducted.size()>0) {
            Point p = inducted.get(0);
            inducted.remove(0);
            try {
                inducted.addAll(octopuses.get(p).increase(p));
            }
            catch (NullPointerException e) {};
        }

        for (Octopus o : octopuses.values()) {
            sum += o.willFlash();
        }

        return sum;
    }

    public int stepsToFullFlash() {
        int i = 1;
        while (calculateStep()!=100) {
            i++;
            clearFlashes();
        }
        return i;
    }
}
