public class Tournament {
    final private int rounds;
    final private Renderer renderer;
    final private Player[] players;
    private int firstWinsCounter;
    private int secondWinsCounter;
    private int tiesCounter;


    Tournament(int rounds, Renderer renderer,Player[] players){
        this.rounds = rounds;
        this.renderer = renderer;
        this.players = players;
        this.firstWinsCounter =0;
        this.secondWinsCounter =0;
        this.tiesCounter=0;
    }
    /** a method that prints in the right format the tournament's summary **/
    private void displayEndMsg(String firstPlayerType, String secondPlayerType){
        System.out.println("######### Results #########");
        System.out.printf("Player 1, %s won: %d rounds\n" ,firstPlayerType, firstWinsCounter);
        System.out.printf("Player 2, %s won: %d rounds\n" ,secondPlayerType, secondWinsCounter);
        System.out.println("Ties: " + tiesCounter);
    }
    /** a method that runs single game and updates the wins counter by this round**/
    private int[] runSingleGame(Player playerX, Player player0, int size, int winStreak){
        int[] counters = {0,0,0};
        Game singleGame = new Game(playerX,player0,size,winStreak,renderer);
        // take a single run and updates the counters by the winner / ties
        Mark winner = singleGame.run();
        if(winner == Mark.X){
            counters[0]++;
        }
        if(winner == Mark.O){
            counters[1]++;
        }
        if(winner==Mark.BLANK){
            counters[2]++;
        }
        return counters;
    }
    /** a method that runs full tournament**/
    public void playTournament(int size, int winStreak, String[] playerNames) {
       // loop by the requested tournament size
       for(int i=0;i<rounds;i++){
           // if it is even round the first player is X and updates the counters
           int[] counters;
           if(i%2==0){
               counters = runSingleGame(players[0],players[1],size,winStreak);
               firstWinsCounter+=counters[0];
               secondWinsCounter+=counters[1];
           }
           // if it is odd round the first player is 0 and updates the counters
           else {
               counters = runSingleGame(players[1],players[0],size,winStreak);
               firstWinsCounter+=counters[1];
               secondWinsCounter+=counters[0];
           }
           tiesCounter+=counters[2];
        }
       displayEndMsg(playerNames[0], playerNames[1]);
    }

    public static void main(String[] args){
        int roundsCount = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);
        int winStreak = Integer.parseInt(args[2]);
        RendererFactory renderFactory = new RendererFactory();
        Renderer render = renderFactory.buildRenderer(args[3],size);
        PlayerFactory playerFactory = new PlayerFactory();
        Player[] players = { playerFactory.buildPlayer(args[4]),playerFactory.buildPlayer(args[5]) };
        if(players[0]==null || players[1]==null || render==null){
            System.out.println("Choose a player, and start again\n" +
                    "The players: [human, clever, whatever, genius]\n");
            return;
        }
        Tournament tournament = new Tournament(roundsCount, render,players);
        String[] playersName = {args[4], args[5]};
        tournament.playTournament(size,winStreak,playersName);











    }
}
