package com.zordo.system.levels.intro;

import com.zordo.LegendOfZordo;
import com.zordo.system.levels.Level;

public class LevelIntro extends Level {
	public LevelIntro(LegendOfZordo game) {
		super(game);
		this.slat.setWidth(game.medium.getWidth());
	}
}
