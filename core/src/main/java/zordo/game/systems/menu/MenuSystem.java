package zordo.game.systems.menu;

import zordo.LegendOfZordo;
import zordo.game.models.levels.LevelComponent;
import zordo.game.entities.player_interface.menu.game.DebugModeMenu;
import zordo.game.entities.player_interface.menu.game.PauseMenu;
import zordo.game.entities.world.level.Level;

import java.io.IOException;

public class MenuSystem {
    public static void debugModeMenu(LegendOfZordo game, LevelComponent selectedLevel) {
        game.setScreen(new DebugModeMenu(game, selectedLevel));
    }

    public static void activateLevel(LegendOfZordo game, LevelComponent level) throws IOException {
        Level loadLevel = new Level(game);
        loadLevel.setDebugMode(level.getDebugMode());
//        loadLevel.setLevelSize(level.getLevelSize());
//        loadLevel.setLevelTheme(level.getLevelTheme());
//        loadLevel.setLevelTerrain(level.setLevelTerrain());
//        loadLevel.setLevelCharacters(level.setLevelCharacters());
        game.activeLevel = loadLevel;
        game.setScreen(game.activeLevel);
    }

    public static void pauseMenu(LegendOfZordo game) {
        game.setScreen(new PauseMenu(game));
    }
}
