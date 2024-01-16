/** a class that manage single game **/
public class Game {
    static final int default_win_streak = 3;
    // the class members
    private Player playerX; // the first player
    private Player player0; // the second player
    private Board board; // the game board
    private int winStreak; // the required streak for win
    private Renderer renderer; // the renderer to display in
    private int emptySlotsCounter; // counts the empty slots


    /** a constructor which get the requested values and build game instance **/
    public Game(Player playerX, Player player0, int size, int winStreak, Renderer renderer){
        this.playerX = playerX;
        this.player0 = player0;
        this.board = new Board(size);
        if(winStreak>size || winStreak<=0){
            this.winStreak = default_win_streak;
        }
        else {
            this.winStreak = winStreak;
        }
        this. renderer = renderer;
        this.emptySlotsCounter = this.board.getSize()*this.board.getSize();
    }
    /** default constructor **/
    public Game(Player playerX, Player player0, Renderer renderer){
        this.playerX = playerX;
        this.player0 = player0;
        this.board = new Board();
        this.winStreak = default_win_streak;
        this. renderer = renderer;
        this.emptySlotsCounter = this.board.getSize()*this.board.getSize();
    }
    /** a method which returns the win streak **/
    public int getWinStreak() {
        return winStreak;
    }


    /** a method which check if there is a streak in rows, returns true if there is, else false **/
    private boolean checkRows(Mark lastPlayer){
        Mark[][] currBoard = board.getBoard();
        int streakCounter = 0;
        // iterates all the row and in each row counts the streak of given player
        for(int i=0; i<board.getSize(); i++){
            for(int j=0; j<board.getSize(); j++){
                if(streakCounter>=this.winStreak){
                    return true; // if already found the appropriate streak
                }
                if(currBoard[i][j]==lastPlayer){
                    streakCounter++;
                }
                else{
                    streakCounter = 0; // if the streak was broken reinitialize it
                }
            }
            // if counts streak in the appropriate length returns true
            if(streakCounter>=this.winStreak){
                return true;
            }
        }
        return false;
    }
    /** a method which check if there is a streak in rows, returns true if there is, else false **/
    private boolean checkCols(Mark lestPlayer){
        Mark[][] currBoard = board.getBoard();
        int streakCounter = 0;
        // iterates all columns and in each column counts the streak of the given player
        for (int j=0; j<board.getSize();j++){
            for(int i=0; i<board.getSize();i++){
                if(streakCounter>=this.winStreak){
                    return true; // if already reached the appropriate streak length
                }
                if(currBoard[i][j]==lestPlayer){
                    streakCounter++;
                }
                else{
                    streakCounter = 0; // if the streak was broken reinitialize it
                }
            }
            // if the counts is in the appropriate length returns true
            if(streakCounter>=this.winStreak){
                return true;
            }
        }
        return false;
    }
    /** a method which receives start point and check if there is streak in this diagonal**/
    private boolean moveOnDiagonal(int startRow, int startCol, String state, Mark lastPlayer){
        Mark[][] currBoard = board.getBoard();
        int raiseFactor;
        // initialize the raise factor by the requested state (left/right diagonal)
        switch (state){
            case "right":
                raiseFactor = 1;
                break;
            case "left":
                raiseFactor = -1;
                break;
            default:
                raiseFactor = 0;
        }
        int streakCounter = 0;
        for(int i=startRow, j=startCol; i<board.getSize() && j< board.getSize() && j>0 ;i++, j+=raiseFactor){
            if(streakCounter>=this.winStreak){
                return true; // if already found the appropriate streak
            }
            if(currBoard[i][j]==lastPlayer){
                streakCounter++;
            }
            else{
                streakCounter = 0; // if the streak was broken reinitialize it
            }
        }
        return (streakCounter>=this.winStreak);
    }
    /** a method that check if there is streak in the diagonals, returns true if there is, else false **/
    private boolean checkDiagonals(Mark lastPlayer){
        Mark[][] currBoard = board.getBoard();
        int streakCounter = 0;
        for(int i=0;i<board.getSize();i++){
            // checks diagonals which start from the first column
            if (moveOnDiagonal(i,0,"right",lastPlayer)){
                return true;
            }
            // checks diagonals which start from the last column
            if (moveOnDiagonal(i,board.getSize()-1,"left",lastPlayer)){
                return true;
            }
            // check diagonals which start from the first row and going right
            if (moveOnDiagonal(0,i,"right",lastPlayer)){
                return true;
            }
            // check diagonals which start from the first row and going left
            if (moveOnDiagonal(0,i,"left",lastPlayer)){
                return true;
            }
        }
       return false;
    }
    /** a method that receives last player and check if he already won, returns the fit mark **/
    private boolean isWin(Mark lastPlayer){
        // executes checks on each direction, if one check found streak returns true, else false
        return (checkRows(lastPlayer) || checkCols(lastPlayer) || checkDiagonals(lastPlayer));
    }

    /** a method that run a single game **/
    public Mark run(){
        while(true){
            renderer.renderBoard(board);
            this.playerX.playTurn(this.board,Mark.X); // the first player make a turn with his mark=X
            // updated the empty slots num and check if the board is full, if it is return blank mark
            if(--emptySlotsCounter==0){
                return Mark.BLANK;
            }
            // check if playerX won
            if(this.isWin(Mark.X)){
                return Mark.X;
            }

            renderer.renderBoard(board);
            this.player0.playTurn(this.board,Mark.O); // the second player make a turn with his mark=O
            // updated the empty slots num and check if the board is full, if it is return blank mark
            if(--emptySlotsCounter==0){
                return Mark.BLANK;
            }
            // check if playerO won
            if(this.isWin(Mark.O)){
                return Mark.O;
            }

        }
    }
}
