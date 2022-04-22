package models.Tile;

public enum TileModeEnum {
    desert("desert", 0, 0, 0, -0.33, 1.0),
    grassland("grassland", 2, 0, 0, -0.33, 1.0),
    hill("hill", 0, 2, 0, 0.25, 2.0),
    mountain("mountain", 0, 0, 0, 0, Double.POSITIVE_INFINITY),
    ocean("ocean", 0, 0, 0, 0, Double.POSITIVE_INFINITY),
    PLAIN("plain", 1, 1, 0, -0.33, 1.0),
    snow("snow", 0, 0, 0, -0.33, 1.0),
    tundra("tundra", 1, 0, 0, -0.33, 1.0);

    private final String name;
    private final int food;
    private final int production;
    private final int gold;
    private final double troopBoost;
    private final Double movementCost;


    TileModeEnum(String name, int food, int production, int gold, double troopBoost, Double movementCost) {
        this.name = name;
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.troopBoost = troopBoost;
        this.movementCost = movementCost;
    }

    public String getName() {
        return name;
    }

    public int getFood() {
        return food;
    }

    public int getProduction() {
        return production;
    }

    public int getGold() {
        return gold;
    }

    public double getTroopBoost() {
        return troopBoost;
    }

    public Double getMovementCost() {
        return movementCost;
    }
}