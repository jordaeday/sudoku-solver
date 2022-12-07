import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        //import things as space-separated
        Scanner file = new Scanner(new File("sudoku.txt")); //implement file support
        String[] sendToSolver = new String[9];
        String textLine = "";
        int gridNum = 0;

        //while loop loops over every puzzle
        while(file.hasNextLine()) {
            textLine = file.nextLine();
            //if title of grid (contains alpha char), skips to next puzzle line
            if(textLine.matches("[a-zA-Z]"))
                textLine = file.nextLine();
            //for each line in puzzle
            for(int i = 0; i < 9; i++) {
                sendToSolver[i] = file.nextLine();
            }
            gridNum++;
            Sudoku s1 = new Sudoku(sendToSolver, gridNum);
            //System.out.println(s1.toString());
            System.out.println(s1.formattedToString());
        }
    }
}
