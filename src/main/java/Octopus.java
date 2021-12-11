import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Octopus implements Comparable<Octopus>{
    int level;
    boolean flashOnThisStep = false;

    public Octopus(int level) {
        this.level = level;
    }

    public int willFlash(){
        return (flashOnThisStep) ? 1 : 0;
    }

    @Override
    public int compareTo(Octopus o) {
        return Integer.compare(level, o.level);
    }

    public void resetFlash() {
        flashOnThisStep = false;
    }

    public List<Point> increase(Point p) {
        List<Point> l = new ArrayList<>();
        if (level < 9 && !flashOnThisStep)
            level++;
        else if (!flashOnThisStep){
            flashOnThisStep = true;
            level = 0;
            l.add(new Point(p.x, p.y-1));
            l.add(new Point(p.x+1, p.y-1));
            l.add(new Point(p.x+1, p.y));
            l.add(new Point(p.x+1, p.y+1));
            l.add(new Point(p.x,p.y+1));
            l.add(new Point(p.x-1, p.y+1));
            l.add(new Point(p.x-1, p.y));
            l.add(new Point(p.x-1, p.y-1));
        }
        return l;
    }


}
