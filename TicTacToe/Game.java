public class Game {
    // constant
    private static final int DEFAULT_WIN_STREAK = 3;
    // members
    private int winStreak;
    private Board gameBoard;
    private Player x;
    private Player o;
    private Renderer rend;


    // public methods

    Game(Player playerX,Player playerO, Renderer renderer){
        this.winStreak = DEFAULT_WIN_STREAK;
        this.gameBoard = new Board();
        this.x = playerX;
        this.o = playerO;
        this.rend = renderer;
    }

    Game(Player playerX,Player playerO,int size, int winStreak, Renderer renderer){
        if(winStreak<2 || winStreak>size){
            winStreak = size;
        }
        this.winStreak = winStreak;
        this.gameBoard = new Board(size);
        this.x = playerX;
        this.o = playerO;
        this.rend = renderer;
    }

    public int getWinStreak(){
        return this.winStreak;
    }

    public int getBoardSize(){
        return this.gameBoard.getSize();
    }

    public Mark run(){
        int emptyCells = gameBoard.getSize()*gameBoard.getSize();
        while(true){
            x.playTurn(gameBoard, Mark.X);
            this.rend.renderBoard(gameBoard);
            if(checkRows(Mark.X) || checkCols(Mark.X)|| checkDiagonal()==Mark.X){
                return Mark.X;
            }
            if(--emptyCells==0){
                return Mark.BLANK;
            }
            o.playTurn(gameBoard, Mark.O);
            this.rend.renderBoard(gameBoard);
        if(checkRows(Mark.O) || checkCols(Mark.O) || checkDiagonal()==Mark.O){
                return Mark.O;
            }
            if(--emptyCells==0){
                return Mark.BLANK;
            }
        }

    }

    // private methods

    private boolean checkRows(Mark currPlayer){
        int rowStreak=0;
        for (int i = 0; i < gameBoard.getSize(); i++) {
            rowStreak = 0;
            for (int j = 0; j < gameBoard.getSize(); j++) {
                if(gameBoard.getMark(i,j)==currPlayer){
                    rowStreak++;
                    if(rowStreak >= winStreak){
                        return true;
                    }
                }
                else{
                    rowStreak=0;
                }
            }

        }
        return false;
    }

    private boolean checkCols(Mark currPlayer){

        for (int i = 0; i < gameBoard.getSize(); i++) {
            int colStreak = 0;
            for (int j = 0; j < gameBoard.getSize(); j++) {
                if(gameBoard.getMark(j,i)==currPlayer){
                    colStreak++;
                    if(colStreak>=winStreak){
                        return true;
                    }
                }
                else{
                    colStreak=0;
                }
            }
        }
        return false;
    }

    private Mark checkDiagonal() {
        for (int i = 0; i < gameBoard.getSize(); i++) {
            for (int j = 0; j < gameBoard.getSize(); j++) {
                // left to right diagonal
                Mark res = leftTorightCheck(i,j);
                if(res==Mark.BLANK){
                    // right to left diagonal
                    res = rightToLeftCheck(i,j);
                }
                if(res!=Mark.BLANK){
                    return res;
                }
            }
        }
        return Mark.BLANK;
    }

    private Mark leftTorightCheck(int i, int j){
        if (gameBoard.getMark(i, j) != Mark.BLANK &&
                i < gameBoard.getSize() - winStreak + 1 && j < gameBoard.getSize() - winStreak + 1) {
            boolean flag = true;
            for (int k = 1; k < winStreak; k++) {
                if (gameBoard.getMark(i, j) != gameBoard.getMark(i + k, j + k)) {
                    flag = false;
                }
            }
            if (flag == true) {
                return gameBoard.getMark(i, j);
            }
        }
        return Mark.BLANK;
    }

    private Mark rightToLeftCheck(int i, int j) {
        if (gameBoard.getMark(i, j) != Mark.BLANK &&
                i > winStreak - 1 && j < gameBoard.getSize() - winStreak + 1) {
            boolean flag = true;
            for (int k = 1; k < winStreak; k++) {
                if (gameBoard.getMark(i, j) != gameBoard.getMark(i - k, j + k)) {
                    flag = false;
                }
            }
            if (flag == true) {
                return gameBoard.getMark(i, j);
            }
        }
        return Mark.BLANK;
    }

}
