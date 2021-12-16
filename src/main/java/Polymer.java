import java.util.*;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Polymer {
    List<String> code = new LinkedList<>();
    String codeS;
    Map<String, Character> mapping = new HashMap<>();
    Map<String, Long> countPairs = new HashMap<>();
    private char rememberMe;


    public Polymer(List<String> input) {
        readCode(input.get(0));
        input.remove(0);
        input.remove(0);
        readMap(input);
    }

    private void readMap(List<String> input) {
        for (String s : input) {
            String[] tmp = s.split(" -> ");
            mapping.put(tmp[0], tmp[1].charAt(0));
        }
    }

    private void readCode(String s) {
        String[] c = s.split("");
        code.addAll(Arrays.asList(c));
        codeS = s;
        rememberMe = s.charAt(s.length() - 1);
    }

    public Long substract() {
        Map<String, Long> counts = new HashMap<>();
        for (String c : code) {
            if (counts.containsKey(c))
                counts.put(c, counts.get(c) + 1);
            else
                counts.put(c, 1L);
        }

        Collection<Long> tmp = counts.values();
        List<Long> t = tmp.stream().sorted().collect(Collectors.toList());
        return t.get(tmp.size() - 1) - t.get(0);
    }

    public void cycle(int nTimes) {
        for (int i = 0; i < nTimes; i++) {
            _cycle();
            System.out.println("Cycle " + i + " done");
        }

    }

    private void _cycle() {
        List<String> tmp = new LinkedList<>();
        tmp.add(code.get(0));
        for (int i = 1; i < code.size(); i++) {
            String c = code.get(i - 1) + code.get(i);

            tmp.add(String.valueOf(mapping.get(c)));
            tmp.add(code.get(i));
        }
        code.clear();
        code.addAll(tmp);
    }

    public void doPolymerization2nTimes(int n) {
        for (String s : mapping.keySet()) {
            countPairs.put(s, 0L);
        }
        for (String s : mapping.keySet()) {
            if (codeS.contains(s)) {
                countPairs.put(s, countPairs.get(s) + countPairs(s));
            }
        }


        for (int i = 0; i < n; i++) {
            HashMap<String, Long> toAdd = new HashMap<>();
            for (String pair : countPairs.keySet()) {
                if (countPairs.get(pair) > 0) {
                    char newChar = mapping.get(pair);
                    long currentValue = countPairs.get(pair);
                    String newPair1 = pair.substring(0, 1) + newChar;
                    String newPair2 = newChar + pair.substring(1);
                    if (toAdd.containsKey(newPair1))
                        toAdd.put(newPair1, toAdd.get(newPair1) + currentValue);
                    else
                        toAdd.put(newPair1, currentValue);
                    if (toAdd.containsKey(newPair2))
                        toAdd.put(newPair2, toAdd.get(newPair2) + currentValue);
                    else toAdd.put(newPair2, currentValue);
                }
            }
            for (String s : countPairs.keySet()) {
                countPairs.put(s, 0L);
            }
            for (String s : toAdd.keySet()) {
                countPairs.put(s, toAdd.get(s));
            }
        }
    }

    private int countPairs(String s) {
        int result = 0;
        for (int i = 0; i < codeS.length() - 1; i++) {
            if (codeS.substring(i, i + 2).equals(s)) result++;
        }
        return result;
    }

    public void displayCounts2() {
        HashMap<Character, Long> counts = new HashMap<>();
        for (String s : countPairs.keySet()) {
            char c = s.charAt(0);
            if (counts.containsKey(c))
                counts.put(c, counts.get(c) + countPairs.get(s));
            else counts.put(c, countPairs.get(s));
        }
        counts.put(rememberMe, counts.get(rememberMe) + 1);
        long max = counts.get('B'), min = counts.get('B');
        for (char c : counts.keySet()) {
            if (counts.get(c) > max) max = counts.get(c);
            if (counts.get(c) < min) min = counts.get(c);
        }
        System.out.println(counts);
        System.out.println(max - min);
    }


}
