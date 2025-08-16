package com.zordo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.zordo.system.level.LevelSize;
import com.zordo.component.title.Screen;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LegendOfZordo extends Game {
    public LevelSize small;
	public LevelSize medium;
	public LevelSize large;

	@Override
	public void create () {
        Properties properties = new Properties();
		File propertiesFile = new File("../src/main/resources/game_config.properties");

		try (FileInputStream props = new FileInputStream(propertiesFile)) {
			properties.load(props);
			float small_width = Float.parseFloat(properties.getProperty("SMALL_WIDTH"));
			float small_height = Float.parseFloat(properties.getProperty("SMALL_HEIGHT"));

			float medium_width = Float.parseFloat(properties.getProperty("MEDIUM_WIDTH"));
			float medium_height = Float.parseFloat(properties.getProperty("MEDIUM_HEIGHT"));

			float large_width = Float.parseFloat(properties.getProperty("LARGE_WIDTH"));
			float large_height = Float.parseFloat(properties.getProperty("LARGE_HEIGHT"));

			small = new LevelSize(small_width, small_height);
			medium = new LevelSize(medium_width, medium_height);
			large = new LevelSize(large_width, large_height);

		} catch (IOException e) {
			e.printStackTrace();
		}
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		this.setScreen(new Screen(this));
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

