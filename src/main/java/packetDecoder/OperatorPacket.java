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
//        System.out.println(version);
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
                        packets.add(p);
                        p.consume(nCode);

                    }
                    catch (Exception e) {
                        System.out.println("not added ver "+version);
                    }

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
                        packets.add(p);
                        p.consume(code);

                    }
                    catch (Exception e) {
                        System.out.println("not added ver "+version);
                    }
                }
            }

        }
        System.out.println("size "+packets.size());
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
        switch (type) {
            case 0: return sumPackets();
            case 1: return mulitplyPackets();
            case 2: return miniumPacket();
            case 3: return maximumPacket();
            case 5: return greaterThan();
            case 6: return lessThan();
            case 7: return equalThan();
        }
        return 0;
    }

    private long equalThan() {
        return (packets.get(0).calclulatePart2() == packets.get(1).calclulatePart2()) ? 1l : 0l;
    }

    private long lessThan() {
        System.out.println("size "+packets.size());
        return (packets.get(0).calclulatePart2() < packets.get(1).calclulatePart2()) ? 1l : 0l;
    }

    private long greaterThan() {
        return (packets.get(0).calclulatePart2() > packets.get(1).calclulatePart2()) ? 1l : 0l;
    }

    private long maximumPacket() {
        Queue<Long> q = new PriorityQueue<>();
        for (Packet p : packets)
            q.offer(p.calclulatePart2());
        Object[] b = q.toArray();
        return Long.parseLong(String.valueOf(b[b.length-1]));
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
}
