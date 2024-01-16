import java.util.Scanner;
public class HumanPlayer implements Player{
    HumanPlayer(){}
    @Override
    public void playTurn(Board board, Mark mark){
        Scanner s = new Scanner(System.in);
        System.out.println("Player " + mark+" type coordinates: ");
        String coords = s.nextLine();
        int rowIndex = Character.getNumericValue(coords.charAt(0));
        int colIndex= Character.getNumericValue(coords.charAt(1));

        while(!board.putMark(mark,rowIndex,colIndex)) {
            System.out.println("Invalid coordinates, type again: ");
            coords = s.nextLine();
            rowIndex = Character.getNumericValue(coords.charAt(0));
            colIndex = Character.getNumericValue(coords.charAt(1));
        }
    }
}
