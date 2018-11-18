package com.iantipov.game;

public class PlayerStats {
    public static final float DEFAULT_SPEED = 0.5f;
    public static final int DEFAULT_HP = 100;
    public static final int DEFAULT_ENERGY = 100;
    public static final int DEFAULT_DAMAGE = 1;
    public static final float DEFAULT_FIRE_INTERVAL = 0.3f;


    private float currentSpeed;
    private int currentHP;
    private int currentEnergy;
    private int currentDamage;
    private float currentFireInterval;

    private float maxSpeed;
    private int maxHP;
    private int maxEnergy;
    private float maxFireInterval;


    private int currentLevel;

    public PlayerStats() {
        currentSpeed = DEFAULT_SPEED;
        currentHP = DEFAULT_HP;
        currentEnergy = DEFAULT_ENERGY;
        currentDamage = DEFAULT_DAMAGE;
        currentFireInterval = DEFAULT_FIRE_INTERVAL;
        currentLevel = 1;
        maxSpeed = DEFAULT_SPEED;
        maxHP = DEFAULT_HP;
        maxEnergy = DEFAULT_ENERGY;
        maxFireInterval = DEFAULT_FIRE_INTERVAL;
    }

    public float getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(float currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public int getCurrentDamage() {
        return currentDamage;
    }

    public void setCurrentDamage(int currentDamage) {
        this.currentDamage = currentDamage;
    }

    public float getCurrentFireInterval() {
        return currentFireInterval;
    }

    public void setCurrentFireInterval(float currentFireInterval) {
        this.currentFireInterval = currentFireInterval;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public float getMaxFireInterval() {
        return maxFireInterval;
    }

    public void setMaxFireInterval(float maxFireInterval) {
        this.maxFireInterval = maxFireInterval;
    }


}
