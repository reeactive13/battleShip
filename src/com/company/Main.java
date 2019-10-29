package com.company;

public class Main {

    public static void main(String[] args) {
        GameLogic.fillField();
        while (GameLogic.getMyHitCounter() < 18 && GameLogic.getEnemyHitCounter() < 18) {
            System.out.println("Your turn to shoot!");
            GameLogic.shoot(1); // my turn
            if (GameLogic.getMyHitCounter() == 18) {
                System.out.println("Player 1 destroyed enemy's fleet!");
            }
            System.out.println("Enemy's turn to shoot!");
            GameLogic.shoot(2); // enemy turn
            if (GameLogic.getEnemyHitCounter() == 18) {
                System.out.println("Player 1 destroyed enemy's fleet!");
            }
        }
    }
}

