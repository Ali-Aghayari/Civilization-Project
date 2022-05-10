package models;

import models.Building.Building;
import models.Building.BuildingEnum;
import models.Resource.TileResource;
import models.Resource.TileResourceEnum;
import models.Tile.Tile;
import models.Tile.TileMode;
import models.Tile.TileModeEnum;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.NoneCombatUnits;
import models.Units.Unit;
import models.Units.UnitNameEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.platform.commons.util.FunctionUtils.where;

public class PlayerTest {
    private Player player1;
    private Player player2;
    private ArrayList<Player> players;
    private GameMap gameMap;
    private City city;

    @BeforeEach
    public void setUp() {
        players = new ArrayList<>();
        player1 = new Player(new User("ilya", "ilya", "ilya"));
        player2 = new Player(new User("paria", "paria", "paria"));
        players.add(player1);
        players.add(player2);
        gameMap = new GameMap(players);
        city = new City(gameMap.getTile(9, 7), gameMap, "firstCity");
        city.getBuildings().add(new Building(BuildingEnum.THEATER));
        player1.getCities().add(city);
        player1.startGame(0);
        gameMap.getTile(9,8).setResource(new TileResource(TileResourceEnum.SILVER));
    }

    @Test
    public void cityTest() {
        Assertions.assertEquals(city, player1.getCityByName("firstCity"));
    }

    @Test
    public void nullCityTest() {
        Assertions.assertEquals(null, player1.getCityByName("firstCity!"));
    }

    @Test
    public void attachCityTest() {
        City attachedCity = new City(gameMap.getTile(10, 10), gameMap, "secondPlayer");
        player2.getCities().add(attachedCity);
        player1.attachCity(attachedCity, player2);
        Assertions.assertTrue(player1.getCities().contains(attachedCity));
    }

    @Test
    public void endTurnTest() {
        player1.endTurn(gameMap);
        Tile tile = player1.getGameMap().getTile(0, 0);
        Assertions.assertTrue(tile == null);
    }

    @Test
    public void getTotalPopulationTest() {
        int population = player1.getTotalPopulation();
        Assertions.assertEquals(1, population);
    }

    @Test
    public void getTilesTest() {
        ArrayList<Tile> tiles = player1.getTiles();
        Assertions.assertEquals(tiles.size(), city.getTiles().size());
    }

    @Test
    public void getCombatTest() {
        ArrayList<Unit> combatUnits = player1.getCombatUnits();
        Assertions.assertEquals(1, combatUnits.size());
    }

    @Test
    public void getStrategicRecoursesTest() {
        player1.getCities().clear();
        ArrayList<TileResource> tileResources = player1.getStrategicResources();
        Assertions.assertEquals(0, tileResources.size());
    }


    @Test
    public void trueGetStrategicRecoursesTest() {
        gameMap.getTile(9, 7).setResource(new TileResource(TileResourceEnum.IRON));
        ArrayList<TileResource> tileResources = player1.getStrategicResources();
        Assertions.assertTrue(tileResources.size() > 0);
    }

    @Test
    public void canBuyTileTest() {
        boolean result = player1.canBuyTile(gameMap.getTile(10, 6), gameMap, city);
        Assertions.assertTrue(result);
    }

    @Test
    public void boughtTileTest() {
        player1.setBoughtTilesNumber(10);
        int result = player1.getBoughtTilesNumber();
        Assertions.assertEquals(10, result);
    }

    @Test
    public void cityBuildForPlayer() {
        city.setBeingBuild(new BeingBuild(new Building(BuildingEnum.CIRCUS)));
        player1.cityBuildForPlayer();
        Assertions.assertEquals(1, city.getBuildings().size());
    }

    @Test
    public void buildCityBuildForPlayer() {
        BeingBuild beingBuild = new BeingBuild(new Building(BuildingEnum.CIRCUS));
        city.setBeingBuild(beingBuild);
        beingBuild.setProductionCost(0);
        player1.cityBuildForPlayer();
        Assertions.assertEquals(2, city.getBuildings().size());
    }

    @Test
    public void unitBuildCityBuildForPlayer() {
        BeingBuild beingBuild = new BeingBuild(new CombatUnits(gameMap.getTile(2, 2), UnitNameEnum.SCOUT, player1));
        city.setBeingBuild(beingBuild);
        beingBuild.setProductionCost(0);
        player1.cityBuildForPlayer();
        Assertions.assertEquals(1, city.getBuildings().size());
    }

    @Test
    public void noneCombatBuildCityBuildForPlayer() {
        BeingBuild beingBuild = new BeingBuild(new NoneCombatUnits(gameMap.getTile(2, 2), UnitNameEnum.SETTLER, player1));
        city.setBeingBuild(beingBuild);
        beingBuild.setProductionCost(0);
        player1.cityBuildForPlayer();
        Assertions.assertEquals(1, city.getBuildings().size());
    }

}