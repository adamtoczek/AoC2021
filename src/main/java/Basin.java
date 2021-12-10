import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Basin implements Comparable<Basin>{
    int y;
    int x;
    int sizeX;
    int sizeY;
    int[][] field;

    List<Point> points = new ArrayList<>();

    public Basin(int y, int x, int[][] field, int sizeX, int sizeY) {
        this.y = y;
        this.x = x;
        this.field = field;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        points.add(new Point(x, y));
    }

    public void findBasin () {
        boolean isMore = true;
        List<Point> tmp = new ArrayList<>();


        while (isMore) {
            for (Point p : points) {
                tmp.addAll(findPointsAround(p));
            }
            if (tmp.size()==0)
                isMore = false;
            points.addAll(tmp);
            points = points.stream().distinct().collect(Collectors.toList());
            tmp.clear();
        }

    }
    public int getBasinSize () {
        return points.size();
    }
    public List<Point> findPointsAround(Point p) {
        List<Point> out = new ArrayList<>();
        //top
        if (p.y>0)
            if (field[p.y-1][p.x]-field[p.y][p.x]>0 && field[p.y-1][p.x]!=9) {
                Point n = new Point(p.x, p.y-1);
                if (!points.contains(n) && !out.contains(n))
                    out.add(n);
            }
        //bottom
        if (p.y<sizeY-1)
            if (field[p.y+1][p.x]-field[p.y][p.x]>0 && field[p.y+1][p.x]!=9) {
                Point n = new Point(p.x, p.y+1);
                if (!points.contains(n) && !out.contains(n))
                    out.add(n);
            }
        //left
        if (p.x>0)
            if (field[p.y][p.x-1]-field[p.y][p.x]>0 && field[p.y][p.x-1]!=9) {
                Point n = new Point(p.x-1, p.y);
                if (!points.contains(n) && !out.contains(n))
                    out.add(n);
            }

        //right
        if (p.x<sizeX-1)
            if (field[p.y][p.x+1]-field[p.y][p.x]>0 && field[p.y][p.x+1]!=9) {
                Point n = new Point(p.x+1, p.y);
                if (!points.contains(n) && !out.contains(n))
                    out.add(n);
            }

        return out;
    }


    @Override
    public int compareTo(Basin o) {
        return Integer.compare(getBasinSize(), o.getBasinSize());
    }
}
