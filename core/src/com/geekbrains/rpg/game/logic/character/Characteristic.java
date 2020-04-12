package com.geekbrains.rpg.game.logic.character;

public class Characteristic {
    private int level;
    private int currExperience;
    private int maxExperience;
    private int baseLevelUpExperience = 1000;
    private int strength;

    public Characteristic(int level) {
        this.level = level;
        this.currExperience = 0;
        this.maxExperience = getMaxExperienceByLevel();
        this.strength = 15 + 5 * level;
    }

    public Characteristic() {
        this(1);
    }

    public void addExperience(int experience) {
        currExperience += experience;
        if (currExperience >= maxExperience) {
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        this.currExperience = 0;
        this.maxExperience = getMaxExperienceByLevel();
        this.strength += 5;
    }

    private int getMaxExperienceByLevel() {
        return maxExperience = baseLevelUpExperience * (int) Math.pow(2, level - 1);
    }

    public int getLevel() {
        return level;
    }

    public int getCurrExperience() {
        return currExperience;
    }

    public void setCurrExperience(int currExperience) {
        this.currExperience = currExperience;
    }

    public int getMaxExperience() {
        return maxExperience;
    }

    public int getStrength() {
        return strength;
    }
}
