package zordo;

import com.badlogic.gdx.Game;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.badlogic.gdx.controllers.Controllers;
import zordo.game.entities.world.level.Level;
import zordo.game.models.physics.world.WorldComponent;
import zordo.game.models.levels.LevelComponent;
import zordo.game.models.levels.LevelSize;
import zordo.game.entities.player_interface.menu.game.DebugModeMenu;
import zordo.game.entities.player_interface.menu.game.LevelMenu;
import zordo.game.entities.player_interface.menu.game.PauseMenu;
import zordo.game.entities.player_interface.menu.title.TitleMenu;
import zordo.game.systems.gamePad.GameControllerSystem;

public class LegendOfZordo extends Game {
    public GameControllerSystem controllerListener;

    public LevelSize small;
	public LevelSize medium;
	public LevelSize large;

    public Level activeLevel;

    public int MAX_VELOCITY;
    public int MAX_WALK_VELOCITY;
    public int WALK_SPEED;
    public int RUN_SPEED;
    public int MAX_UPWARD_VELOCITY;

    public boolean isOnTitleMenu;
    public boolean isOnLevelMenu;
    public boolean isOnPauseMenu;
    public boolean isOnLevel;
    public boolean isOnDebugMenu;

    public LevelMenu levelMenu;
    public DebugModeMenu debugModeMenu;
    public PauseMenu pauseMenu;
    public WorldComponent world;

    @Override
	public void create () {
        this.isOnTitleMenu = true;
        this.isOnLevelMenu = false;
        this.isOnPauseMenu = false;
        this.isOnLevel = false;
        this.isOnDebugMenu = false;
        controllerListener = new GameControllerSystem(this);
        Controllers.addListener(controllerListener);
        levelMenu = new LevelMenu(this);
        debugModeMenu = new DebugModeMenu(this, new LevelComponent());
        pauseMenu = new PauseMenu(this);
        activeLevel = new Level(this);
        world = new WorldComponent();

		Properties properties = new Properties();
		File propertiesFile = new File("game_config.properties");

		try (FileInputStream props = new FileInputStream(propertiesFile)) {
			properties.load(props);
			int small_width = Integer.parseInt(properties.getProperty("SMALL_WIDTH"));
			int small_height = Integer.parseInt(properties.getProperty("SMALL_HEIGHT"));

			int medium_width = Integer.parseInt(properties.getProperty("MEDIUM_WIDTH"));
			int medium_height = Integer.parseInt(properties.getProperty("MEDIUM_HEIGHT"));

			int large_width = Integer.parseInt(properties.getProperty("LARGE_WIDTH"));
			int large_height = Integer.parseInt(properties.getProperty("LARGE_HEIGHT"));

			small = new LevelSize(small_width, small_height);
			medium = new LevelSize(medium_width, medium_height);
			large = new LevelSize(large_width, large_height);

            MAX_VELOCITY = Integer.parseInt(properties.getProperty("MAX_VELOCITY"));
            RUN_SPEED = Integer.parseInt(properties.getProperty("RUN_SPEED"));
            WALK_SPEED = Integer.parseInt(properties.getProperty("WALK_SPEED"));
            MAX_WALK_VELOCITY = Integer.parseInt(properties.getProperty("MAX_WALK_VELOCITY"));
            MAX_UPWARD_VELOCITY = Integer.parseInt(properties.getProperty("MAX_UPWARD_VELOCITY"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setScreen(new TitleMenu(this));
	}

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {
        super.dispose();
    }

}

