public class PlayerFactory {

    public Player buildPlayer(String type){
        Player newPlayer;
        type = type.toLowerCase();
        switch (type){
            case "human":
                newPlayer = new HumanPlayer();
                break;
            case "clever":
                newPlayer = new CleverPlayer();
                break;
            case "genius":
                newPlayer = new GeniusPlayer();
                break;
            case "whatever":
                newPlayer = new WhateverPlayer();
                break;
            default:
                newPlayer = null;
        }
        return newPlayer;

    }
}
