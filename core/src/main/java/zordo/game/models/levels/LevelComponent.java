package zordo.game.models.levels;

import zordo.game.models.menu.LevelItem;

public class LevelComponent extends LevelItem {
    LevelSize levelSize;
    boolean debugMode;

    public LevelComponent() {
        super();
        debugMode = false;
    }

    public void setLevelSize(LevelSize levelSize) {
        this.levelSize = levelSize;
    }

    public LevelSize getLevelSize() {
        return this.levelSize;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public boolean getDebugMode() {
        return this.debugMode;
    }
}
