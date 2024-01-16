import java.util.Random;

public class WhateverPlayer implements Player{
    WhateverPlayer(){}
    @Override
    public void playTurn(Board board, Mark mark){
        Random random = new Random();
        // we allow whatever to choose any random coordinates in the board
        int rowIndex = random.nextInt(board.getSize());
        int colIndex = random.nextInt(board.getSize());
        while(!board.putMark(mark,rowIndex, colIndex)){
            rowIndex = random.nextInt(board.getSize());;
            colIndex = random.nextInt(board.getSize());
        }
    }
}
