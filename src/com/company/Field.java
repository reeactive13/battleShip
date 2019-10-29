package com.company;

class Field {
    private final static int rows = 10;
    private final static int columns = 10;
    private static String[][] myField = new String[rows][columns];
    private static String[][] EnemyField = new String[rows][columns];

    static String[][] getMyField() {
        return myField;
    }

    static void setMyField(String[][] myField) {
        Field.myField = myField;
    }

    static String[][] getEnemyField() {
        return EnemyField;
    }

    static void setEnemyField(String[][] enemyField) {
        EnemyField = enemyField;
    }

    static boolean checkNeighbours(String[][] field, Ship ship, int orientation, int firstPointX, int firstPointY) {
        boolean hasNeighbours = true;
        switch (orientation) {
            case 1:
                for (int i = firstPointX - 1; i <= firstPointX + 1; i++) {
                    if (i < 0 || i > 9) {
                        continue;
                    }
                    for (int j = firstPointY - 1; j <= firstPointY + ship.getSize(); j++) {
                        if (j < 0 || j > 9) {
                            continue;
                        }
                        hasNeighbours = field[i][j] != null;
                        if (hasNeighbours) {
                            Ship.numberOfShips++;
                            return true;
                        }
                    }
                }
                break;
            case 2:
                for (int i = firstPointX - 1; i <= firstPointX + ship.getSize(); i++) {
                    if (i < 0 || i > 9) {
                        continue;
                    }
                    for (int j = firstPointY - 1; j <= firstPointY + 1; j++) {
                        if (j < 0 || j > 9) {
                            continue;
                        }
                        hasNeighbours = field[i][j] != null;
                        if (hasNeighbours) {
                            Ship.numberOfShips++;
                            return true;
                        }
                    }
                }
                break;
            default:
                System.out.println("Wrong orientation!");
        }
        return hasNeighbours;
    }

    //TODO: check full gameplay
    static void placeShip(String[][] field, Ship ship, int orientation, int firstPointX, int firstPointY) {
        switch (orientation) {
            case 1: // horizontal (left->right)
                if (firstPointY > columns - ship.size) {
                    System.out.println("Impossible to place ship here. Out of bounds.");
                    Ship.numberOfShips++;
                } else {
                    Ship.currentShipToPlace++;
                    for (int i = firstPointY; i <= firstPointY + ship.getSize() - 1; i++) {
                        field[firstPointX][i] = ship.getName();
                    }
                }
                break;
            case 2: // vertical (up->down)
                if (firstPointX > rows - ship.getSize()) {
                    System.out.println("Impossible to place ship here. Out of bounds");
                    Ship.numberOfShips++;
                } else {
                    Ship.currentShipToPlace++;
                    for (int i = firstPointX; i <= firstPointX + ship.getSize() - 1; i++) {
                        field[i][firstPointY] = ship.getName();
                    }
                }
                break;
            default:
                System.out.println("Wrong orientation!");
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.printf("%s  ", field[i][j]);
            }
            System.out.println();
        }
    }
}

