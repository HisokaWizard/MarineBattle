package test.module;

import com.BattleField.Cell;

import java.util.ArrayList;

public class CellTest {
    public void TestMethod() {
        Cell testCell = new Cell();
        ArrayList<String> neighbors = new ArrayList<String>();
        neighbors.add("74");
        neighbors.add("53");
        neighbors.add("67");
        testCell.setBorder(true);
        testCell.setShip(true);
        testCell.setAddressId("55");
        testCell.setNeighbors(neighbors);
        testCell.setShipId("Huge");
        testCell.setWasUsed(true);

        if(!testCell.getBorder()) {
            System.out.println("Mistake: setBorder / getBorder works incorrect");
            return;
        }

        if(!testCell.getShip()) {
            System.out.println("Mistake: setShip / getShip works incorrect");
            return;
        }

        if(testCell.getAddressId() != "55") {
            System.out.println("Mistake: setAddressId / getAddressId works incorrect");
            return;
        }

        if(testCell.getNeighbors().size() != 3) {
            System.out.println("Mistake: setNeighbors / getNeighbors works incorrect");
            return;
        }

        if(testCell.getShipId() != "Huge") {
            System.out.println("Mistake: setShipId / getShipId works incorrect");
            return;
        }

        if(!testCell.getWasUsed()) {
            System.out.println("Mistake: setWasUsed / getWasUsed works incorrect");
            return;
        }

        //System.out.println("Cell: Success");
    }
}
