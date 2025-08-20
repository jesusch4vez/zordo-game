package com.zordo.components.world.levels;

import com.zordo.LegendOfZordo;
import com.zordo.components.menu.LevelItem;

public class LevelComponent extends LevelItem {
    LegendOfZordo.LevelSize levelSize;

    public LevelComponent() {
        super();
    }

    public void setLevelSize(LegendOfZordo.LevelSize levelSize) {
        this.levelSize = levelSize;
    }

    public LegendOfZordo.LevelSize getLevelSize() {
        return this.levelSize;
    }
}
