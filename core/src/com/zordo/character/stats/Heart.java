package com.zordo.character.stats;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Heart {
	String heartsuri = "hearts/";
	
	private Sprite heartState;
	private Sprite[] heartSegments;
	private int heartHealth;
	
	public Heart() {
		this.heartSegments = new Sprite[5];
		this.heartHealth = 4;
	    // heart textures
		for(int i = 0; i < this.heartSegments.length; i++) {
			this.heartSegments[i] = new Sprite(new Texture(heartsuri + "heart-" + i + ".png"));
		}
		this.heartState = this.heartSegments[4];
	}
	
	public void damageHeart() {
		this.heartHealth = this.heartHealth -1;
		if(this.heartHealth >= 0) {
			this.heartState = this.heartSegments[this.heartHealth];
		}
	}
	
	public Sprite getHeartState() {
		return this.heartState;
	}
	
	public void setHeartState(Sprite heartState) {
		this.heartState = heartState;
	}
	
	public int getHeartHealth() {
		return this.heartHealth;
	}
	
}
