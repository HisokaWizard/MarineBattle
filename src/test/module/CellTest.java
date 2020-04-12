package test.module;

import com.BattleField.Cell;

public class CellTest {
    public void TestMethod() {
        Cell testCell = new Cell();
        String [] neighbors = {"45", "74", "67"};
        testCell.setBorder(true);
        testCell.setShip(true);
        testCell.setAddressId("55");
        testCell.setNeighbors(neighbors);

        if(testCell.getBorder() != true) {
            System.out.println("Mistake: setBorder / getBorder works incorrect");
        }

        if(testCell.getShip() != true) {
            System.out.println("Mistake: setShip / getShip works incorrect");
        }

        if(testCell.getAddressId() != "55") {
            System.out.println("Mistake: setAddressId / getAddressId works incorrect");
        }

        if(testCell.getNeighbors().length != 3) {
            System.out.println("Mistake: setNeighbors / getNeighbors works incorrect");
        }

        System.out.println("Cell: Success");
    }
}
