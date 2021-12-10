import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SyntaxChecker {
    List<String> input;

    public SyntaxChecker(List<String> input) {
        this.input = input;
    }

    private String removeCorrectChars(String s){
            String line = s;
            String tmp;
            boolean test = true;
            while (test) {
                tmp = line.replace("()", "");
                tmp = tmp.replace("{}", "");
                tmp = tmp.replace("<>", "");
                tmp = tmp.replace("[]", "");
                if (tmp.length() == line.length())
                    test = false;
                line = tmp;
            }
            return line;
        }


    private boolean lineHasOnlyOpenings(String s) {
        if (s.contains(")") || s.contains("]") || s.contains("}") || s.contains(">"))
            return false;
        else
            return true;
    }

    public int checkInput() {
        int sum =0;
        for (int i = 0; i<input.size(); i++) {
            String s = removeCorrectChars(input.get(i));
            boolean lineConsumed = lineHasOnlyOpenings(s);
                sum += calculateScore(s);
        }
        return sum;
    }

    private int calculateScore(String l) {
        char[] tmp = l.toCharArray();
        for (int i = 0; i< tmp.length; i++) {
            if (tmp[i] == ')')
                return 3;
            else if (tmp[i] == ']')
                return 57;
            else if (tmp[i] == '}')
                return 1197;
            else if (tmp[i] == '>')
                return 25137;
        }
        return 0;


    }

    public Long middleScoreCompletition() {
        List<Long> scores = new ArrayList<>();
        for (int i = 0; i<input.size(); i++) {
            String s = removeCorrectChars(input.get(i));
            if (lineHasOnlyOpenings(s)) {
                String tmp = reverseBrackets(s);
                Long score = getLineScore(tmp);
                scores.add(getLineScore(tmp));
                System.out.println(score + "  "+tmp);
            }
        }
        Collections.sort(scores);
        int pos = scores.size()/2;

        return scores.get(pos);
    }

    public static Long getLineScore(String tmp) {
        Long score = 0l;
        char[] t = tmp.toCharArray();
        for (int i = t.length-1; i >=0; i--) {
            char a = t[i];
            score = score * 5;
            switch (a) {
                case ')' : score+=1;
                break;
                case ']' :score +=2;
                break;
                case '}' : score+=3;
                break;
                case '>' : score+=4;
            }
        }

        return score;
    }

    private String reverseBrackets(String s) {
        List<String> t = Arrays.asList(s.split(""));
        String out = "";
        for (String c : t) {
            if (c.equals("("))
                out+=")";
            else if (c.equals("["))
                out+="]";
            else if (c.equals("{"))
                out+="}";
            else if (c.equals("<"))
                out+=">";
        }
        return out;
    }
}
