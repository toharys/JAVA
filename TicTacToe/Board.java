/** a class that response on the current board state**/

public class Board {
    static final int default_size = 4;
    private int size;
    private Mark[][] board;

    /** a constructor which get size and build a board in this size **/
    public Board(int size){
        this.size = size;
        this.board = new Mark[this.size][this.size];
        // set all the slots as blanks
        for(Mark[] row : this.board){
            for(int j=0;j<this.size;j++){
                row[j] = Mark.BLANK;
            }
        }
    }
    /** default constructor **/
    public Board(){
        this(default_size); // calling to the other constructor with the default size as the size parameter
    }
    /** a method which returns the board size **/
    public int getSize() {
        return size;
    }
    /**  a method which returns the board **/
    public Mark[][] getBoard() {
        return board;
    }
    /** trying to set a slot, returns true in success else false **/
    public boolean putMark(Mark mark, int row, int col){
        // if the requested slot invalid
        if(row>=this.size || col>=this.size || row<0 || col<0 || board[row][col]!=Mark.BLANK){
            return false;
        }
        this.board[row][col] = mark; // else set the slot
        return true;
    }
    /** return the mark of the slot in the given indexes **/
    public Mark getMark(int row, int col){
        if(row>=this.size || col>=this.size){ // if invalid indexes
            return Mark.BLANK;
        }
        return this.board[row][col];
    }
}
