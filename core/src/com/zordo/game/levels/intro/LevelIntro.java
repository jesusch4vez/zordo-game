package com.zordo.game.levels.intro;

import com.zordo.game.LegendOfZordo;
import com.zordo.game.levels.Level;

public class LevelIntro extends Level {
	public LevelIntro(LegendOfZordo game) {
		super(game);
		this.slat.setWidth(game.medium.getWidth());
	}
}
