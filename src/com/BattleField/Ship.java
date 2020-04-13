package com.BattleField;

import java.util.ArrayList;

public class Ship {
    private String shipId;
    private ArrayList<String> shipPositions;
    private Integer shipLength;

    public void setShipId(String shipId) {
        if (shipId == null) {
            return;
        }
        this.shipId = shipId;
    }
    public String getShipId() {
        return this.shipId;
    }

    public void setShipPositions(ArrayList<String> positions) {
        if (positions == null && positions.size() != 0) {
            return;
        }
        this.shipPositions = positions;
        this.shipLength = this.shipPositions.size();
    }

    public String checkAttack(String position) {
        String result = null;
        for (Integer i = 0; i < this.shipPositions.size(); i++) {
            if (position.equals(this.shipPositions.get(i))) {
                this.shipLength--;
                this.shipPositions.remove(this.shipPositions.get(i));
                if (this.shipLength == 0) {
                    result = "Destroyed: " + this.shipId;
                    break;
                } else if (this.shipLength > 0) {
                    result = "Hurt";
                    break;
                }
            } else {
                result = "Past";
            }
        }
        return result;
    }
}
