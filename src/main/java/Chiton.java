import java.util.List;

public class Chiton {
    private int[][] map;
    private int[][] dist;
    int sizeX = 0;
    int sizeY = 0;



    public Chiton(List<String> input) {
        sizeY = input.size();
        sizeX = input.get(0).length();

        System.out.println("size y "+sizeY);
        System.out.println("size x "+sizeX);
        map = new int[sizeY][sizeX];
        dist = new int[sizeY][sizeX];
        dist[0][0] = 0;
        for (int y = 0; y < sizeY; y++) {
            String[] s = input.get(y).split("");
            for (int x = 0; x < sizeX; x++) {
                map[y][x] = Integer.parseInt(s[x]);
            }
        }
    }

    public void drawDistanceMap() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if (x==0 && y==0)
                    x++;
                int top;
                try {
                    top = dist[y - 1][x];
                } catch (Exception e) {
                    top = dist[y][x - 1];
                }
                int left;
                try {
                    left = dist[y][x - 1];
                } catch (Exception e) {
                    left = dist[y - 1][x];
                }
                dist[y][x] = map[y][x]+ Math.min(top,left);
            }
        }
//        drawTable();
        while (redrawMap()>0) {}

    }

    private int redrawMap(){
        int[][] tmp = new int[sizeY][sizeX];
        int count = 0;
        for (int y=0; y<sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                //sprawdz czy taniej dojsc z lewej / prawej / dolu
                int oldCost = dist[y][x];
                int leftCost = oldCost;
                int rightCost = oldCost;
                int topCost = oldCost;
                int bottomCost = oldCost;
                try {
                    bottomCost = dist[y+1][x]+map[y][x];
                }
                catch (Exception e) {}
                try {
                    topCost = dist[y-1][x] + map[y][x];
                }
                catch (Exception e) {}
                try {
                    leftCost = dist[y][x-1] + map[y][x];
                }
                catch (Exception e) {}
                try {
                    rightCost = dist[y][x+1] + map[y][x];
                }
                catch (Exception e) {}
                int newCost = Math.min(leftCost, rightCost);
                newCost = Math.min(newCost, bottomCost);
                newCost = Math.min(newCost, topCost);
                tmp[y][x] = newCost;
                if (newCost < oldCost) {
                    count++;
                }
            }
        }
//        System.out.println("redraw "+count);
//        drawTable();
        dist = tmp.clone();
        return count;
    }


    public void drawTable() {
        for (int y =0; y<sizeY; y++){
            for (int x =0 ; x<sizeX; x++)
                System.out.print(dist[y][x]+"\t");
            System.out.println("");
        }
    }
    public void drawSource() {
        for (int y =0; y<sizeY; y++){
            for (int x =0 ; x<sizeX; x++)
                System.out.print(map[y][x]+"\t");
            System.out.println("");
        }
    }

    public int getResultP1() {
        return dist[sizeY-1][sizeX-1];
    }

    public void multiplyinput() {
        int tmpM[][] = map.clone();
        int size = sizeX;
        sizeX = size *5;
        sizeY = size *5;
        map = new int[sizeY][sizeX];
        dist = new int[sizeY][sizeX];
        dist[0][0] = 0;
        for (int y=0; y<size; y++) {
            for (int x=0; x<size; x++) {

                for (int k =0; k<5; k++)
                    for (int n = 0; n<5; n++) {
                        int val = increase(tmpM[y][x], k+n);

//                        System.out.println(val);
                        map[y+k*size][x+n*size] = val;
                    }
            }
        }
//        drawSource();
    }

    private int increase(int val, int n) {
        for (int i=1; i<=n; i++) {
            val++;
            if (val==10)
                val=1;
        }
        return val;
    }
}
