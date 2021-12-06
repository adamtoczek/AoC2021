import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fish {
    List<Integer> hive = new ArrayList<>();
    Long[] fish = new Long[]{0l,0l,0l,0l,0l,0l,0l,0l,0l};


    public Fish(List<String> input) {
        List<String> in = Arrays.asList(input.get(0).split(","));
        in.stream().forEach(s -> {
            int v = Integer.parseInt(s);
            fish[v]++;
        });
    }

    private void cycleDay() {
        Long born = fish[0];
        Long[] tmp = new Long[]{0l,0l,0l,0l,0l,0l,0l,0l,0l};
        tmp[0] = fish[1];
        tmp[1] = fish[2];
        tmp[2] = fish[3];
        tmp[3] = fish[4];
        tmp[4] = fish[5];
        tmp[5] = fish[6];
        tmp[6] = fish[7]+born;
        tmp[7] = fish[8];
        tmp[8] = born;
        fish = tmp;
    }

    public Long cycleDays(int days) {
        for (int i=0; i<days; i++) {
            System.out.println("day "+i);
            cycleDay();
        }
        return sum();
    }

    private Long sum() {
        Long x = 0l;
        for (int i=0; i<fish.length; i++)
            x+=fish[i];
        return x;
    }
}
