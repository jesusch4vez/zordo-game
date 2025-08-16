package com.zordo.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zordo.LegendOfZordo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "The Legend of Zordo";
		config.height = 400;
		config.width = 800;
		config.addIcon("environment/triforce.png", Files.FileType.Internal);
		new LwjglApplication(new LegendOfZordo(), config);
	}
}
