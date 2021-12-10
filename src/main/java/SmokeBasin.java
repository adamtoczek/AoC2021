import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SmokeBasin {
    int[][] field;
    int[][] lows;
    int sizeX;
    int sizeY;
    List<Basin> basins = new ArrayList<>();


    public SmokeBasin(List<String> input) {
        sizeX = input.get(0).length();
        sizeY = input.size();
        field = new int[sizeY][sizeX];
        lows = new int[sizeY][sizeX];
         for (int row = 0; row < sizeY; row++) {
             String[] s = input.get(row).split("");

             for (int x = 0; x < this.sizeX; x++) {
                 field[row][x] = Integer.parseInt(s[x]);
                 lows[row][x] = 0;
             }
         }
        identifyLowSpots();

    }

    private void identifyLowSpots() {
        for (int i = 0; i< sizeY; i++)
            for (int k = 0; k< sizeX; k++) {
                if (isLow(i, k)) {
                    lows[i][k] = 1;
                    basins.add(new Basin(i, k, field, sizeX, sizeY));
                }
            }

    }

    private boolean isLow(int row, int x) {
        int top = getTop(row, x);
        int left = getLeft(row, x);
        int right = getRight(row, x);
        int bottom = getBottom(row, x);

        if (field[row][x]<top && field[row][x] <left && field[row][x] <right && field[row][x] <bottom)
            return true;
        else
            return false;
    }

    private int getBottom(int row, int x) {
        if (row == sizeY-1)
            return field[row][x]+1;
        else
            return field[row+1][x];
    }

    private int getRight(int row, int x) {
        if (x==sizeX-1)
            return field[row][x]+1;
        else
            return field[row][x+1];
    }

    private int getLeft(int row, int x) {
        if (x==0)
            return field[row][x]+1;
        else
            return field[row][x-1];
    }

    private int getTop(int row, int x) {
        if (row ==0)
            return field[row][x]+1;
        else
            return field[row-1][x];

    }


    public int calculateLowSpots() {
        int sum = 0;
        for (int row = 0; row < sizeY; row++)
            for (int x = 0; x < sizeX; x++) {
                if (lows[row][x]==1) {
                    System.out.println("Low on "+row+":"+x+" value "+field[row][x]);
                    sum += field[row][x] + 1;
                }
            }
        return sum;
    }

    public int identifyBasins() {
        basins.stream().forEach(b -> b.findBasin());
        System.out.println(basins.size());
        for(Basin b : basins)
            System.out.println(b.x +":"+ b.y +" - "+b.getBasinSize());
        Collections.sort(basins);
        Collections.reverse(basins);

        return basins.get(0).getBasinSize() * basins.get(1).getBasinSize() * basins.get(2).getBasinSize();


    }



}
