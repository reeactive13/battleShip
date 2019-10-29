package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class GameLogic {
    private static int myHitCounter = 0;
    private static int enemyHitCounter = 0;

    static int getMyHitCounter() {
        return myHitCounter;
    }

    private static void setMyHitCounter(int myHitCounter) {
        GameLogic.myHitCounter = myHitCounter;
    }

    static int getEnemyHitCounter() {
        return enemyHitCounter;
    }

    private static void setEnemyHitCounter(int enemyHitCounter) {
        GameLogic.enemyHitCounter = enemyHitCounter;
    }

    private static List<Ship> createFleet() {
        List<Ship> myShips = new ArrayList<>();
        myShips.add(new Ship.AircraftCarrier());
        myShips.add(new Ship.BattleShip());
        myShips.add(new Ship.Cruiser());
        myShips.add(new Ship.Destroyer());
        myShips.add(new Ship.Destroyer());
        myShips.add(new Ship.Submarine());
        myShips.add(new Ship.Submarine());
        return myShips;
    }

    private static List<Ship> createEnemyFleet() {
        List<Ship> enemyShips = new ArrayList<>();
        enemyShips.add(new Ship.AircraftCarrier());
        enemyShips.add(new Ship.BattleShip());
        enemyShips.add(new Ship.Cruiser());
        enemyShips.add(new Ship.Destroyer());
        enemyShips.add(new Ship.Destroyer());
        enemyShips.add(new Ship.Submarine());
        enemyShips.add(new Ship.Submarine());
        return enemyShips;
    }

    static void fillField() {
        System.out.println("Filling your field\nChoose orientation and first point\n(1 - horizontal, 2 - vertical)\nExample:'1 0 6'");
        List<Ship> myShips = createFleet();
        for (int i = 0; i < Ship.numberOfShips; i++) {
            System.out.println("Orientation, X, Y:");
            Scanner input = new Scanner(System.in);
            int orientation = input.nextInt();
            int x = input.nextInt();
            int y = input.nextInt();
            if (!checkInput(orientation, x, y)) {
                continue;
            }
            if (Field.checkNeighbours(Field.getMyField(), myShips.get(Ship.currentShipToPlace), orientation, x, y)) {
                System.out.println("Ships can't touch each other!");
            } else {
                Field.placeShip(Field.getMyField(), myShips.get(Ship.currentShipToPlace), orientation, x, y);
            }
        }
        Ship.numberOfShips = 7;
        Ship.currentShipToPlace = 0;
        System.out.println("Filling enemy field\nChoose orientation and first point\n(1 - horizontal, 2 - vertical)\nExample:'1 0 6'");
        List<Ship> enemyShips = createEnemyFleet();
        for (int i = 0; i < Ship.numberOfShips; i++) {
            System.out.println("Orientation, X, Y:");
            Scanner input = new Scanner(System.in);
            int orientation = input.nextInt();
            int x = input.nextInt();
            int y = input.nextInt();
            if (!checkInput(orientation, x, y)) {
                continue;
            }
            if (Field.checkNeighbours(Field.getEnemyField(), enemyShips.get(Ship.currentShipToPlace), orientation, x, y)) {
                System.out.println("Ships can't touch each other!");
            } else {
                Field.placeShip(Field.getEnemyField(), enemyShips.get(Ship.currentShipToPlace), orientation, x, y);
            }
        }
    }

    private static boolean checkInput(int orientation, int x, int y) {
        if (x < 0 || x > 9 || y < 0 || y > 9 || (orientation != 1 && orientation != 2)) {
            System.out.println("Wrong input!");
            Ship.numberOfShips++;
            return false;
        }
        return true;
    }

    static void shoot(int player) {
        System.out.println("Choose target (x, y): ");
        Scanner input = new Scanner(System.in);
        int xShot = input.nextInt();
        int yShot = input.nextInt();
        if (!checkInput(1, xShot, yShot)) {
            System.out.println("Wrong input!");
            shoot(player);
        }
        switch (player) {
            case 1:
                String[][] tempField1 = Field.getEnemyField();
                if (tempField1[xShot][yShot] != null) {
                    System.out.println("Bullseye! Hitted enemy's " + tempField1[xShot][yShot]);
                    tempField1[xShot][yShot] = null;
                    Field.setEnemyField(tempField1);
                    setMyHitCounter(1 + getMyHitCounter());
                } else {
                    System.out.println("Miss.");
                }
                break;
            case 2:
                String[][] tempField2 = Field.getMyField();
                if (tempField2[xShot][yShot] != null) {
                    System.out.println("Bullseye! Hitted enemy's " + tempField2[xShot][yShot]);
                    tempField2[xShot][yShot] = null;
                    Field.setMyField(tempField2);
                    setEnemyHitCounter(1 + getEnemyHitCounter());
                } else {
                    System.out.println("Miss.");
                }
                break;
        }
    }
}
