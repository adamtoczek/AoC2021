import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Cave implements Comparator<Cave> {
    String id;
    List<Cave> connects = new ArrayList<>();
    int level;
    boolean isLowerCase;

    public String getId() {
        return id;
    }
    public Cave(String id) {
        this.id = id;
        char[] tmp = id.toCharArray();
        isLowerCase = (Character.isLowerCase(tmp[0])) ? true : false;
    }

    public void addConnection (Cave c) {
        connects.add(c);
    }

    @Override
    public int compare(Cave o1, Cave o2) {
        return o1.getId().compareTo(o2.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cave cave = (Cave) o;
        return id.equals(cave.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getId();
    }
}
