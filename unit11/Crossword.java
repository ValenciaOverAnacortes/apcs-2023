package unit11;

// 2016 FRQ #3
// https://secure-media.collegeboard.org/digitalServices/pdf/ap/ap16_frq_computer_science_a.pdf#page=12

class Square {
    /**
     * Constructs one square of a crossword puzzle grid.
     * Postcondition:
     * - The square is black if and only if isBlack is true.
     * - The square has number num.
     */
    public Square(boolean isBlack, int num) { /* implementation not shown */
        this.isBlack = isBlack;
        this.num = num;
    }

    // There may be instance variables, constructors, and methods that are not
    // shown.
    private boolean isBlack;
    private int num;

    public int getNum() {
        return num;
    }

    public boolean isBlack() {
        return isBlack;
    }
}

public class Crossword {
    /**
     * Each element is a Square object with a color (black or white) and a number.
     * puzzle[r][c] represents the square in row r, column c.
     * There is at least one row in the puzzle.
     */
    private Square[][] puzzle;

    /**
     * Constructs a crossword puzzle grid.
     * Precondition: There is at least one row in blackSquares.
     * Postcondition:
     * - The crossword puzzle grid has the same dimensions as blackSquares.
     * - The Square object at row r, column c in the crossword puzzle grid is black
     * if and only if blackSquares[r][c] is true.
     * - The squares in the puzzle are labeled according to the crossword labeling
     * rule.
     */
    public Crossword(boolean[][] blackSquares) {
        puzzle = new Square[blackSquares.length][blackSquares[0].length];
        int count = 1;
        for(int r = 0; r < puzzle.length; r++){
            for(int c = 0; c < puzzle[r].length; c++){
                if(blackSquares[r][c]){
                    puzzle[r][c] = new Square(true, 0);
                }
                else if(toBeLabeled(r, c, blackSquares)){
                    puzzle[r][c] = new Square(false, count);
                    count++;
                }
                else{
                    puzzle[r][c] = new Square(false, 0);
                }
            }
        }
    }

    /**
     * Returns true if the square at row r, column c should be labeled with a
     * positive number;
     * false otherwise.
     * The square at row r, column c is black if and only if blackSquares[r][c] is
     * true.
     * Precondition: r and c are valid indexes in blackSquares.
     */
    private boolean toBeLabeled(int r, int c, boolean[][] blackSquares) {
        boolean myBool;
        if(blackSquares[r][c] == false){
            myBool = true;
        }
        else{
            return false;
        }

        if(c == 0 || blackSquares[r][c - 1] ){
            myBool = true;
        }
        else{
            return false;
        }

        if(r == 0 || blackSquares[r-1][c] ){
            myBool = true;
        }
        else{
            return false;
        }

        return myBool;
    }

    // There may be instance variables, constructors, and methods that are not
    // shown.

    public static void check(boolean test) throws AssertionError {
        if (!test)
            throw new AssertionError("sad panda");
    }

    public static boolean[][] factory() {
        boolean[][] board = {
                { true, false, false, true, true, true, false, false, false },
                { false, false, false, false, true, false, false, false, false },
                { false, false, false, false, false, false, true, true, true },
                { false, false, true, false, false, false, true, false, false },
                { true, true, true, false, false, false, false, false, false },
                { false, false, false, false, true, false, false, false, false },
                { false, false, false, true, true, true, false, false, true },
        };
        return board;
    }

    public static void main(String[] args) {

        boolean[][] board = Crossword.factory();
        Crossword c = new Crossword(board);
        check(c.toBeLabeled(2, 0, board));
        check(!c.toBeLabeled(2, 1, board));
        check(!c.toBeLabeled(0, 5, board));
        check(c.toBeLabeled(1, 5, board));
        check(c.toBeLabeled(3, 8, board));
        check(!c.toBeLabeled(6, 8, board));
        check(!c.toBeLabeled(6, 7, board));

        check(c.puzzle[0][2].getNum() == 2);
        check(c.puzzle[3][7].getNum() == 13);
        check(c.puzzle[6][6].getNum() == 22);

        System.out.println("Happy Panda! \uD83D\uDC3C");

    }

}