package com.zordo.systems.health;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.components.health.Heart;

import java.util.ArrayList;

public class HealthSystem {
    public static void healthRender(ArrayList<Heart> hearts, SpriteBatch batch) {
        for( int i = 0; i < hearts.size(); i++) {
            batch.draw(hearts.get(i).getHeartState(), 200 + i*10, 200);
        }
    }

    public static void healthRender(ArrayList<Heart> hearts, SpriteBatch batch, int x, int y) {
        if(!hearts.isEmpty()) {
            for( int i = 0; i < hearts.size(); i++) {
                batch.draw(hearts.get(i).getHeartState(),x + i * 10,y);
            }
        }
    }
}
