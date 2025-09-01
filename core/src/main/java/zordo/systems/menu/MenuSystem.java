package zordo.systems.menu;

import zordo.LegendOfZordo;
import zordo.models.world.levels.LevelComponent;
import zordo.entities.player_interface.menu.game.DebugModeMenu;
import zordo.entities.player_interface.menu.game.PauseMenu;
import zordo.entities.world.level.Level;

public class MenuSystem {
    public static void debugModeMenu(LegendOfZordo game, LevelComponent selectedLevel) {
        game.setScreen(new DebugModeMenu(game, selectedLevel));
    }

    public static void activateLevel(LegendOfZordo game, LevelComponent level) {
        Level loadLevel = new Level(game);
        loadLevel.setDebugMode(level.getDebugMode());
        loadLevel.setLevelSize(level.getLevelSize());
//        loadLevel.setLevelTheme(level.getLevelTheme());
//        loadLevel.setLevelTerrain(level.setLevelTerrain());
//        loadLevel.setLevelCharacters(level.setLevelCharacters());
        game.setScreen(loadLevel);
    }

    public static void pauseMenu(LegendOfZordo game, Level level) {
        game.setScreen(new PauseMenu(game, level));
    }
}
