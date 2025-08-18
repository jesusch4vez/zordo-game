package com.zordo.components.health;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;
import com.zordo.components.Component;

public class Heart extends Component implements Disposable {
    String heartsuri = "character/player/hearts/";

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

    public Sprite getHeartState() {
        return this.heartState;
    }

    public void setHeartState(Sprite heartState) {
        this.heartState = heartState;
    }

    public int getHeartHealth() {
        return this.heartHealth;
    }

    public void setHeartHealth(int heartHealth) {
        this.heartHealth = heartHealth;
    }

    public void setHeartSegments(Sprite[] heartSegments) {
        this.heartSegments = heartSegments;
    }

    public Sprite[] getHeartSegments() {
        return this.heartSegments;
    }

    @Override
    public void dispose() {

    }
}