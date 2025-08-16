package com.zordo.entity.level.intro;

import com.zordo.LegendOfZordo;
import com.zordo.component.level.Level;

public class LevelIntro extends Level {
	public LevelIntro(LegendOfZordo game) {
		super(game);
		this.slat.setWidth(game.medium.getWidth());
	}
}
