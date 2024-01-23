import java.util.Random;
public class GeniusPlayer implements Player{
    private static final int MAX_TRIALS_NUM = 1000; // the maximum tries to search coords geniusly
    private static final int FAILED_TO_FIND = -1;

    /**
     * a nethod which play turns in way that ensure winning of more than 50% on clever and whatever players
     * @param board
     * @param mark
     */
    @Override
    public void playTurn(Board board, Mark mark){
        int[] coords = chooseTurnCoordsGenius(board,mark,board.getSize()/2,0);
        int leftTrials = MAX_TRIALS_NUM; // the maximum trials we can try to find coords by chooseTurnCoords
        Random rand = new Random();
        // tries to put mark until it's returns true, if we didnt acvhieved the max trials its try to find
        // cell geniusly, if we already try the max trials we can find random coords
        while(!board.putMark(mark,coords[0],coords[1])){
            if(leftTrials!=0){
                coords = chooseTurnCoordsGenius(board, mark,board.getSize()/2,0);
                leftTrials--;
                continue;
            }
            coords[0] = rand.nextInt(board.getSize());
            coords[1]= rand.nextInt(board.getSize());
        }
    }

    /**
     * a method that finds an empty cell by cell with the player's mark
     * @param board
     * @param mark
     * @return
     */
    private int[] chooseTurnCoordsGenius(Board board, Mark mark,int row,int col){

        // search an empty cell near cell with the player's mark
            for (; row < board.getSize(); row++) {
                for (; col < board.getSize(); col++) {
                    if (validCoord(board,row,col+1) && board.getMark(row, col) == mark &&
                            board.getMark(row, col+1) == Mark.BLANK) {
                            return new int[]{row, col+1};
                    }
                    if (validCoord(board,row,col-1) && board.getMark(row, col) == mark &&
                            board.getMark(row, col-1) == Mark.BLANK) {
                        return new int[]{row, col-1};
                    }
                    if (validCoord(board,row+1,col) && board.getMark(row, col) == mark &&
                            board.getMark(row+1, col) == Mark.BLANK) {
                        return new int[]{row+1, col};
                    }
                    if (validCoord(board,row-1,col) && board.getMark(row, col) == mark &&
                            board.getMark(row-1, col) == Mark.BLANK) {
                        return new int[]{row-1, col};
                    }
                    if (validCoord(board,row+1,col-1) && board.getMark(row, col) == mark &&
                            board.getMark(row+1, col-1) == Mark.BLANK) {
                        return new int[]{row+1, col-1};
                    }
                    if (validCoord(board,row-1,col+1) && board.getMark(row, col) == mark &&
                            board.getMark(row-1, col+1) == Mark.BLANK) {
                        return new int[]{row-1, col+1};
                    }
                    if (validCoord(board,row+1,col+1) && board.getMark(row, col) == mark &&
                            board.getMark(row+1, col+1) == Mark.BLANK) {
                        return new int[]{row+1, col+1};
                    }
                    if (validCoord(board,row-1,col-1) && board.getMark(row, col) == mark &&
                            board.getMark(row-1, col-1) == Mark.BLANK) {
                        return new int[]{row-1, col-1};
                    }
                }
            }
        return new int[]{FAILED_TO_FIND,col};
    }

    /**
     * a method that recieves a coords of cell and check if they are valid
     * @param board
     * @param row
     * @param col
     * @return true if valif, else false
     */
    private boolean validCoord(Board board,int row, int col){
        return row>=0&&col>=0&&row<board.getSize()&&col<board.getSize();
    }
}
