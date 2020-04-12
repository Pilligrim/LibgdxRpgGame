package com.geekbrains.rpg.game.logic;

public class Calculator {
    public static int getDamage(int strength, float dps) {
        return (int) ((dps + strength * 10) / 3.5);
    }
}
