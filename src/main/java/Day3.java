import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day3 {
    int[][] table;
    List<String> input;
    int rows;
    int chars;
    String gamma ="";
    String epsilon = "";
    int gammaInt = 0;
    int epsilonInt = 0;

    public Day3(List<String> in) {
        input = in;
        rows = in.size();
        chars = in.get(0).length();
        table = new int[rows][chars];

        for (int i = 0; i<rows; i++) {
            String c[] = in.get(i).split("");
            for (int j=0; j<chars; j++) {

                table[i][j] = Integer.parseInt(c[j]);
            }
        }

    }

    public int compute(){
        for (int i = 0 ; i< chars; i++) {
            int sum = 0;
            for (int j=0; j<rows; j++) {
                sum += table[j][i];
            }
            if (sum > rows/2) {
                gamma +="1";
                epsilon +="0";
            }
            else {
                gamma +="0";
                epsilon +="1";
            }

        }
        System.out.println(gamma);
        System.out.println(epsilon);
        gammaInt = Integer.parseInt(gamma,2);
        epsilonInt = Integer.parseInt(epsilon,2);
        return gammaInt * epsilonInt;
    }

    public int computeOxygen() {
        System.out.println("compute ox");
        List<Integer[]> list = parseInput();
        int index= 0 ;
        while (list.size() > 1) {
            System.out.println(list.size());
            list = filterOut(list, true, index);
            index++;
        }

        String k = "";
        for (int i = 0; i<list.get(0).length; i++)
            k += list.get(0)[i];
        return Integer.parseInt(k, 2);
    }

    private List<Integer[]> parseInput() {
        List<Integer[]> res = new ArrayList<>();
        for (String s : input) {
            String j[] = s.split("");
            Integer[] a = new Integer[j.length];
            for (int i = 0; i < j.length; i++) {
                a[i] = Integer.parseInt(j[i]);
            }
            res.add(a);
        }

        return res;
    }

    public int computeCO2() {
        System.out.println("Compute co2");
        List<Integer[]> list = parseInput();
        int index = 0;
        while (list.size() > 1) {
            System.out.println(list.size());
            list = filterOut(list, false, index);
            index++;
        }
        String k = "";
        for (int i = 0; i<list.get(0).length; i++)
            k += list.get(0)[i];
        return Integer.parseInt(k, 2);
    }

    private List<Integer[]> filterOut(List<Integer[]> list, boolean isOxygen, int index) {
        int a =  isOxygen? getMostCommonFirstBit(list, index) : getLeastCommonFirstBit(list, index);
        List<Integer[]> res = new ArrayList<>();
        System.out.println("bit: "+ a);
        for (int i =0; i < list.size(); i++) {
            if (list.get(i)[index]==a)
                res.add(list.get(i));
        }
        printList(res);
        return res;
    }

    private void printList(List<Integer[]> list) {
        for (Integer[] a : list) {
            System.out.print("[");
            for (int i : a) {
                System.out.print(""+i);
            }
            System.out.println("]");
        }
    }

    private int getMostCommonFirstBit(List<Integer[]> list, int index) {
        System.out.println("getMostCommonBit on "+index);
        int sum0 = 0;
        int sum1 = 0;
        for (Integer[] a : list) {
            if (a[index]==1)
                sum1 ++;
            else
                sum0 ++;
        }
        if (sum1 >= sum0)
            return 1;
        else
            return 0;
    }

    private int getLeastCommonFirstBit(List<Integer[]> list, int index) {
        System.out.println("getLeastCommonBit on "+index);
        int sum0 = 0;
        int sum1 = 0;
        for (Integer[] a : list) {
            if (a[index]==1)
                sum1 ++;
            else
                sum0 ++;
        }
        if (sum0 <= sum1)
            return 0;
        else
            return 1;
    }
}
