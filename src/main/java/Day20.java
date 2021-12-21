import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day20 {
    char[] map = new char[512];
    Map<Point, Character> obraz = new HashMap<>();
    Point topLeft;
    Point rightBottom;
    char infinitCh = '.';
    public Day20(List<String> input) {
        char[] tmp = input.get(0).toCharArray();
        for (int i=0; i < tmp.length; i++) {
            map[i] = tmp[i];
        }
        input.remove(0);
        input.remove(0);
        int sizeX = input.get(0).length();
        for (int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x < sizeX; x++) {
                obraz.put(new Point(x,y), line.charAt(x));
            }
        }
        topLeft = new Point(0,0);
        rightBottom = new Point(sizeX-1, input.size()-1);
    }

    public void cycle(int n) {
        for (int i=0; i<n; i++)
            _cycle(i);
    }
    private char getInfinitChar(int cycle) {
        char inf = '.';
        for (int i=0; i<cycle; i++) {
            String s = (inf=='.') ? "0" : "1";
            inf = map[Integer.parseInt(s+s+s+s+s+s+s+s+s, 2)];
        }
        return inf;
    }
    private void _cycle(int i) {
        infinitCh=getInfinitChar(i);
        _drawCircle(infinitCh);
        _drawCircle(infinitCh);

        Map<Point, Character> tmp = new HashMap<>();
        for (Map.Entry<Point, Character> e : obraz.entrySet()) {
            Point p = e.getKey();
            tmp.put(p,map[getBinaryAround(p, infinitCh)]);
        }

        obraz.clear();
        obraz.putAll(tmp);
    }

    private int getBinaryAround(Point p, char infinitCh) {
        int x = p.x;
        int y=p.y;
        String s1 = decodeChar(obraz.get(new Point(x-1,y-1)), infinitCh);
        String s2 = decodeChar(obraz.get(new Point(x,y-1)), infinitCh);
        String s3 = decodeChar(obraz.get(new Point(x+1,y-1)), infinitCh);
        String s4 = decodeChar(obraz.get(new Point(x-1,y)), infinitCh);
        String s5 = decodeChar(obraz.get(new Point(x,y)), infinitCh);
        String s6 = decodeChar(obraz.get(new Point(x+1,y)), infinitCh);
        String s7 = decodeChar(obraz.get(new Point(x-1,y+1)), infinitCh);
        String s8 = decodeChar(obraz.get(new Point(x,y+1)), infinitCh);
        String s9 = decodeChar(obraz.get(new Point(x+1,y+1)), infinitCh);
        String res = (s1+s2+s3+s4+s5+s6+s7+s8+s9).replace(".","0").replace("#","1");

        return Integer.parseInt(res, 2);
    }

    private String decodeChar(Character character, char infinitCh) {
        if (character==null)
            return String.valueOf(infinitCh);
        else
            return String.valueOf(character);
    }

    private void _drawCircle(char c) {
        int xStart = topLeft.x-1;
        int xEnd = rightBottom.x+1;
        int yStart = topLeft.y-1;
        int yEnd = rightBottom.y+1;

        for (int x=xStart; x<=xEnd; x++) {
            obraz.put(new Point(x, yStart), c);
            obraz.put(new Point(x, yEnd), c);
        }
        for (int y = topLeft.y; y<yEnd; y++) {
            obraz.put(new Point(xStart, y), c);
            obraz.put(new Point(xEnd, y), c);
        }
        topLeft = new Point(xStart, yStart);
        rightBottom = new Point(xEnd, yEnd);

    }

    public int getPartOne() {
        return (int) obraz.values().stream().filter(c -> c == '#').count();

    }
}
