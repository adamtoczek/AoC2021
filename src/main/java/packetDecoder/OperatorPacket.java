package packetDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class OperatorPacket extends Packet{
    int lType;
    List<Packet> packets = new ArrayList<>();

    public OperatorPacket(int version, int type) {
        super(version, type);
        System.out.println(version);
    }

    @Override
    public void consume(Queue<String> code) {
        this.lType = Integer.parseInt(code.poll());
        if (lType==0) { //length mode
            String s = "";
            for (int i=0; i < 15 ; i++)
                s+=code.poll();
            int length = Integer.parseInt(s, 2);
//            System.out.println(" l: "+length);
            String c="";
            for (int i=0; i< length; i++)
                c+=code.poll();
            Queue<String> nCode = generateCode(c);
            while (!nCode.isEmpty()) {
                Packet p = factory.createPacket(nCode);
                if (p!=null) {
                    try {
                        p.consume(nCode);
                        packets.add(p);
                    }
                    catch (Exception e) {}

                }
            }
        }
        else if (lType==1) { // count mode
            String s = "";
            for (int i=0; i < 11 ; i++)
                s+=code.poll();
            int numberOfPackages = Integer.parseInt(s, 2);
//            System.out.println(" c: "+numberOfPackages);
            for (int i=0; i<numberOfPackages; i++) {
                Packet p = factory.createPacket(code);
                if (p!=null)
                {
                    try {
                        p.consume(code);
                        packets.add(p);
                    }
                    catch (Exception e) {}
                }
            }

        }

    }

    @Override
    public int calculate() {
        int sum = version;
        for (Packet p : packets)
            sum += p.calculate();
        return sum;
    }
}
