import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Snailfish {
    String s;
    Queue<String> input = new ArrayDeque<>();
//    Map<String, Integer> mapa = new HashMap<>();

    public Snailfish(List<String> input) {
        for (String s : input)
            this.input.add(s);
        s = this.input.poll();
//        mapa.put("0", 0);
//        mapa.put("1", 1);
//        mapa.put("2", 2);
//        mapa.put("3", 3);
//        mapa.put("4", 4);
//        mapa.put("5", 5);
//        mapa.put("6", 6);
//        mapa.put("7", 7);
//        mapa.put("8", 8);
//        mapa.put("9", 9);
//        mapa.put("a", 10);
//        mapa.put("b", 11);
//        mapa.put("c", 12);
//        mapa.put("d", 13);
//        mapa.put("e", 14);
//        mapa.put("f", 15);
//        mapa.put("g", 16);
//        mapa.put("h", 17);
//        mapa.put("i", 18);
    }

    public void add() {
        while (!input.isEmpty()) {
            _add(input.poll());

            reduceAll();
        }
    }

    private void reduceAll() {
        while(reduce()){}
    }

    private boolean reduce() {
        boolean test = false;

        if (_explode()>0) return true;
        if (_split()>0) return true;

        return false;
    }

    private int _split() {

        int count = 0;
        Pattern pattern = Pattern.compile("[0-9]{2,}");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
//            System.out.println("split");
            count++;
            _splitAtPos(matcher.start(), matcher.end());
        }
//        System.out.println(s);
        return count;
    }

    private void _splitAtPos(int start, int end) {
//        System.out.println("Split at position: "+start);

        int val = Integer.parseInt(s.substring(start, end));
        String leftT = s.substring(0,start);
        String rightT = s.substring(end);
//        System.out.println("\t new value: "+val);
        int left = val / 2;
        int right = (int) Math.ceil(val / 2.);
        s = leftT+"[" + left + "," + right + "]"+rightT;
    }

    private int _explode() {

        Pattern pattern = Pattern.compile("\\[[0-9]*,[0-9]*\\]");
        Matcher matcher = pattern.matcher(s);
        int count = 0;

        while (matcher.find()) {
            if (isInsideFour(matcher.start())) {
//                System.out.println("reduce");
//                System.out.println(s);
                count++;
                String t = s.substring(matcher.start()+1, matcher.end()-1);
                String[] v = t.split(",");
                int leftVal = Integer.parseInt(v[0]);
                int rightVal = Integer.parseInt(v[1]);
                String leftText = s.substring(0, matcher.start());
                String rightText = s.substring(matcher.end());
                leftText = addLeft(leftText, leftVal);
                rightText = addRight(rightText, rightVal);
                s = leftText+"0"+rightText;
                break;
            }
            else {
                String leftText = s.substring(0, matcher.start());
                String rightText = s.substring(matcher.end());
                String t = s.substring(matcher.start(), matcher.end()).replace(",","|");
                s=leftText+t+rightText;
            }
        }
        s=s.replaceAll("\\|", ",");
//        System.out.println(s);
        return count;
    }


    public static String addRight(String text, int right) {
        String[] tmp = text.split("");
        String n = "";
        int pos = -1;
        for (int i =0; i<text.length(); i++) {
            if ("0123456789".contains(tmp[i])) {
                n += tmp[i];
                tmp[i] = "";
            } else if (n.length() > 0) {
                pos = i - 1;
                break;
            }
        }
//        System.out.println("pos:" + pos);
        if (n.length() > 0 && pos > -1) {

            int newVal = right + Integer.parseInt(n);
            tmp[pos] = String.valueOf(newVal);
        }
        String newText = "";
        for (String c : tmp) {
            newText += c;
        }
        return newText;
    }

//    private String getKey(int val) {
//        for (Map.Entry<String, Integer> a : mapa.entrySet()) {
//            if (a.getValue() == val)
//                return a.getKey();
//        }
//        return "";
//    }

    public static String addLeft(String text, int left) {
        String[] tmp = text.split("");
        String n = "";
        int pos = -1;
        for (int i = text.length() - 1; i >= 0; i--) {
            if ("0123456789".contains(tmp[i])) {
                n = tmp[i] + n;
                tmp[i] = "";
            } else if (n.length() > 0) {
                pos = i + 1;
                break;
            }
        }
//        System.out.println("pos:" + pos);
        if (n.length() > 0 && pos > -1) {

            int newVal = left + Integer.parseInt(n);
            tmp[pos] = String.valueOf(newVal);
        }
        String newText = "";
        for (String c : tmp) {
            newText += c;
        }
        return newText;
    }

    private int getRightVal(int start) {
        String[] tmp = s.split("");
        String n = "";
        for (; start < s.length(); start++)
            if (tmp[start].equals(","))
                break;
        for (start++; start < s.length(); start++) {
            if (tmp[start].equals("]"))
                n += tmp[start];
            else
                break;
        }
        return Integer.parseInt(n);
    }

    private int getLeftVal(int start) {
        String[] tmp = s.split("");
        System.out.println(s);
        System.out.println(start + ": " + tmp[start + 1]);
        String n = "";
        for (int i = start + 1; i < s.length(); i++) {
            if (!tmp[i].equals(","))
                n += tmp[i];
            else
                break;
        }
        return Integer.parseInt(n);

    }

    private boolean isInsideFour(int startIndex) {
        char[] tmp = s.toCharArray();
        int count = 0;
        for (int i = 0; i < startIndex; i++) {
            if (tmp[i] == '[')
                count++;
            if (tmp[i] == ']')
                count--;
        }
        if (count >= 4)
            return true;
        else
            return false;
    }

    private void _add(String poll) {
        String tmp = "[" + s + "," + poll + "]";
        s = tmp;
    }

    public int calculate() {
        while (s.contains(",")) {
            _calculate();
        }
        return Integer.parseInt(s);
    }

    private void _calculate() {
        Pattern pattern = Pattern.compile("\\[[0-9]*,[0-9]*\\]");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            int sTmp = matcher.start();
            int start = sTmp;
            int eTmp = matcher.end();
            String left = "";
            String right = "";
            for (sTmp++; sTmp < s.length(); sTmp++) {
                if (s.charAt(sTmp) == ',')
                    break;
                else
                    left += String.valueOf(s.charAt(sTmp));
            }
            for (sTmp++; sTmp < s.length(); sTmp++) {
                if (s.charAt(sTmp) == ']')
                    break;
                else
                    right += String.valueOf(s.charAt(sTmp));
            }
            int val = Integer.parseInt(left) * 3 + Integer.parseInt(right) * 2;
            String tmp = "";
            tmp = s.substring(0, start) + val + s.substring(eTmp);
            s = tmp;
//            System.out.println(s);

        }
    }


}
