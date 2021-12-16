package packetDecoder;

import java.util.Queue;

public class LiteralPacket extends Packet {
    public int code;

    public LiteralPacket(int version, int type) {
        super(version, type);
        System.out.println(version);
    }

    @Override
    public void consume(Queue<String> code) {
        int count = 0;
        boolean done = false;
        String s = "";
        while (!done) {
            String c = code.poll();
            if (c.equals("0"))
                done = true;
            s+=code.poll();
            s+=code.poll();
            s+=code.poll();
            s+=code.poll();
            count+=5;
        }
        this.code = Integer.parseInt(s, 2);
//        _consumeRest(code, count);
//        System.out.println("\tLiteral Code "+this.code);
    }

    @Override
    public int calculate() {
        return version;
    }

    private void _consumeRest(Queue<String> code, int count) {

        int i = 4-((count+6)%4);
        System.out.println("consume rest "+i);
        for (int n=0; n<i; n++)
            code.poll();
    }
}
