import packetDecoder.Packet;

import java.util.*;

public class PackeDecoder {
    Map<String, String> hex = new HashMap<>();
    Queue<String> code = new ArrayDeque<>();
    Packet packets;
    public PackeDecoder(List<String> input) {
        hex.put("0", "0000");
        hex.put("1", "0001");
        hex.put("2", "0010");
        hex.put("3", "0011");
        hex.put("4", "0100");
        hex.put("5", "0101");
        hex.put("6", "0110");
        hex.put("7", "0111");
        hex.put("8", "1000");
        hex.put("9", "1001");
        hex.put("A", "1010");
        hex.put("B", "1011");
        hex.put("C", "1100");
        hex.put("D", "1101");
        hex.put("E", "1110");
        hex.put("F", "1111");
        String[] s = input.get(0).split("");
        for (String c : s) {
            _addCode(c);
        }
        System.out.println(code);
    }

    private void _addCode(String c) {
        String[] t = hex.get(c).split("");
        for (String s : t) {
            code.add(s);
        }
    }

    public void decode() {
             packets = Packet.factory.createPacket(code);
             packets.consume(code);
    }

    public int getControlSum() {
        return packets.calculate();
    }

    public long getPartTwo() {
        return packets.calclulatePart2();

    }
}
