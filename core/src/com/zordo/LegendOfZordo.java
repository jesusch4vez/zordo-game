package com.zordo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.zordo.entity_component_system.entity.menu.Title;

public class LegendOfZordo extends Game {

	@Override
	public void create () {
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		this.setScreen(new Title(this));
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

