import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaveSystem {
    Map<String,Cave> caves = new HashMap<>();
    List<List<Cave>> paths = new ArrayList<>();

    public CaveSystem(List<String> input) {
        for (String s : input) {
            String[] p = s.split("-");
            Cave p1 = new Cave(p[0]);
            Cave p2 = new Cave(p[1]);
            if (!caves.containsValue(p1))
                caves.put(p[0], p1);
            if (!caves.containsValue(p2))
                caves.put(p[1], p2);

            Cave c1 = caves.get(p[0]);
            Cave c2 = caves.get(p[1]);
            c1.addConnection(c2);
            c2.addConnection(c1);
        }

        Cave start = caves.get("start");
        for (Cave c : start.connects) {
            List<Cave> l = new ArrayList<>();
            l.add(start);
            l.add(c);
            paths.add(l);
        }


    }

    public boolean generatePaths() {
        List<List<Cave>> tmp = new ArrayList<>();
        for (List<Cave> p : paths) {
            if (!pathEnded(p)) {
                Cave last = p.get(p.size() - 1);
                List<Cave> nTemp = new ArrayList<>();

                for (Cave c : last.connects) {
                    if (!c.isLowerCase || !p.contains(c))
                        nTemp.add(c);
                }

                for (Cave c : nTemp) {
                    List<Cave> n = new ArrayList<>();
                    n.addAll(p);
                    n.add(c);
                    tmp.add(n);
                }

            }
            else if (pathCorrectEnd(p))
                tmp.add(p);
        }
        paths = tmp;
        return allPathsEnded();
    }

    private boolean pathCorrectEnd(List<Cave> p) {
        if (p.get(p.size()-1).getId().equals("end"))
            return true;
        else
            return false;
    }

    private boolean pathEnded(List<Cave> path) {
        Cave last = path.get(path.size()-1);
        Cave preLast = path.get(path.size()-2);
        if (last.getId().equals("end")) {
            return true;
        }
        else if (last.connects.size()==1 && last.connects.get(0).isLowerCase && last.connects.get(0).equals(preLast)) {
            return true;
        }
        else
            return false;
    }

    private boolean allPathsEnded() {
        for (List<Cave> l : paths) {
            if (!pathEnded(l))
                return false;
        }
        return true;
    }



    public int countPaths() {
        while (!generatePaths()) {
        }
        return paths.size();
    }

    public int countPathsPart2() {
        while (!generatePathsPart2()) {
        }
        return paths.size();
    }

    private boolean generatePathsPart2() {
        List<List<Cave>> tmp = new ArrayList<>();
        for (List<Cave> p : paths) {
            if (pathEndedPart2(p))
                tmp.add(p);
            else {
                    Cave last = p.get(p.size() - 1);
                    List<Cave> nTemp = new ArrayList<>();

                    for (Cave c : last.connects) {
                        if (isEligible(c, p))
                            nTemp.add(c);
                    }

                    for (Cave c : nTemp) {
                        List<Cave> n = new ArrayList<>();
                        n.addAll(p);
                        n.add(c);
                        tmp.add(n);
                    }
                }
        }
        paths = tmp;
        return allPathsEndedPart2();
    }

    private boolean isEligible(Cave c, List<Cave> p) {
        if (!c.isLowerCase) //big cave
            return true;
        else if (c.getId().equals("start"))
            return false;
        else if (!p.contains(c))
            return true;
        else if (pathHasNoDoubleSmallCave(p))
            return true;
        else {
            return false;
        }
    }


    private boolean allPathsEndedPart2() {
        for (List<Cave> p : paths)
            if (!pathEndedPart2(p))
                return false;
        return true;
    }

    private boolean pathEndedPart2(List<Cave> p) {
        if (p.get(p.size()-1).getId().equals("end"))
            return true;

        return false;
    }

    private boolean pathHasNoDoubleSmallCave(List<Cave> p) {
        List<Cave> tmp = new ArrayList<>();
        for (Cave c : p ) {
            if (c.isLowerCase) {
                if (tmp.contains(c))
                    return false;
                else
                    tmp.add(c);
            }
        }
        return true;
    }

}
