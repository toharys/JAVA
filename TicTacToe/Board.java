public class Board {
    private static final int DEFAULT_BOARD_SIZE = 4;
    private Mark[][] board;
    private int boardSize;


    /**
     * a c'tor that build a board in the reqursted size
     * @param size
     */
    Board(int size){
        this.boardSize = size;
        this.board = new Mark[size][size];
        // initialize an empty board
        for(Mark[] row : this.board){
            for (int i=0; i<size; i++) {
                row[i]=Mark.BLANK;
            }
        }
    }

    /**
     * a c'tor which build an default board in the default size
     */
    Board() {
        this(DEFAULT_BOARD_SIZE); //call to the Board(int size) with the default size as the argument
    }

    /**
     * a getter method to the board's size
     * @return the board's size
     */
    public int getSize(){
        return this.boardSize;
    }

    /**
     * a method that recieves coords and mark, and try to put the mark. if succeed return true, else
     * if the coords are not valid or that the cell is not empty returns false
     * @param mark the player's mark
     * @param row requested row num to put the mark
     * @param col requested col num to put the mark
     * @return true if succeed to put the mark, else false
     */
    public boolean putMark(Mark mark, int row, int col){
        // check if the coords arent valid (negative or bigger than the board size) and if the cell is empty
        if(row>=this.boardSize || col>=this.boardSize || row<0 || col<0 ||
                this.board[row][col] != Mark.BLANK){
            return false;
        }
        this.board[row][col]=mark;
        return true;
    }

    /**
     * a method that recieves cell's coords and returns the mark on it
     * @param row
     * @param col
     * @return the mark in the cell which (row,board) are his coords
     */
    public Mark getMark(int row, int col){
        // if the coords arn't valid returns blank
        if(row>=this.boardSize || col>=this.boardSize || row<0 || col<0){
            return Mark.BLANK;
        }
        return this.board[row][col];
    }
}
