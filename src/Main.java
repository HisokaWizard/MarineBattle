import com.BattleField.BattleField;
import com.BattleField.Cell;
import test.module.BattleFieldTest;
import test.module.CellTest;
import test.module.ShipTest;

import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        Main tests = new Main();
        tests.AllTests();

        BattleField field = new BattleField();
        field.generateBattleField();

        Cell [][] cells = field.getBattleField();
        for (Integer y = 0; y < 10; y++) {
            for (Integer x = 0; x < 10; x++) {
                System.out.print(cells[x][y].getAddressId() + " ");
            }
            System.out.print("\t");
            for (Integer x = 0; x < 10; x++) {
                if (cells[x][y].getShip()) {
                    System.out.print(" # ");
                } else if(cells[x][y].getBorder()) {
                    System.out.print(" * ");
                } else {
                    System.out.print(" O ");
                }
            }
            System.out.println("");
        }
        field.fightProcess();
    }

    public void AllTests() {
        CellTest cellTest = new CellTest();
        cellTest.TestMethod();
        BattleFieldTest fieldTest = new BattleFieldTest();
        fieldTest.TestMethod();
        ShipTest shipTest = new ShipTest();
        shipTest.TestMethod();
    }
}
