package com.BattleField;

import java.util.ArrayList;

public class Cell {
    private String addressId;
    private ArrayList<String> neighbors;
    private Boolean ship;
    private Boolean border;
    private String shipId;
    private Boolean wasUsed;

    public void setAddressId(String addressId) {
        if (addressId == null) {
            return;
        }
        this.addressId = addressId;
    }
    public String getAddressId() {
        return this.addressId;
    }

    public void setNeighbors(ArrayList<String> neighbors) {
        if (neighbors == null || neighbors.size() == 0) {
            return;
        }
        this.neighbors = neighbors;
    }
    public ArrayList<String> getNeighbors() {
        return this.neighbors;
    }

    public void setBorder(Boolean border) {
        if (border == null) {
            return;
        }
        this.border = border;
    }
    public Boolean getBorder() {
        return this.border;
    }

    public void setShip(Boolean ship) {
        if (ship == null) {
            return;
        }
        this.ship = ship;
    }
    public Boolean getShip() {
        return this.ship;
    }

    public void setShipId(String shipId) {
        if (shipId == null) {
            return;
        }
        this.shipId = shipId;
    }
    public String getShipId() {
        return this.shipId;
    }

    public void setWasUsed(Boolean wasUsed) {
        if (wasUsed == null) {
            return;
        }
        this.wasUsed = wasUsed;
    }

    public Boolean getWasUsed() {
        return this.wasUsed;
    }
}
