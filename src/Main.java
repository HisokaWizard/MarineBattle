import com.BattleField.BattleField;
import com.BattleField.Cell;

public class Main {
    public static void main(String [] args) {
        BattleField field = new BattleField();
        field.generateBattleField();

        Cell [][] cells = field.getBattleField();
        Integer x = 0;
        Integer y = 0;
        for (x = 0; x < 10; x++) {
            for (y = 0; y < 10; y++) {
                if (cells[x][y].getShip() == true) {
                    System.out.print("#");
                } else if(cells[x][y].getBorder() == true) {
                    System.out.print("*");
                } else {
                    System.out.print("~");
                }
            }
            System.out.println("");
        }
    }
}
