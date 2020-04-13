package test.module;

import com.BattleField.Ship;

import java.util.ArrayList;

public class ShipTest {
    public void TestMethod() {
        Ship ship = new Ship();

        ship.setShipId("Huge");
        ArrayList<String> positions = new ArrayList<String>();
        for (Integer i = 0; i < 4; i++) {
            positions.add(i.toString() + 4);
        }
        ship.setShipPositions(positions);

        String [] inputPositions = {"58", "04", "14", "24", "34", "44"};

        for (Integer i = 0; i < 6; i++) {
            String result = ship.checkAttack(inputPositions[i]);
            if (i == 4 && !result.equals("Destroyed: " + ship.getShipId())) {
                System.out.println("Mistake: checkAttack() works incorrect.");
                return;
            }
        }

        //System.out.println("Ship: Success");
    }
}
