import java.util.Random;

public class CleverPlayer  implements  Player{
    CleverPlayer(){}
    /** a method which enable to the clever one to "remember" what was his last move but in order to decrease
     * his winning probability in compare to genius there is probability of 1/4 that he will do random move**/
    private int[] findMyLastCoord(Board board, Mark myMark){
        int[] res = {0,0};
        Random random = new Random();
        if(random.nextInt(5) == 1){
            res[0] = random.nextInt(board.getSize());
            res[1] = random.nextInt(board.getSize());
            return res;
        }
        Mark[][] currBoard = board.getBoard();
        for(int i=0;i< board.getSize();i++){
            for (int j=0; j< board.getSize()-1;j++){
                if (currBoard[i][j] == myMark && currBoard[i][j+1] == Mark.BLANK) {
                    res[0]=i;
                    res[1]=j+1;
                    return res;
                }
            }
        }
        return res;
    }

    /** a method that resemble clever move, trying to put mark by previous move **/
    @Override
    public void playTurn(Board board, Mark mark) {
        int[] coords = findMyLastCoord(board, mark); // trying to put mark by its last move
        // if the move he tried to place don't valid try randomly one else
        while (!board.putMark(mark, coords[0], coords[1])) {
            Random random = new Random();
            coords[0] = random.nextInt(board.getSize());
            coords[1]= random.nextInt(board.getSize());
        }
    }
}
