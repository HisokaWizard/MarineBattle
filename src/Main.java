import com.BattleField.BattleField;
import com.BattleField.Cell;
import test.module.BattleFieldTest;
import test.module.CellTest;

public class Main {
    public static void main(String [] args) {
        Main tests = new Main();
        tests.AllTests();
        BattleField field = new BattleField();
        field.generateBattleField();

        Cell [][] cells = field.getBattleField();
        Integer x = 0;
        Integer y = 0;
        for (x = 0; x < 10; x++) {
            for (y = 0; y < 10; y++) {
                if (cells[x][y].getShip() == true) {
                    System.out.print(" # ");
                } else if(cells[x][y].getBorder() == true) {
                    System.out.print(" * ");
                } else {
                    System.out.print(" O ");
                }
            }
            System.out.println("");
        }
    }

    public void AllTests() {
        CellTest cellTest = new CellTest();
        cellTest.TestMethod();
        BattleFieldTest fieldTest = new BattleFieldTest();
        fieldTest.TestMethod();
    }
}
