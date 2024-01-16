import java.util.Random;

public class GeniusPlayer implements Player{
      GeniusPlayer(){
    }
    /** a method which enable to the genius one to "remember" what was his last move**/
    private int[] findMyLastCoord(Board board, Mark myMark){
        Mark[][] currBoard = board.getBoard();
        int[] res = {0,0};
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

    /** a method that resemble genius move, trying to put mark by previous move **/
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
