import java.util.Arrays;
import java.util.List;

public class Day7 {
    int[] crabs;
    int posMin =0 ;
    int posMax =0 ;
    public Day7(List<String> input) {
        List<String> in = Arrays.asList(input.get(0).split(","));
        posMin = Integer.parseInt(in.get(0));
        posMax = posMin;
        crabs = new int[in.size()];
        for (int i =0; i< in.size(); i++) {
            crabs[i] = Integer.parseInt(in.get(i));
            if (crabs[i] < posMin)
                posMin = crabs[i];
            if (crabs[i] > posMax)
                posMax = crabs[i];
        }
    }

    public int computeMinimumFuel() {
        int minF = computeFuelAtPosition(posMin);
        for (int pos = posMin; pos <=posMax; pos++) {
            if (computeFuelAtPosition(pos) < minF)
                minF = computeFuelAtPosition(pos);
        }
        return minF;

    }

    private int computeFuelAtPosition(int pos) {
        int sum =0;
        for (int i : crabs)
            sum += Math.abs(pos - i);
        return sum;
    }

    public int computeMinimumFuelpart2() {
        int minF = computeProgressionFuelAtPosition(posMin);
        for (int pos = posMin; pos <=posMax; pos++) {
            if (computeProgressionFuelAtPosition(pos) < minF)
                minF = computeProgressionFuelAtPosition(pos);
        }
        return minF;
    }

    private int computeProgressionFuelAtPosition(int pos) {
        int sum =0;
        for (int i : crabs)
            sum += sumaCiagu( Math.abs(pos - i));
        return sum;
    }

    private int sumaCiagu(int n) {
        return (1+n)*n/2;
    }
}
