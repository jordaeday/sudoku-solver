public class Sudoku {
    private int[][] puzzle;
    private String[] puzzleStringArr;
    private int puzzleNum = 0;

    Sudoku(String[] lines, int gridNum) {
        puzzle = new int[9][9];
        for(int o = 0; o < 9; o++) {
            puzzleStringArr = lines[o].split("");
            for(int i = 0; i < 9; i++) {
                puzzle[o][i] = Integer.parseInt(puzzleStringArr[i]);
            }
        }
        puzzleNum = gridNum;
        solve();
    }

    public void solve() {
        recurseSolve(0);
    }

    private boolean recurseSolve(int pos) {
        //if at last position/board is full
        if(pos == 81)
            return true;
        //finds and sets row and col based on position
        int row = pos / 9;
        int col = pos % 9;
        if(puzzle[row][col] == 0) { //if value not given
            //loops through numbers 1-9 to test if they are valid
            for(int i = 1; i <= 9; i++) {
                puzzle[row][col] = i;
                //checks if inputting number is valid and future numbers are valid
                if(isValidSudoku(row, col) && recurseSolve(pos+1))
                    return true;
                puzzle[row][col] = 0; //resets square value for next for iteration
            }
        } else { //value given
            return recurseSolve(pos+1);
        }
        return false;
    }

    private boolean isValidSudoku(int r, int c) {
        int val = puzzle[r][c];
        //block values
        int rBlock = r/3 * 3;
        int cBlock = c/3 * 3;
        for(int i = 0; i < 9; i++) {
            //tests if column is valid (no repeats)
            if(i != r && puzzle[i][c] == val)
                return false;
            //tests if row is valid (no repeats)
            if(i != c && puzzle[r][i] == val)
                return false;
            //tests if block (bigger square) is valid (no repeats)
            int addR = i/3;
            int addC = i%3;
            if((rBlock + addR) != r && (cBlock + addC) != c && puzzle[rBlock+addR][cBlock+addC] == val)
                return false;
        }
        return true;
    }

    public String formattedToString() {
        String ret = "Grid " + puzzleNum + ":\n";
        for(int o = 0; o < 9; o++) {
            if(o%3 == 0)
                ret += "-------------------\n";
            for(int i = 0; i < 9; i++) {
                if(i%3 == 0)
                    ret += "|";
                else
                    ret += " ";
                ret += puzzle[o][i];
                if(i == 8)
                    ret += "|";
            }
            ret += "\n";
        }
        ret += "-------------------\n";
        return ret;
    }

    public String toString() {
        String ret = "Grid " + puzzleNum + ":\n";
        for(int o = 0; o < 9; o++) {
            for(int i = 0; i < 9; i++) {
                ret += puzzle[o][i];
            }
            ret += "\n";
        }
        return ret;
    }
}
