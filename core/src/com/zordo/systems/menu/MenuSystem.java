package com.zordo.systems.menu;

import com.zordo.LegendOfZordo;
import com.zordo.components.world.levels.LevelComponent;
import com.zordo.entities.world.level.Level;

public class MenuSystem {
    public static void activateLevel(LegendOfZordo game, LevelComponent level) {
        Level loadLevel = new Level(game);
        loadLevel.setLevelSize(level.getLevelSize());
//        loadLevel.setLevelTheme(level.getLevelTheme());
//        loadLevel.setLevelTerrain(level.setLevelTerrain());
//        loadLevel.setLevelCharacters(level.setLevelCharacters());
        game.setScreen(loadLevel);
    }
}
