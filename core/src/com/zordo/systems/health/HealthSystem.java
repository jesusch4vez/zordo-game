package com.zordo.systems.health;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.components.health.HeartComponent;
import com.zordo.entities.characters.Character;

import java.util.ArrayList;

public class HealthSystem {
    public static void healthRender(ArrayList<HeartComponent> hearts, SpriteBatch batch) {
        for( int i = 0; i < hearts.size(); i++) {
            batch.draw(hearts.get(i).getHeartState(), 200 + i*10, 200);
        }
    }

    public static void healthRender(ArrayList<HeartComponent> hearts, SpriteBatch batch, int x, int y) {
        if(!hearts.isEmpty()) {
            for( int i = 0; i < hearts.size(); i++) {
                batch.draw(hearts.get(i).getHeartState(),x + i * 10,y);
            }
        }
    }

    public static void damageHeart(HeartComponent heart) {
        heart.setHeartHealth(heart.getHeartHealth()-1);
        if(heart.getHeartHealth() >= 0) {
            heart.setHeartState(heart.getHeartSegments()[heart.getHeartHealth()]);
        }
    }

    public void damage(ArrayList<HeartComponent> hearts, Character character) {
        damageHeart(topHeart(hearts));
        if(topHeart(hearts).getHeartHealth() <= 0) {
            character.getCharacterComponent().health--;
        }
    }

    private HeartComponent topHeart(ArrayList<HeartComponent> hearts) {
        return hearts.get(topHeartIndex(hearts));
    }

    private int topHeartIndex(ArrayList<HeartComponent> hearts) {
        if(!hearts.isEmpty()) {
            return hearts.size() - 1;
        } else {
            return 0;
        }
    }
}
