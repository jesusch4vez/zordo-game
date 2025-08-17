package com.zordo.systems.menu;

import com.zordo.LegendOfZordo;
import com.zordo.entities.world.level.Level;

public class MenuSystem {
    public static void activateLevel(LegendOfZordo game) {
        Level level = new Level(game);
        game.setScreen(level);
    }
}
