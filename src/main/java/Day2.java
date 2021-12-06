public class Day2 {
    int position = 0;
    int depth = 0;
    int aim = 0;

    public void parseLine(String line) {
        String l[] = line.split(" ");
        int delta = Integer.parseInt(l[1]);
        int sign = 1;

        switch (l[0]) {
            case "up" : depth -= delta;
            break;
            case "down" : depth += delta;
            break;
            case "forward" : position += delta;
            break;
        }
    }

    public int getResult() {
        return depth * position;
    }

    public void parseLinePart2(String line) {
        String l[] = line.split(" ");
        int delta = Integer.parseInt(l[1]);
        switch (l[0]) {
            case "up" : aim -= delta;
                break;
            case "down" : aim += delta;
                break;
            case "forward" : position += delta;
                depth += (aim * delta);
                break;
        }


    }

}
