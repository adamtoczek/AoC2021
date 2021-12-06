import java.util.List;

public class BingoBoard {
    private int[][] table = new int[5][5];
    private boolean[][] hits = new boolean[5][5];
    private int rowToFill = 0;
    public boolean win = false;
    public int winCalc;

    public BingoBoard() {
        for (int i = 0; i < 5; i++)
            for (int k = 0; k < 5; k++)
                hits[i][k] = false;
    }

    public void fillRow(String inputRow) {
//        System.out.println("Fill row "+rowToFill+" with "+inputRow);
        inputRow = inputRow.replace("  ", " ");
        inputRow = inputRow.replace("  ", " ");
        inputRow = inputRow.replace("  ", " ");
        inputRow = inputRow.replace("  ", " ");
        inputRow = inputRow.trim();
        if (rowToFill > 4)
            return;
        String[] parts = inputRow.split(" ");
        for (int i = 0; i < 5; i++) {
            table[rowToFill][i] = Integer.parseInt(parts[i]);
        }
        rowToFill++;
    }

    public boolean isWinning(int row, int col) {
        if (hits[row][0] && hits[row][1] && hits[row][2] && hits[row][3] && hits[row][4])
            return true;
        if (hits[0][col] && hits[1][col] && hits[2][col] && hits[3][col] && hits[4][col])
            return true;
        return false;
    }

    public boolean markHit(int num) {
        for (int row = 0; row < 5; row++)
            for (int col = 0; col < 5; col++) {
                if (table[row][col] == num) {
                    hits[row][col] = true;
                    if (isWinning(row, col)) {
                        win = true;
                        calcWin(num);
                        return win;
                    }
                }
            }
        return false;
    }

    private void calcWin(int num) {
        int res = 0;
        for (int row = 0; row < 5; row++)
            for (int col = 0; col < 5; col++)
                if (!hits[col][row])
                    res += table[col][row];
        res *= num;
        winCalc = res;
    }

    public boolean getWin () {
        if (win)
            System.out.print(1+" ");
        else
            System.out.print(0+" ");
        return win;
    }


}
