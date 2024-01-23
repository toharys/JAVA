public class Tournament {
    private final static String START = "######### Results #########";
    private final static String P1_MSG = "Player 1, %s won: %d rounds" ;
    private final static String P2_MSG = "Player 2, %s won: %d rounds";
    private final static String TIES_MSG = "Ties: %d";

    private final int rounds;
    private final Renderer renderer;
    private final Player player1;
    private final Player player2;
    private int[] cntrs;

    /**
     * a c'tor which recieves the num of games in the tournament, renderer and two players
     * @param rounds
     * @param renderer
     * @param player1
     * @param player2
     */
    Tournament(int rounds, Renderer renderer, Player player1, Player player2){
        this.rounds = rounds;
        this.renderer = renderer;
        this.player1 = player1;
        this.player2 = player2;
        this.cntrs = new int[]{0,0,0};
    }

    /**
     * a method which runs full tournament and counts the results
     * @param size
     * @param winStreak
     * @param playerName1
     * @param playerName2
     */
    public void playTournament(int size, int winStreak, String playerName1, String playerName2){

        for (int i = 0; i < rounds; i++) {
            singleGame(size,winStreak,i);
        }
        displayTournamentResults(playerName1,playerName2);
    }

    /**
     * a method that run a single game an updates the results counters
     * @param size
     * @param winStreak
     * @param round
     */
    private void singleGame(int size, int winStreak, int round){
        // checks if the round's num is even or no and by this alternate between the players marks
        if(round%2==0) {
            Game gameRound = new Game(player1,player2,size, winStreak, renderer);
            int[] newCntrs = increaseCntrs(gameRound.run());
            this.cntrs[0] += newCntrs[0];
            this.cntrs[1] += newCntrs[1];
            this.cntrs[2] += newCntrs[2];
        }
        else {
            Game gameRound = new Game(player2, player1, size, winStreak, renderer);
            int[] newCntrs = increaseCntrs(gameRound.run());
            this.cntrs[0] += newCntrs[0];
            this.cntrs[1] += newCntrs[2];
            this.cntrs[2] += newCntrs[1];
        }
    }

    /**
     * a method which get mark and returns an array of integers by the form [ties, x, o]
     * where in the mark's place its update to 1 and in the rest 0
     * @param res
     * @return
     */
    private int[] increaseCntrs(Mark res){
        int winsX = 0;
        int winsO = 0;
        int ties = 0;
        if(res == Mark.X){
            winsX++;
        }
        else if (res ==Mark.O) {
            winsO++;
        }
        else{
            ties++;
        }
        return new int[]{ties,winsX, winsO};
    }

    /**
     * a method that displays the tournament results
     * @param p1
     * @param p2
     */
    private void displayTournamentResults(String p1, String p2){
        System.out.println(START);
        System.out.println(String.format(P1_MSG,p1,cntrs[1]));
        System.out.println(String.format(P2_MSG,p2,cntrs[2]));
        System.out.println(String.format(TIES_MSG, cntrs[0]));
    }


    public static void main(String[] args){
        // converts the input from the command line which resemble the rounds, size and winStreak to int's
        int rounds = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);
        int winStreak = Integer.parseInt(args[2]);
        // build a renderer and players
        RendererFactory rF = new RendererFactory();
        PlayerFactory pF = new PlayerFactory();
        Renderer rend = rF.buildRenderer(args[3],size);
        if(rend == null){
            System.out.println(Constants.UNKNOWN_RENDERER_NAME);
            return;
        }
        Player[] players = {pF.buildPlayer(args[4]),pF.buildPlayer(args[5])};
        if(players[0]==null || players[1]==null){
            System.out.println(Constants.UNKNOWN_PLAYER_NAME);
            return;
        }
        // build a tournament and runs a play
        Tournament t =  new Tournament(rounds,rend,players[0],players[1]);
        t.playTournament(size,winStreak,args[4],args[5]);
    }
}
