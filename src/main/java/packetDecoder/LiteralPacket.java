package packetDecoder;

import java.util.Queue;

public class LiteralPacket extends Packet {
    public long code;

    public LiteralPacket(int version, int type) {
        super(version, type);
    }

    @Override
    public void consume(Queue<String> code) {
        boolean done = false;
        String s = "";
        while (!done) {
            if (code.poll().equals("0"))
                done = true;
            s+=code.poll();
            s+=code.poll();
            s+=code.poll();
            s+=code.poll();
        }
        this.code = Long.parseLong(s, 2);
    }

    @Override
    public int calculate() {
        return version;
    }

    @Override
    public long calclulatePart2() {
        return code;
    }

//    private void _consumeRest(Queue<String> code, int count) {
//
//        int i = 4-((count+6)%4);
//        System.out.println("consume rest "+i);
//        for (int n=0; n<i; n++)
//            code.poll();
//    }
}
