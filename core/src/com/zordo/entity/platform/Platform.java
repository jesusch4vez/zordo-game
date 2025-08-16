package com.zordo.entity.platform;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Platform extends Rectangle {
	
	private final Texture platformTexture;

	public Platform() {
		super();
		super.setWidth(100);
		super.setHeight(10);
		platformTexture = new Texture("environment/platform-square.png");
	}
	
	public void setCoordinates(int x, int y) {
		super.setX(x);
		super.setY(y);
	}
	
	public float getX() {
		return super.getX();
	}
	
	public float getY() {
		return super.getY();
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(platformTexture, getX(), getY(),super.getWidth(),super.getHeight());
	}
}
