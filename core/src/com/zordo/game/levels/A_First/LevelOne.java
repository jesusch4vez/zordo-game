package com.zordo.game.levels.A_First;

import com.zordo.game.LegendOfZordo;
import com.zordo.game.levels.Level;

public class LevelOne extends Level {
	public LevelOne(LegendOfZordo game) {
		super(game);
		this.slat.setWidth(game.large.getWidth());
	}
}
