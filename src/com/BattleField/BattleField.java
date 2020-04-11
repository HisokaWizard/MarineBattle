package com.BattleField;

import java.util.ArrayList;
import java.util.List;

public class BattleField {
    private Cell [][] battleField;

    public void generateBattleField() {
        this.battleField = this.generateEmptyField();
        this.generateHugeShips(4);
        this.generateHugeShips(3);
        this.generateHugeShips(3);
        this.generateHugeShips(3);
    }

    public Cell[][] getBattleField() {
        return this.battleField;
    }

    private Cell[][] generateEmptyField() {
        final int fieldSize = 10;
        Cell [][] field = new Cell[fieldSize][fieldSize];
        for (Integer indexX = 0; indexX < fieldSize; indexX++) {
            for (Integer indexY = 0; indexY < fieldSize; indexY++) {
                field[indexX][indexY] = new Cell();
                field[indexX][indexY].setAddressId(indexX.toString() + indexY.toString());
                field[indexX][indexY].setNeighbors(this.selectNeighbors(indexX, indexY));
                field[indexX][indexY].setBorder(false);
                field[indexX][indexY].setShip(false);
            }
        }
        return field;
    }

    private String[] selectNeighbors(Integer indexX, Integer indexY) {
        List<String> neighbors = new ArrayList<String>();
        if (indexX - 1 >= 0) {
            Integer newIndexX = indexX - 1;
            neighbors.add(newIndexX.toString() + indexY.toString());
        }
        if (indexX + 1 <= 9) {
            Integer newIndexX = indexX + 1;
            neighbors.add(newIndexX.toString() + indexY.toString());
        }
        if (indexY - 1 >= 0) {
            Integer newIndexY = indexY - 1;
            neighbors.add(indexX.toString() + newIndexY.toString());
        }
        if (indexY + 1 <= 9) {
            Integer newIndexY = indexY + 1;
            neighbors.add(indexX.toString() + newIndexY.toString());
        }
        return neighbors.toArray(new String[neighbors.size()]);
    }

    private void generateHugeShips(Integer size) {
        Integer [] coordinate = this.genarateShipBaseCoordinate();
        for (Integer x = 0; x < 10; x++) {
            for (Integer y = 0; y < 10; y++) {
                if (coordinate[0] == x && coordinate[1] == y &&
                    this.battleField[x][y].getShip() != true && this.battleField[x][y].getBorder() != true) {
                    this.battleField[x][y].setShip(true);
                    if (size == 4) {
                        if (x + 3 <= 9) {
                            this.battleField[x + 1][y].setShip(true);
                            this.battleField[x + 2][y].setShip(true);
                            this.battleField[x + 3][y].setShip(true);
                        } else if (x - 3 >= 0) {
                            this.battleField[x - 1][y].setShip(true);
                            this.battleField[x - 2][y].setShip(true);
                            this.battleField[x - 3][y].setShip(true);
                        } else if (y + 3 <= 9) {
                            this.battleField[x][y + 1].setShip(true);
                            this.battleField[x][y + 2].setShip(true);
                            this.battleField[x][y + 3].setShip(true);
                        } else if (y - 3 >= 0) {
                            this.battleField[x][y - 1].setShip(true);
                            this.battleField[x][y - 2].setShip(true);
                            this.battleField[x][y - 3].setShip(true);
                        }
                    }
                    if (size == 3) {
                        if (x + 2 <= 9) {
                            this.battleField[x + 1][y].setShip(true);
                            this.battleField[x + 2][y].setShip(true);
                        } else if (x - 2 >= 0) {
                            this.battleField[x - 1][y].setShip(true);
                            this.battleField[x - 2][y].setShip(true);
                        } else if (y + 2 <= 9) {
                            this.battleField[x][y + 1].setShip(true);
                            this.battleField[x][y + 2].setShip(true);
                        } else if (y - 2 >= 0) {
                            this.battleField[x][y - 1].setShip(true);
                            this.battleField[x][y - 2].setShip(true);
                        }
                    }
                }
            }
        }
        for (Integer x = 0; x < 10; x++) {
            for (Integer y = 0; y < 10; y++) {
                if (this.battleField[x][y].getShip() == true) {
                    Integer amountOfNeighbor = this.battleField[x][y].getNeighbors().length;
                    String [] neighbors = this.battleField[x][y].getNeighbors();
                    for (int n = 0; n < amountOfNeighbor; n++) {
                        Integer indexX = Integer.decode(Character.toString(neighbors[n].charAt(0)));
                        Integer indexY = Integer.decode(Character.toString(neighbors[n].charAt(1)));
                        if (this.battleField[indexX][indexY].getShip() != true) {
                            this.battleField[indexX][indexY].setBorder(true);
                        }
                    }
                }
            }
        }
    }

    private Integer[] genarateShipBaseCoordinate() {
        Integer coordX = (int)Math.round(Math.random() * 10);
        Integer coordY = (int)Math.round(Math.random() * 10);
        Integer [] coordinate = {coordX, coordY};
        return coordinate;
    }
}
