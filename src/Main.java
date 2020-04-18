import com.BattleField.BattleField;
import com.BattleField.Cell;
import test.module.BattleFieldTest;
import test.module.CellTest;
import test.module.ShipTest;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String [] args) {
        Main tests = new Main();
        tests.AllTests();
        BattleField field = new BattleField();
        field.generateBattleField();
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
