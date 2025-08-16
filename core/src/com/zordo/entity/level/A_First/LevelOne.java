package com.zordo.entity.level.A_First;

import com.zordo.LegendOfZordo;
import com.zordo.component.level.Level;

public class LevelOne extends Level {
	public LevelOne(LegendOfZordo game) {
		super(game);
		this.slat.setWidth(game.large.getWidth());
	}
}
