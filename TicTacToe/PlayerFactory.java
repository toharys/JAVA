/** a class that builds players objects **/
public class PlayerFactory {
    /** a method that receives the requested player class, built it and returns it **/
    public Player buildPlayer(String type){
        Player newPlayer;
        type = type.toLowerCase(); // upper and lower case both are valid, so change the type to lower case
                                    // in order to do one check
        switch (type){  // call's to constructor by type
            case "human":
                newPlayer = new HumanPlayer();
                break;
            case "clever":
                newPlayer = new CleverPlayer();
                break;
            case "whatever":
                newPlayer = new WhateverPlayer();
                break;
            case "genius":
                newPlayer = new GeniusPlayer();
                break;
            default:
                newPlayer = null; // if the type argument doesn't in the list
        }
        return newPlayer;
    }
}
