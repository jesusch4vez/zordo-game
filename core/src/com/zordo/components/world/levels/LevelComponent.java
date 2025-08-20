package com.zordo.components.world.levels;

import com.zordo.components.menu.LevelItem;

public class LevelComponent extends LevelItem {
    LevelSize levelSize;

    public LevelComponent() {
        super();
    }

    public void setLevelSize(LevelSize levelSize) {
        this.levelSize = levelSize;
    }

    public LevelSize getLevelSize() {
        return this.levelSize;
    }
}
