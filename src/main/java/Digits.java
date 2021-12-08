import java.util.*;
import java.util.stream.Collectors;

public class Digits {
    List<List<String>> controls = new ArrayList<>();
    List<List<String>> digits = new ArrayList<>();

    public Digits(List<String> input) {
        for (String line : input) {
//            System.out.println("Line: "+line);
            String s[] = line.split("\\|");
//            System.out.println(s[0] +" - "+ s[1]);
            controls.add(Arrays.asList(s[1].trim().split(" ")));
            digits.add(Arrays.asList(s[0].trim().split(" ")));
        }

    }

    public int countValveDigits() {
        int count = 0;
        for (List<String> l : controls) {
            for (String s : l) {
                int k = identifyNumer(s);
                if (k==1 || k==4 || k==7 || k==8)
                    count++;
            }
        }
        return count;
    }

    private int identifyNumer(String s) {
        switch (s.length()) {
            case 2 : return 1;
            case 3 : return 7;
            case 4 : return 4;
            case 7 : return 8;
            default: return 0;

        }
    }

    public int sumControls() {
        int count = 0;
        for (int i=0; i < digits.size(); i++) {
            count += decodeNumber(createMap(digits.get(i)), controls.get(i));
        }
        return count;
    }

    public static int decodeNumber(Map<String, Integer> map, List<String> d) {
        int power = 1000;
        int number = 0;
        for (String s : d) {
            number += map.get(sortString(s))*power;
            power = power / 10;
        }
        return number;
    }

    public static Map<String, Integer> createMap(List<String> inputs) {
        inputs = sortWords(inputs);
        Map<String, Integer> map = new HashMap<>();

        String one = getByLength(2, inputs).get(0);
        String seven = getByLength(3, inputs).get(0);
        String four = getByLength(4, inputs).get(0);
        String eight = getByLength(7, inputs).get(0);

        String a = identifyA(seven,one);
        String two = identifyTwo(getByLength(5, inputs), four, one, a);
        String three = identifyThree(getByLength(5, inputs), one);
        String five = identifyFive(getByLength(5, inputs), two, three);
        String nine = sortString(plus(five, one));
        String six = identifySix(getByLength(6, inputs), nine, one);
        String zero = identifyZero(getByLength(6, inputs), nine, one);

        map.put(zero, 0);
        map.put(one, 1);
        map.put(two, 2);
        map.put(three, 3);
        map.put(four, 4);
        map.put(five, 5);
        map.put(six, 6);
        map.put(seven, 7);
        map.put(eight, 8);
        map.put(nine, 9);

        return map;
    }

    public static String identifySix(List<String> inputs, String nine, String one) {
        inputs.remove(inputs.indexOf(nine));
        for (String s : inputs) {
            String tmp = minus(s, one);
            if (tmp.length() == 5)
                return s;
        }
        return null;
    }
    public static String identifyZero(List<String> inputs, String nine, String one) {
        inputs.remove(inputs.indexOf(nine));
        for (String s : inputs) {
            String tmp = minus(s, one);
            if (tmp.length() == 4)
                return s;
        }
        return null;
    }
    private static  List<String> sortWords(List<String> inputs) {
        List<String> out = new ArrayList<>();
        for (String s : inputs)
            out.add(sortString(s));
        return out;
    }

    public static String plus(String a, String b) {
        List<String> out = new ArrayList<>();
                out.addAll(Arrays.asList(a.split("")));
        List<String> tmp = Arrays.asList(b.split(""));

        for (String s : tmp) {
            if (!out.contains(s))
                out.add(s);
        }
        return out.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(""));
    }

    public static String identifyFive(List<String> inputs, String two, String three) {
        inputs.remove(inputs.indexOf(two));
        inputs.remove(inputs.indexOf(three));
        return inputs.get(0);
    }

    public static String identifyThree(List<String> inputs, String one) {
        for(String p : inputs) {
            String tmp = p;
            tmp = minus(tmp, one);
            if (tmp.length()==3)
                return p;
        }
        return null;
    }

    public static String identifyTwo(List<String> inputs, String four, String one, String a) {
        for (String tmp : inputs) {
            String p = tmp;
            p = minus(p, four);
            p = minus(p, one);
            p = minus(p, a);
            if (p.length()==2)
                return tmp;
        }
        return null;
    }

    public static  String identifyG(List<String> inputs, String four, String one, String a) {

        String p2 = inputs.get(0);
        String p5 = inputs.get(1);
        p2 = minus(p2, four);
        p2 = minus(p2, one);
        p2 = minus(p2, a);
        if (p2.length()==1)
            return p2;
        p5 = minus(p5, four);
        p5 = minus(p5, one);
        p5 = minus(p5, a);
        if (p5.length()==1)
            return p5;
        return null;
    }

    public static String minus(String word, String a) {
        List<String> tmpA = Arrays.asList(a.split(""));
        List<String> tmpW = Arrays.asList(word.split(""));
        String out="";
        for (String s : tmpW)
            if (!tmpA.contains(s))
                out+=s;
        return out;
    }

    private static  String identifyA(String seven, String one) {
        List<String> s = Arrays.asList(seven.split(""));
        List<String> o = Arrays.asList(one.split(""));
        for (String c : s) {
            if (!o.contains(c))
                return c;
        }

        return null;
    }

    private static String sortString(String text) {
        char tmp[] = text.toCharArray();
        Arrays.sort(tmp);
        return new String(tmp);
    }

    private static List<String> getByLength(int i, List<String> inputs) {
        List<String> out = new ArrayList<>();
        for (String s :inputs)
            if (s.length() == i)
                out.add(s);

            return out;
    }

}
