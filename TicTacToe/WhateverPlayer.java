import java.util.Random;
public class WhateverPlayer implements Player{
    private static final int MAX_TRIALS_NUM = 1000;// the maximum trials we can try to find coords
    // not in the main diagonal and not near cell with the same mark

    /**
     * a method which play a turn by choosing random cell in the game board
     * @param board
     * @param mark
     */
    @Override
    public void playTurn(Board board, Mark mark){
        int leftTrials = MAX_TRIALS_NUM;
        Random rand = new Random();
        // always choode cell randomly
        int row = rand.nextInt(board.getSize());
        int col = rand.nextInt(board.getSize());
        // tries to make move until he secceed, if he didnt reach the max num of trials he try to choose a
        // cell that its not in the main diagonal or right to cell with his mark
        // (in order to decrease his win chance)
        while(leftTrials>0){
            if(board.getMark(row,col-1)==mark){
                row = rand.nextInt(board.getSize());
                col = rand.nextInt(board.getSize());
            }
            leftTrials--;
        }
        for(;!board.putMark(mark,row,col);
            row = rand.nextInt(board.getSize()), col = rand.nextInt(board.getSize())){}
    }
}
