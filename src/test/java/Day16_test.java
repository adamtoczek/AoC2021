import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day16_test {
    @Test
    public void day16_p1_t1 () {
        List<String> input = Arrays.asList(new String[]{"8A004A801A8002F478"});
        PackeDecoder pd = new PackeDecoder(input);
        pd.decode();
        assertEquals(16, pd.getControlSum());
    }
    @Test
    public void day16_p1_t2 () {
        List<String> input = Arrays.asList(new String[]{"D2FE28"});
        PackeDecoder pd = new PackeDecoder(input);
        pd.decode();
        assertEquals(6, pd.getControlSum());
    }
    @Test
    public void day16_p1_t3 () {
        List<String> input = Arrays.asList(new String[]{"620080001611562C8802118E34"});
        PackeDecoder pd = new PackeDecoder(input);
        pd.decode();
        assertEquals(12, pd.getControlSum());
    }
    @Test
    public void day16_p1_t4 () {
        List<String> input = Arrays.asList(new String[]{"C0015000016115A2E0802F182340"});
        PackeDecoder pd = new PackeDecoder(input);
        pd.decode();
        assertEquals(23, pd.getControlSum());
    }
    @Test
    public void day16_p1_t5 () {
        List<String> input = Arrays.asList(new String[]{"A0016C880162017C3686B18A3D4780"});
        PackeDecoder pd = new PackeDecoder(input);
        pd.decode();
        assertEquals(31, pd.getControlSum());
    }
    @Test
    public void day16_p1 () throws IOException {
        PackeDecoder pd = new PackeDecoder(Utils.readFile("day16.txt"));
        pd.decode();
        System.out.println(pd.getControlSum());
    }

    @Test
    public void day16_p2 () throws IOException {
        PackeDecoder pd = new PackeDecoder(Utils.readFile("day16.txt"));
        pd.decode();
        System.out.println(pd.getPartTwo());
    }

}
