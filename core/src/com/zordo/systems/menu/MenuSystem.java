package com.zordo.systems.menu;

import com.zordo.LegendOfZordo;
import com.zordo.components.menu.LevelItem;
import com.zordo.entities.world.level.Level;

public class MenuSystem {
    public static void activateLevel(LegendOfZordo game, LevelItem levelItem) {
        Level level = new Level(game, levelItem.getName());
        game.setScreen(level);
    }
}
