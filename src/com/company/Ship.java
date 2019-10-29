package com.company;

class Ship {
    String name;
    int size;
    static int numberOfShips = 7;
    static int currentShipToPlace = 0;

    public int getNumberOfShips() {
        return numberOfShips;
    }

    public void setNumberOfShips(int numberOfShips) {
        Ship.numberOfShips = numberOfShips;
    }

    String getName() {
        return name;
    }

    int getSize() {
        return size;
    }

    static class AircraftCarrier extends Ship {
        AircraftCarrier() {
            this.size = 5;
            this.name = "Carrier";
        }
    }

    static class BattleShip extends Ship {
        BattleShip() {
            this.size = 4;
            this.name = "Battleship";
        }
    }

    static class Cruiser extends Ship {
        Cruiser() {
            this.size = 3;
            this.name = "Cruiser";
        }
    }

    static class Destroyer extends Ship {
        Destroyer() {
            this.size = 2;
            this.name = "Destroyer";
        }
    }

    static class Submarine extends Ship {
        Submarine() {
            this.size = 1;
            this.name = "Submarine";
        }
    }
}
