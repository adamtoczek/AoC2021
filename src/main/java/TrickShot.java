import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TrickShot {
    List<Point> velocity = new ArrayList<>();
    Point targetMin;
    Point targetMax;
    int maxY=0;

    public TrickShot(List<String> input) {
        String[] s = input.get(0).replace("target area: x=","").replace(" y=","").replace("..","t").split(",");
        String[] x = s[0].split("t");
        String[] y = s[1].split("t");
        targetMin = new Point(Integer.parseInt(x[0]), Integer.parseInt(y[0]));
        targetMax = new Point(Integer.parseInt(x[1]), Integer.parseInt(y[1]));

        _findAllV();
    }

    private void _findAllV() {
        for (int x = 1; x <= targetMax.x; x++) {
            for (int y=targetMin.y; y<10000; y++) {
                if (_bullsEye(x, y)) {
//                    System.out.println("Bullseye x: " + x + "\ty: " + y);
                    velocity.add(new Point(x, y));
                    int tmp = _calculateMaxY(y);
                    maxY = (tmp > maxY) ? tmp : maxY;
                }
            }
        }
    }

    private int _calculateMaxY(int y) {
        int max = 0 ;
        int posY =0;
        while (y>0) {
            posY += y;
            y--;
            if (posY > max)
                max = posY;
        }
//        System.out.println("max "+max);
        return max;
    }

    private boolean _bullsEye(int x, int y) {
        int posX=0;
        int posY=0;
        while(!beyondTarget(posX, posY)) {
            if (_hit(posX, posY)) {
                return true;
            }
            posX +=x;
            if (x>0)
                x--;
            posY +=y;
            y--;
        }
        return false;
    }

    private boolean beyondTarget(int posX, int posY) {
        if (posX > targetMax.x)
            return true;
        if (posY < targetMin.y)
            return true;

        return false;
    }

    private boolean _hit(int posX, int posY) {
        if (posX>=targetMin.x && posX<=targetMax.x && posY >= targetMin.y && posY <=targetMax.y)
            return true;
        else
            return false;
    }

    public int getPartOne() {
        return maxY;
    }

    public int getPartTwo() {
        return velocity.size();
    }
}
