package com.zordo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.zordo.components.world.levels.LevelSize;
import com.zordo.entities.player_interface.menu.title.TitleMenu;

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
			int small_width = Integer.parseInt(properties.getProperty("SMALL_WIDTH"));
			int small_height = Integer.parseInt(properties.getProperty("SMALL_HEIGHT"));

			int medium_width = Integer.parseInt(properties.getProperty("MEDIUM_WIDTH"));
			int medium_height = Integer.parseInt(properties.getProperty("MEDIUM_HEIGHT"));

			int large_width = Integer.parseInt(properties.getProperty("LARGE_WIDTH"));
			int large_height = Integer.parseInt(properties.getProperty("LARGE_HEIGHT"));

			small = new LevelSize(small_width, small_height);
			medium = new LevelSize(medium_width, medium_height);
			large = new LevelSize(large_width, large_height);

		} catch (IOException e) {
			e.printStackTrace();
		}

//		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
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

