package controllers.GameControllers;

import controllers.Output;
import models.Improvement.TileImprovement;
import models.Improvement.TileImprovementEnum;
import models.Player;
import models.Units.Nonecombat.BuilderUnit;

public class BuilderController {
    //TODO : کلاس ها همشون با یونیتن ولی فک کنم باید تقسیم کنم ؟!

    public Output repair(Player player, BuilderUnit unit) {
        if (unit.getPlayer() != player) return Output.UNIT_NOT_YOURS;
        if (!unit.isAWorker()) return Output.NOT_A_WORKER;
        if (unit.getIsWorking()) return Output.WORKER_IS_BUSY;
        // TODO :  فک کنم باید یه بولین بزاریم برای اینکه بفهمیم خراب شده یا نه بعد بره اون چیزی ک خراب شده رو درست کنه ؟
        // todo :  پس یه ارور برای نبودن یه چیز مخروبه میزاریم
//        assignWorker(unit,"repair");
        return null;
    }

    public Output removeTileFeature(Player player, BuilderUnit unit) {
        if (unit.getPlayer() != player) return Output.UNIT_NOT_YOURS;
        if (!unit.isAWorker()) return Output.NOT_A_WORKER;
        if (unit.getIsWorking()) return Output.WORKER_IS_BUSY;
        if (unit.getPosition().getFeature() == null) return Output.NO_FEATURE_TO_REMOVE;
        assignWorker(unit, "remove feature");
        return Output.REMOVING_FEATURE;
    }

    public Output makeARoad(Player player, BuilderUnit unit) {
        if (unit.getPlayer() != player) return Output.UNIT_NOT_YOURS;
        if (!unit.isAWorker()) return Output.NOT_A_WORKER;
        if (unit.getIsWorking()) return Output.WORKER_IS_BUSY;
        if (unit.getPosition().getHasRoad()) return Output.TILE_HAS_ROAD;
        assignWorker(unit, "create road");
        return Output.BUILDING_ROAD;
    }


    public Output improveTile(Player player, BuilderUnit unit, TileImprovementEnum improvement) {
        if (unit.getPlayer() != player) return Output.UNIT_NOT_YOURS;
        if (!unit.isAWorker()) return Output.NOT_A_WORKER;
        if (unit.getIsWorking()) return Output.WORKER_IS_BUSY;
        TileImprovement temp = new TileImprovement(improvement);
        temp.getRequiredTech();
        temp.getWhereCanBeFind();
        if (temp.getRequiredTech() != null && player.getFullyResearchedTechByEnum(temp.getRequiredTech()) == null)
            return Output.YOUR_TECH_IS_BEHIND;
        if (!unit.getPosition().checkEnums(temp.getWhereCanBeFind())) return Output.CANT_PUT_AN_IMPROVEMENT_HERE;
        assignWorker(unit, improvement.getName());
        return Output.IMPROVING_TILE;
    }

    private void assignWorker(BuilderUnit unit, String work) {
        unit.setIsWorking(true);
        unit.setWork(work);
        unit.setTurn(0);
    }


}