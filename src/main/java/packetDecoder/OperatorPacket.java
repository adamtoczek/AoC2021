package packetDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class OperatorPacket extends Packet{
    int lType;
    List<Packet> packets = new ArrayList<>();

    public OperatorPacket(int version, int type) {
        super(version, type);
    }

    @Override
    public void consume(Queue<String> code) {
        this.lType = Integer.parseInt(code.poll());
        if (lType==0) { //length mode
            String s = "";
            for (int i=0; i < 15 ; i++)
                s+=code.poll();
            int length = Integer.parseInt(s, 2);
            String c="";
            for (int i=0; i< length; i++)
                c+=code.poll();
            Queue<String> nCode = generateCode(c);
            while (!nCode.isEmpty()) {
                Packet p = factory.createPacket(nCode);
                if (p!=null) {
                        packets.add(p);
                        p.consume(nCode);

                }
            }
        }
        else if (lType==1) { // count mode
            String s = "";
            for (int i=0; i < 11 ; i++)
                s+=code.poll();
            int numberOfPackages = Integer.parseInt(s, 2);
            for (int i=0; i<numberOfPackages; i++) {
                Packet p = factory.createPacket(code);
                if (p!=null)
                {
                        packets.add(p);
                        p.consume(code);
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

    @Override
    public long calclulatePart2() {
        long val = 0l;
        switch (type) {
            case 0: val = sumPackets();
            break;
            case 1: val = mulitplyPackets();
            break;
            case 2: val = miniumPacket();
            break;
            case 3: val = maximumPacket();
            break;
            case 5: val = greaterThan();
            break;
            case 6: val = lessThan();
            break;
            case 7: val = equalThan();
            break;
        }
//        System.out.println(type+" "+val);
        return val;
    }

    private long equalThan() {
        return (packets.get(0).calclulatePart2() == packets.get(1).calclulatePart2()) ? 1l : 0l;
    }

    private long lessThan() {
        return (packets.get(0).calclulatePart2() < packets.get(1).calclulatePart2()) ? 1l : 0l;
    }

    private long greaterThan() {
        return (packets.get(0).calclulatePart2() > packets.get(1).calclulatePart2()) ? 1l : 0l;
    }

    private long maximumPacket() {
        long max=0l;

        for (Packet p : packets)
            if (p.calclulatePart2() > max)
                max = p.calclulatePart2();

        return max;
    }

    private long miniumPacket() {
        Queue<Long> q = new PriorityQueue<>();
        for (Packet p : packets)
            q.offer(p.calclulatePart2());
        return q.poll();
    }

    private long mulitplyPackets() {
        long multi = 1l;
        for (Packet p : packets){
            multi*=p.calclulatePart2();
        }
        return multi;
    }

    private long sumPackets() {
        long sum = 0l;
        for (Packet p : packets)
            sum += p.calclulatePart2();
        return sum;
    }

    @Override
    public String toString() {
        String text =
         "OperatorPacket{" +
                "lType=" + lType +
                 ", version=" + version +
                 ", type=" + type +
                ", packets=";
        for (Packet p : packets)
            text+="\n"+p.toString();
                text  +=

                '}';

        return text;
    }
}
