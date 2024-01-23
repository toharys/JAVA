import java.util.Random;
public class CleverPlayer implements Player{
    private Player[] players;

    /**
     * a c'tor that build a Clever player with 4 inner players. 3 genius and 1 whatever
     */
    CleverPlayer(){
        this.players = new Player[]{new GeniusPlayer(), new WhateverPlayer(), new GeniusPlayer(),
                      new GeniusPlayer()};

    }

    /**
     * a method that make a genius turn with probability of 3/4 and with 1/4 whatever turn
     * @param board
     * @param mark
     */
    @Override
    public void playTurn(Board board, Mark mark){
        Random rand = new Random();
        this.players[rand.nextInt(4)].playTurn(board,mark);
    }

}
