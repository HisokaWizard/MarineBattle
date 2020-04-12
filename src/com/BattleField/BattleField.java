package com.BattleField;

import java.util.ArrayList;
import java.util.List;

public class BattleField {
    private Cell [][] battleField;

    public void generateBattleField() {
        this.battleField = this.generateEmptyField();
        while (true) {
            if(this.generateHugeShips(4)) {
                break;
            }
        }
        for (Integer i = 0; i < 2; i++) {
            while (true) {
                if(this.generateHugeShips(3)) {
                    break;
                }
            }
        }
        for (Integer i = 0; i < 3; i++) {
            while (true) {
                if(this.generateMiddleShip()) {
                    break;
                }
            }
        }
        for (Integer i = 0; i < 4; i++) {
            while (true) {
                if(this.generateLittleShip()) {
                    break;
                }
            }
        }
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
        if (indexX - 1 >= 0 && indexY - 1 >= 0) {
            Integer newIndexX = indexX - 1;
            Integer newIndexY = indexY - 1;
            neighbors.add(newIndexX.toString() + newIndexY.toString());
        }
        if (indexX - 1 >= 0 && indexY + 1 <= 9) {
            Integer newIndexX = indexX - 1;
            Integer newIndexY = indexY + 1;
            neighbors.add(newIndexX.toString() + newIndexY.toString());
        }
        if (indexX + 1 <= 9 && indexY - 1 >= 0) {
            Integer newIndexX = indexX + 1;
            Integer newIndexY = indexY - 1;
            neighbors.add(newIndexX.toString() + newIndexY.toString());
        }
        if (indexX + 1 <= 9 && indexY + 1 <= 9) {
            Integer newIndexX = indexX + 1;
            Integer newIndexY = indexY + 1;
            neighbors.add(newIndexX.toString() + newIndexY.toString());
        }
        return neighbors.toArray(new String[neighbors.size()]);
    }

    private Boolean generateHugeShips(Integer size) {
        Integer [] coordinate = this.generateShipBaseCoordinate();
        Boolean isShipCreated = false;
        Boolean shipNotClimb = false;
        for (Integer x = 0; x < 10; x++) {
            if (shipNotClimb || isShipCreated) {
                break;
            }
            for (Integer y = 0; y < 10; y++) {
                Boolean freeCell = !this.battleField[x][y].getShip() && !this.battleField[x][y].getBorder();
                if (coordinate[0] == x && coordinate[1] == y && freeCell) {
                    this.battleField[x][y].setShip(true);
                    if (size == 4) {
                        if (x + 3 <= 9) {
                            if (this.checkAllShipLengthFree(x, y, size, true, true)) {
                                this.battleField[x + 1][y].setShip(true);
                                this.battleField[x + 2][y].setShip(true);
                                this.battleField[x + 3][y].setShip(true);
                            } else {
                                this.battleField[x][y].setShip(false);
                                shipNotClimb = true;
                                break;
                            }
                        } else if (x - 3 >= 0) {
                            if (this.checkAllShipLengthFree(x, y, size, false, true)) {
                                this.battleField[x - 1][y].setShip(true);
                                this.battleField[x - 2][y].setShip(true);
                                this.battleField[x - 3][y].setShip(true);
                            } else {
                                this.battleField[x][y].setShip(false);
                                shipNotClimb = true;
                                break;
                            }
                        } else if (y + 3 <= 9) {
                            if (this.checkAllShipLengthFree(x, y, size, true, false)) {
                                this.battleField[x][y + 1].setShip(true);
                                this.battleField[x][y + 2].setShip(true);
                                this.battleField[x][y + 3].setShip(true);
                            } else {
                                this.battleField[x][y].setShip(false);
                                shipNotClimb = true;
                                break;
                            }
                        } else if (y - 3 >= 0) {
                            if (this.checkAllShipLengthFree(x, y, size, false, false)) {
                                this.battleField[x][y - 1].setShip(true);
                                this.battleField[x][y - 2].setShip(true);
                                this.battleField[x][y - 3].setShip(true);
                            }  else {
                                this.battleField[x][y].setShip(false);
                                shipNotClimb = true;
                                break;
                            }
                        }
                        isShipCreated = true;
                        break;
                    }
                    if (size == 3) {
                        if (x + 2 <= 9) {
                            if (this.checkAllShipLengthFree(x, y, size, true, true)) {
                                this.battleField[x + 1][y].setShip(true);
                                this.battleField[x + 2][y].setShip(true);
                            } else {
                                this.battleField[x][y].setShip(false);
                                shipNotClimb = true;
                                break;
                            }
                        } else if (x - 2 >= 0) {
                            if (this.checkAllShipLengthFree(x, y, size, false, true)) {
                                this.battleField[x - 1][y].setShip(true);
                                this.battleField[x - 2][y].setShip(true);
                            } else {
                                this.battleField[x][y].setShip(false);
                                shipNotClimb = true;
                                break;
                            }
                        } else if (y + 2 <= 9) {
                            if (this.checkAllShipLengthFree(x, y, size, true, false)) {
                                this.battleField[x][y + 1].setShip(true);
                                this.battleField[x][y + 2].setShip(true);
                            } else {
                                this.battleField[x][y].setShip(false);
                                shipNotClimb = true;
                                break;
                            }
                        } else if (y - 2 >= 0) {
                            if (this.checkAllShipLengthFree(x, y, size, false, false)) {
                                this.battleField[x][y - 1].setShip(true);
                                this.battleField[x][y - 2].setShip(true);
                            } else {
                                this.battleField[x][y].setShip(false);
                                shipNotClimb = true;
                                break;
                            }
                        }
                        isShipCreated = true;
                        break;
                    }
                }
            }
        }
        this.setNeighbors();
        return isShipCreated;
    }

    private Boolean checkAllShipLengthFree(Integer x, Integer y, Integer size, Boolean plus, Boolean horizontal) {
        if (size == 4) {
            if (plus && horizontal) {
                for (int i = 1; i < size; i++) {
                    if (this.battleField[x + i][y].getShip() || this.battleField[x + i][y].getBorder()) {
                        return false;
                    }
                }
            } else if (plus && !horizontal) {
                for (int i = 1; i < size; i++) {
                    if (this.battleField[x][y + i].getShip() || this.battleField[x][y + i].getBorder()) {
                        return false;
                    }
                }
            } else if (!plus && horizontal) {
                for (int i = 1; i < size; i++) {
                    if (this.battleField[x - i][y].getShip() || this.battleField[x - i][y].getBorder()) {
                        return false;
                    }
                }
            } else if (!plus && !horizontal) {
                for (int i = 1; i < size; i++) {
                    if (this.battleField[x][y - i].getShip() || this.battleField[x][y - i].getBorder()) {
                        return false;
                    }
                }
            }
        }
        if (size == 3) {
            if (plus && horizontal) {
                for (int i = 1; i < size; i++) {
                    if (this.battleField[x + i][y].getShip() || this.battleField[x + i][y].getBorder()) {
                        return false;
                    }
                }
            } else if (plus && !horizontal) {
                for (int i = 1; i < size; i++) {
                    if (this.battleField[x][y + i].getShip() || this.battleField[x][y + i].getBorder()) {
                        return false;
                    }
                }
            } else if (!plus && horizontal) {
                for (int i = 1; i < size; i++) {
                    if (this.battleField[x - i][y].getShip() || this.battleField[x - i][y].getBorder()) {
                        return false;
                    }
                }
            } else if (!plus && !horizontal) {
                for (int i = 1; i < size; i++) {
                    if (this.battleField[x][y - i].getShip() || this.battleField[x][y - i].getBorder()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private Boolean generateMiddleShip() {
        Integer [] coordinate = this.generateShipBaseCoordinate();
        Boolean isShipCreated = false;
        for (Integer x = 0; x < 10; x++) {
            if (isShipCreated) {
                break;
            }
            for (Integer y = 0; y < 10; y++) {
                Boolean freeCell = !this.battleField[x][y].getShip() && !this.battleField[x][y].getBorder();
                if (coordinate[0] == x && coordinate[1] == y && freeCell) {
                    this.battleField[x][y].setShip(true);
                    if (x - 1 >= 0 && !this.battleField[x - 1][y].getShip() && !this.battleField[x - 1][y].getBorder()) {
                        this.battleField[x - 1][y].setShip(true);
                        isShipCreated = true;
                        break;
                    } else if (x + 1 <= 9 && !this.battleField[x + 1][y].getShip() && !this.battleField[x + 1][y].getBorder()) {
                        this.battleField[x + 1][y].setShip(true);
                        isShipCreated = true;
                        break;
                    } else if (y - 1 >= 0 && !this.battleField[x][y - 1].getShip() && !this.battleField[x][y - 1].getBorder()) {
                        this.battleField[x][y - 1].setShip(true);
                        isShipCreated = true;
                        break;
                    } else if (y + 1 <= 9 && !this.battleField[x][y + 1].getShip() && !this.battleField[x][y + 1].getBorder()) {
                        this.battleField[x][y + 1].setShip(true);
                        isShipCreated = true;
                        break;
                    }
                }
            }
        }
        this.setNeighbors();
        return isShipCreated;
    }

    private Boolean generateLittleShip() {
        Integer [] coordinate = this.generateShipBaseCoordinate();
        Boolean isShipCreated = false;
        for (Integer x = 0; x < 10; x++) {
            if (isShipCreated) {
                break;
            }
            for (Integer y = 0; y < 10; y++) {
                Boolean freeCell = !this.battleField[x][y].getShip() && !this.battleField[x][y].getBorder();
                if (coordinate[0] == x && coordinate[1] == y && freeCell) {
                    this.battleField[x][y].setShip(true);
                    isShipCreated = true;
                    break;
                }
            }
        }
        this.setNeighbors();
        return isShipCreated;
    }

    private void setNeighbors() {
        for (Integer x = 0; x < 10; x++) {
            for (Integer y = 0; y < 10; y++) {
                if (this.battleField[x][y].getShip()) {
                    Integer amountOfNeighbor = this.battleField[x][y].getNeighbors().length;
                    String [] neighbors = this.battleField[x][y].getNeighbors();
                    for (int n = 0; n < amountOfNeighbor; n++) {
                        Integer indexX = Integer.decode(Character.toString(neighbors[n].charAt(0)));
                        Integer indexY = Integer.decode(Character.toString(neighbors[n].charAt(1)));
                        if (!this.battleField[indexX][indexY].getShip()) {
                            this.battleField[indexX][indexY].setBorder(true);
                        }
                    }
                }
            }
        }
    }

    private Integer[] generateShipBaseCoordinate() {
        Integer coordinateX = (int)Math.round(Math.random() * 9);
        Integer coordinateY = (int)Math.round(Math.random() * 9);
        Integer [] coordinate = {coordinateX, coordinateY};
        return coordinate;
    }
}
