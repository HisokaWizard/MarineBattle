package test.module;

import com.BattleField.BattleField;
import com.BattleField.Cell;

public class BattleFieldTest {
    public void TestMethod() {
        BattleField field = new BattleField();

        try {
            for (Integer i = 0; i < 100; i++) {
                field.generateBattleField();
                Cell [][] cells = field.getBattleField();
            }
        } catch (Error error) {
            System.out.println(error);
            return;
        }

        //System.out.println("BattleField: Success");
    }
}
