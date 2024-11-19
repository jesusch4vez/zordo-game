package com.zordo.view.levels.A_First;

import com.zordo.LegendOfZordo;
import com.zordo.view.levels.Level;

public class LevelOne extends Level {
	public LevelOne(LegendOfZordo game) {
		super(game);
		this.slat.setWidth(game.large.getWidth());
	}
}
