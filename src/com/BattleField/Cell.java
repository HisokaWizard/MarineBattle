package com.BattleField;

public class Cell {
    private String addressId;
    private String [] neighbors;
    private Boolean ship;
    private Boolean border;

    public void setAddressId(String addressId) {
        if (addressId == null) {
            return;
        }
        this.addressId = addressId;
    }

    public String getAddressId() {
        return this.addressId;
    }

    public void setNeighbors(String[] neighbors) {
        if (neighbors == null || neighbors.length == 0) {
            return;
        }
        this.neighbors = neighbors;
    }

    public String[] getNeighbors() {
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
}
