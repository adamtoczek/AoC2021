import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class Day1_test {

    @Test
    public void day1p1() throws IOException {
        List<String> input = Utils.readFile("day1.txt");
        int count = 0;
        for (int i = 1; i<input.size(); i++) {
            if (Integer.parseInt(input.get(i)) > Integer.parseInt(input.get(i-1)))
                count++;
        }
        System.out.println(count);
    }

    @Test
    public void day1p2() throws IOException {
        List<String> input = Utils.readFile("day1.txt");
        int count = 0;
        for (int i = 3; i< input.size(); i++) {
            int a = Integer.parseInt(input.get(i));
            int b = Integer.parseInt(input.get(i-1));
            int c = Integer.parseInt(input.get(i-2));
            int d = Integer.parseInt(input.get(i-3));
            if (a+b+c > b+c+d)
                count++;
        }
        System.out.println(count);
    }

}
