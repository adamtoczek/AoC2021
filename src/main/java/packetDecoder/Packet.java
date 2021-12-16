package packetDecoder;

import java.util.ArrayDeque;
import java.util.Queue;

public abstract class Packet {
    int version;
    int type;
    public static PackageFactory factory = new PackageFactory();

    public Packet(int version, int type) {
        this.version = version;
        this.type = type;
    }

    public abstract void consume(Queue<String> code);
    public abstract int calculate();

    public static Queue<String> generateCode(String c) {
        String[] t = c.split("");
        Queue<String> code = new ArrayDeque<>();
        for (String a : t)
            code.add(a);
        return code;
    }

    public abstract long calclulatePart2();
}
