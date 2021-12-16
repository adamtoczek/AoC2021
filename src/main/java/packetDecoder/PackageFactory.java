package packetDecoder;

import java.util.Queue;

public class PackageFactory {
    public Packet createPacket(String version, String typeId) {

        int v = Integer.parseInt(version,2);
        int t = Integer.parseInt(typeId,2);
//        if (v==0 && t==0)
//            return null;

        switch (t) {
            case 4 : return new LiteralPacket(v, t);

            default: return new OperatorPacket(v, t);
        }
    }

    public Packet createPacket(Queue<String> code) {
        try {
            String ver = code.poll() + code.poll() + code.poll();
            String type = code.poll() + code.poll() + code.poll();
            return createPacket(ver, type);
        }
        catch (Exception e) {
            return null;
        }
    }
}
