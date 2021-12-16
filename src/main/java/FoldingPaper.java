import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FoldingPaper {

    List<Pair<String, Integer>> instructions = new ArrayList<>();
    List<Point> points = new ArrayList<>();

    public FoldingPaper(List<String> input) {
        int del = input.indexOf("");
        for (int i = 0; i<del; i++) {
            String[] tmp = input.get(i).split(",");
            points.add(new Point(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])));
        }
        for (int i = del+1; i < input.size(); i++) {
            String[] tmp = input.get(i).replace("fold along ", "").split("=");
            instructions.add(new Pair<>(tmp[0], Integer.parseInt(tmp[1])));
        }

    }

    public void fold() {
        Pair<String, Integer> ins = instructions.get(0);
        instructions.remove(0);
        switch (ins.getKey()) {
            case "x" : foldOnX(ins.getValue());
            break;
            case "y" : foldOnY(ins.getValue());
        }

    }

    private void foldOnX(int x) {
        for (int i=0; i < points.size(); i++) {
            Point tmp = points.get(i);
            if (tmp.x >x)
                tmp.x = tmp.x - 2*(tmp.x-x);
            points.set(i, tmp);
        }
    }

    private void foldOnY(int y) {
        for (int i=0; i < points.size(); i++) {
            Point tmp = points.get(i);
            if (tmp.y >y)
                tmp.y = tmp.y - 2*(tmp.y-y);
            points.set(i, tmp);
        }
    }

    public int countDots() {
        makeDistinct();
        return points.size();
    }

    private void makeDistinct() {
        List<Point> tmp = new ArrayList<>();
        for (Point p : points) {
            if (!tmp.contains(p))
                tmp.add(p);
        }
        points.clear();
        points.addAll(tmp);
    }

    public void foldAll() {
        while (instructions.size() > 0)
            fold();
    }


    public void showCode() {
        int sizeX = 0;
        int sizeY = 0;
        for(Point p : points) {
            if (sizeX < p.x)
                sizeX = p.x;
            if (sizeY < p.y)
                sizeY = p.y;
        }
        sizeX++;
        sizeY++;
        boolean[][] table = new boolean[sizeX][sizeY];
        for (Point p : points) {
            table[p.x][p.y] = true;
        }

        for (int y=0; y<sizeY; y++) {
            for (int x=0; x<sizeX; x++)
                System.out.print((table[x][y])?"#" : " ");
            System.out.println("");
        }

    }
}
