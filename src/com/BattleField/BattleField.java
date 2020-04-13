package com.BattleField;

import java.util.ArrayList;
import java.util.Scanner;

public class BattleField {
    private Cell [][] battleField;
    private ArrayList<Ship> shipList = new ArrayList<Ship>();
    private final int fieldSize = 10;

    public void fightProcess() {
        if (this.shipList.size() != 10 && this.getBattleField().length != 0) {
            return;
        }
        Integer amountOfAttack = 0;
        while(true) {
            Scanner inputAttackPosition = new Scanner(System.in);
            System.out.print("Set cell: ");
            String position = inputAttackPosition.nextLine();
            amountOfAttack++;
            for (Integer i = 0; i < this.shipList.size(); i++) {
                Ship ship = this.shipList.get(i);
                String result = ship.checkAttack(position);
                if (result == "Destroyed: " + ship.getShipId()) {
                    this.shipList.remove(ship);
                }
                System.out.println(result);
            }
            if (this.shipList.size() == 0) {
                System.out.println("You win: " + amountOfAttack + " tries");
                break;
            }
        }
    }

    public void generateBattleField() {
        this.battleField = this.generateEmptyField();
        for (Integer i = 0; i < 3; i++) {
            while (true) {
                if(this.generateMiddleShip(2, i)) {
                    break;
                }
            }
        }
        while (true) {
            if(this.generateHugeShips(4, 1)) {
                break;
            }
        }
        for (Integer i = 0; i < 2; i++) {
            while (true) {
                if(this.generateHugeShips(3, i)) {
                    break;
                }
            }
        }
        for (Integer i = 0; i < 4; i++) {
            while (true) {
                if(this.generateLittleShip(1, i)) {
                    break;
                }
            }
        }
    }

    public Cell[][] getBattleField() {
        return this.battleField;
    }

    private Cell[][] generateEmptyField() {

        Cell [][] field = new Cell[this.fieldSize][this.fieldSize];
        for (Integer indexX = 0; indexX < this.fieldSize; indexX++) {
            for (Integer indexY = 0; indexY < this.fieldSize; indexY++) {
                field[indexX][indexY] = new Cell();
                field[indexX][indexY].setAddressId(indexX.toString() + indexY.toString());
                field[indexX][indexY].setNeighbors(this.selectNeighbors(indexX, indexY));
                field[indexX][indexY].setBorder(false);
                field[indexX][indexY].setShip(false);
            }
        }
        return field;
    }

    private ArrayList<String> selectNeighbors(Integer indexX, Integer indexY) {
        ArrayList<String> neighbors = new ArrayList<String>();
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
        return neighbors;
    }

    private Boolean generateHugeShips(Integer size, Integer number) {
        Integer [] coordinate = this.generateShipBaseCoordinate();
        Boolean isShipCreated = false;
        Boolean shipNotClimb = false;
        for (Integer x = 0; x < this.fieldSize; x++) {
            if (shipNotClimb || isShipCreated) {
                break;
            }
            for (Integer y = 0; y < this.fieldSize; y++) {
                Boolean freeCell = !this.battleField[x][y].getShip() && !this.battleField[x][y].getBorder();
                if (coordinate[0] == x && coordinate[1] == y && freeCell) {
                    if (size == 4) {
                        if (x + 3 <= 9) {
                            if (this.checkAllShipLengthFree(x, y, size, true, true)) {
                                Integer [] valuesX = {x, x + 1, x + 2, x + 3};
                                Integer [] valuesY = {y, y, y, y};
                                this.initShip(valuesX, valuesY, size, number);
                            } else {
                                shipNotClimb = true;
                                break;
                            }
                        } else if (x - 3 >= 0) {
                            if (this.checkAllShipLengthFree(x, y, size, false, true)) {
                                Integer [] valuesX = {x, x - 1, x - 2, x - 3};
                                Integer [] valuesY = {y, y, y, y};
                                this.initShip(valuesX, valuesY, size, number);
                            } else {
                                shipNotClimb = true;
                                break;
                            }
                        } else if (y + 3 <= 9) {
                            if (this.checkAllShipLengthFree(x, y, size, true, false)) {
                                Integer [] valuesX = {x, x, x, x};
                                Integer [] valuesY = {y, y + 1, y + 2, y + 3};
                                this.initShip(valuesX, valuesY, size, number);
                            } else {
                                shipNotClimb = true;
                                break;
                            }
                        } else if (y - 3 >= 0) {
                            if (this.checkAllShipLengthFree(x, y, size, false, false)) {
                                Integer [] valuesX = {x, x, x, x};
                                Integer [] valuesY = {y, y - 1, y - 2, y - 3};
                                this.initShip(valuesX, valuesY, size, number);
                            }  else {
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
                                Integer [] valuesX = {x, x + 1, x + 2};
                                Integer [] valuesY = {y, y, y};
                                this.initShip(valuesX, valuesY, size, number);
                            } else {
                                shipNotClimb = true;
                                break;
                            }
                        } else if (x - 2 >= 0) {
                            if (this.checkAllShipLengthFree(x, y, size, false, true)) {
                                Integer [] valuesX = {x, x - 1, x - 2};
                                Integer [] valuesY = {y, y, y};
                                this.initShip(valuesX, valuesY, size, number);
                            } else {
                                shipNotClimb = true;
                                break;
                            }
                        } else if (y + 2 <= 9) {
                            if (this.checkAllShipLengthFree(x, y, size, true, false)) {
                                Integer [] valuesX = {x, x, x};
                                Integer [] valuesY = {y, y + 1, y + 2};
                                this.initShip(valuesX, valuesY, size, number);
                            } else {
                                shipNotClimb = true;
                                break;
                            }
                        } else if (y - 2 >= 0) {
                            if (this.checkAllShipLengthFree(x, y, size, false, false)) {
                                Integer [] valuesX = {x, x, x};
                                Integer [] valuesY = {y, y - 1, y - 2};
                                this.initShip(valuesX, valuesY, size, number);
                            } else {
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

    private void initShip(Integer [] valuesX, Integer [] ValuesY, Integer size, Integer number) {
        Ship ship = new Ship();
        ArrayList<String> positions = new ArrayList<String>();
        if (size == 4) {
            for (Integer i = 0; i < 4; i++) {
                positions.add(valuesX[i].toString() + ValuesY[i].toString());
                this.battleField[valuesX[i]][ValuesY[i]].setShip(true);
            }
            ship.setShipPositions(positions);
            ship.setShipId("Huge");
            this.shipList.add(ship);
        }
        if (size == 3) {
            for (Integer i = 0; i < 3; i++) {
                positions.add(valuesX[i].toString() + ValuesY[i].toString());
                this.battleField[valuesX[i]][ValuesY[i]].setShip(true);
            }
            ship.setShipPositions(positions);
            ship.setShipId("Big_" + number);
            this.shipList.add(ship);
        }
        if (size == 2) {
            for (Integer i = 0; i < 2; i++) {
                positions.add(valuesX[i].toString() + ValuesY[i].toString());
                this.battleField[valuesX[i]][ValuesY[i]].setShip(true);
            }
            ship.setShipPositions(positions);
            ship.setShipId("Middle_" + number);
            this.shipList.add(ship);
        }
        if (size == 1) {
            positions.add(valuesX[0].toString() + ValuesY[0].toString());
            this.battleField[valuesX[0]][ValuesY[0]].setShip(true);
            ship.setShipPositions(positions);
            ship.setShipId("Little_" + number);
            this.shipList.add(ship);
        }
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

    private Boolean generateMiddleShip(Integer size, Integer number) {
        Integer [] coordinate = this.generateShipBaseCoordinate();
        Boolean isShipCreated = false;
        for (Integer x = 0; x < this.fieldSize; x++) {
            if (isShipCreated) {
                break;
            }
            for (Integer y = 0; y < this.fieldSize; y++) {
                Boolean freeCell = !this.battleField[x][y].getShip() && !this.battleField[x][y].getBorder();
                if (coordinate[0] == x && coordinate[1] == y && freeCell) {
                    if (x - 1 >= 0 && !this.battleField[x - 1][y].getShip() && !this.battleField[x - 1][y].getBorder()) {
                        Integer [] valuesX = {x, x - 1}; Integer [] valuesY = {y, y};
                        this.initShip(valuesX, valuesY, size, number);
                        isShipCreated = true;
                        break;
                    } else if (x + 1 <= 9 && !this.battleField[x + 1][y].getShip() && !this.battleField[x + 1][y].getBorder()) {
                        Integer [] valuesX = {x, x + 1}; Integer [] valuesY = {y, y};
                        this.initShip(valuesX, valuesY, size, number);
                        isShipCreated = true;
                        break;
                    } else if (y - 1 >= 0 && !this.battleField[x][y - 1].getShip() && !this.battleField[x][y - 1].getBorder()) {
                        Integer [] valuesX = {x, x}; Integer [] valuesY = {y, y - 1};
                        this.initShip(valuesX, valuesY, size, number);
                        isShipCreated = true;
                        break;
                    } else if (y + 1 <= 9 && !this.battleField[x][y + 1].getShip() && !this.battleField[x][y + 1].getBorder()) {
                        Integer [] valuesX = {x, x}; Integer [] valuesY = {y, y + 1};
                        this.initShip(valuesX, valuesY, size, number);
                        isShipCreated = true;
                        break;
                    }
                }
            }
        }
        this.setNeighbors();
        return isShipCreated;
    }

    private Boolean generateLittleShip(Integer size, Integer number) {
        Integer [] coordinate = this.generateShipBaseCoordinate();
        Boolean isShipCreated = false;
        for (Integer x = 0; x < this.fieldSize; x++) {
            if (isShipCreated) {
                break;
            }
            for (Integer y = 0; y < this.fieldSize; y++) {
                Boolean freeCell = !this.battleField[x][y].getShip() && !this.battleField[x][y].getBorder();
                if (coordinate[0] == x && coordinate[1] == y && freeCell) {
                    Integer [] valuesX = {x}; Integer [] valuesY = {y};
                    this.initShip(valuesX, valuesY, size, number);
                    isShipCreated = true;
                    break;
                }
            }
        }
        this.setNeighbors();
        return isShipCreated;
    }

    private void setNeighbors() {
        for (Integer x = 0; x < this.fieldSize; x++) {
            for (Integer y = 0; y < this.fieldSize; y++) {
                if (this.battleField[x][y].getShip()) {
                    ArrayList<String> neighbors = this.battleField[x][y].getNeighbors();
                    neighbors.forEach(neighbor -> {
                        Integer indexX = Integer.parseInt(Character.toString(neighbor.charAt(0)));
                        Integer indexY = Integer.parseInt(Character.toString(neighbor.charAt(1)));
                        if (!this.battleField[indexX][indexY].getShip()) {
                            this.battleField[indexX][indexY].setBorder(true);
                        }
                    });
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
