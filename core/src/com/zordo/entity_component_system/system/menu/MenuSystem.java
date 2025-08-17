package com.zordo.entity_component_system.system.menu;

import com.zordo.LegendOfZordo;
import com.zordo.entity_component_system.component.menu.LevelItem;
import com.zordo.entity_component_system.entity.level.Level;

public class MenuSystem {
    public static void activateLevel(LegendOfZordo game, LevelItem levelItem) {
        Level level = new Level(game, levelItem.getName());
        game.setScreen(level);
    }
}
