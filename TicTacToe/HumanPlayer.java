public class HumanPlayer implements Player {
    private final static String X = "X";
    private final static String O = "O";

    /**
     * a method that make a turn by human player chose
     * @param board
     * @param mark
     */
    @Override
    public void playTurn(Board board, Mark mark){
        // prints request to player's move by his mark: x\o
        if(mark == Mark.X) {
            System.out.println(Constants.playerRequestInputString(X));
        }
        if(mark == Mark.O) {
            System.out.println(Constants.playerRequestInputString(O));
        }
        // reads the user's input
        int coord = KeyboardInput.readInt();
        // while he choose unvalid coords ask the player to choose again
        while(!validCoord(board,coord)){
            coord = KeyboardInput.readInt();
        }
        board.putMark(mark,coord/10,coord%10);

    }

    /**
     * a method which recieves a coords and returns true if they valid and false else
     * @param board
     * @param coord
     * @return
     */
    private boolean validCoord(Board board,int coord){
        // the coords are always in the form of xy where x,y are digits between 0 and 9 and represents the
        // row and the col
        int row = coord/10;
        int col = coord%10;
        // checks if the coord are not in the board
        if(row>=board.getSize() || col>=board.getSize() || row<0 || col<0 ){
            System.out.println(Constants.INVALID_COORDINATE);
            return false;
        }
        // checks if the requested cell is already occupied
        if(board.getMark(row, col)!= Mark.BLANK){
            System.out.println(Constants.OCCUPIED_COORDINATE);
            return false;
        }
        return true;
    }
}
