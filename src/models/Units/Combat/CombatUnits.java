package models.Units.Combat;

import models.Units.Units;

public class CombatUnits extends Units {
    protected UnitNameEnum unitName;
    protected UnitTypeEnum unitType;
    protected int maxHealth;
    protected int maxDefendingDamage;
    protected int maxAttackingDamage;
    protected int currentHealth;

    public float calculateAttack() {
        return 0;
    }

    public UnitNameEnum getUnitName() {
        return unitName;
    }

    public void setUnitName(UnitNameEnum unitName) {
        this.unitName = unitName;
    }

    public UnitTypeEnum getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitTypeEnum unitType) {
        this.unitType = unitType;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getMaxDefendingDamage() {
        return maxDefendingDamage;
    }

    public void setMaxDefendingDamage(int maxDefendingDamage) {
        this.maxDefendingDamage = maxDefendingDamage;
    }

    public int getMaxAttackingDamage() {
        return maxAttackingDamage;
    }

    public void setMaxAttackingDamage(int maxAttackingDamage) {
        this.maxAttackingDamage = maxAttackingDamage;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
}