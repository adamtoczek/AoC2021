import java.util.List;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class Hydrothermal {
    int[][] field;
    int sizeX=0;
    int sizeY = 0;

    public Hydrothermal(List<String> in, boolean drawDiagonal) {
        int minx=0;
        int maxx=0;
        int miny=0;
        int maxy=0;
        for(String s : in) {
            String[] p = s.split(" -> ");
            String[] a = p[0].split(",");
            String[] b = p[1].split(",");
            int x1 = Integer.parseInt(a[0]);
            int x2 = Integer.parseInt(b[0]);
            int y1 = Integer.parseInt(a[1]);
            int y2 = Integer.parseInt(b[1]);
            if (x1 < minx)
                minx = x1;
            if (x1 > maxx)
                maxx = x1;
            if (x2 < minx)
                minx = x2;
            if (x2> maxx)
                maxx = x2;

            if (y1 < miny)
                miny = y1;
            if (y1 > maxy)
                maxy = y1;
            if (y2 < miny)
                miny = y2;
            if (y2 > maxy)
                maxy = y2;

        }
        sizeX = maxx + Math.abs(minx)+1;
        sizeY = maxy + Math.abs(miny)+1;
        System.out.println("Filed x:"+sizeX+" y:"+sizeY);
        field = new int[sizeX][sizeY];
        //fill the field with 0s
        for (int i=0; i<sizeX; i++)
            for (int j =0; j<sizeY; j++)
                field[i][j] = 0;

            //proceed with input - draw lines
        in.stream().forEach(s ->{
            String[] p = s.split(" -> ");
            String[] a = p[0].split(",");
            String[] b = p[1].split(",");
            drawLine(Integer.parseInt(a[0])
                    , Integer.parseInt(a[1])
                    , Integer.parseInt(b[0])
                    , Integer.parseInt(b[1])
                    ,drawDiagonal);
        });
    }

    private void drawLine(int x1, int y1, int x2, int y2, boolean drawDiagonal) {
        if (x1==x2)
            drawHorizontal(x1,y1,y2);
        else if (y1==y2)
            drawVertical(y1, x1, x2);
        else if (drawDiagonal)
            drawDiag(x1, y1, x2, y2);
    }

    private void drawDiag(int x1, int y1, int x2, int y2) {
        int deltaX = x2-x1;
        int deltaY = y2-y1;
        int dirX = deltaX/Math.abs(deltaX);
        int dirY = deltaY/Math.abs(deltaY);
        int steps = Math.abs(deltaX)+1;
        for (int i = 0; i < steps; i++) {
            field[x1+(i*dirX)][y1+(i*dirY)]++;
        }
    }

    private void drawVertical(int y, int a, int b) {
        int x1 = min(a, b);
        int x2 = max(a, b);
        for (int x = x1; x<=x2; x++)
            field[x][y]++;
    }



    private void drawHorizontal(int x, int a, int b) {
        int y1 = min(a,b);
        int y2 = max(a,b);
        for (int y = y1; y<=y2; y++)
            field[x][y]++;
    }

    public int countIntersections(int min) {
        int count = 0;
        for (int i = 0; i < sizeX; i++)
            for (int j =0; j < sizeY; j++)
                if (field[i][j] >= min)
                    count++;
                return count;
    }

    public void proceedDiagonal() {

    }
}
