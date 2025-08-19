package com.zordo.systems.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.entities.characters.Character;
import com.zordo.entities.world.level.Level;
import com.zordo.systems.health.HealthSystem;

public class HudSystem {

    public static void renderHUD(Character player) {
        SpriteBatch hudBatch = new SpriteBatch();
        BitmapFont font = new BitmapFont();
        hudBatch.begin();
        HealthSystem.healthRender(player.getCharacterComponent().getHearts(), hudBatch);
        font.getData().setScale(2);
        font.draw(hudBatch, "AIRBORNE " + player.getCharacterComponent().getIsAirborne().toString(), Gdx.graphics.getWidth()/2f - 250, Gdx.graphics.getHeight()/2f + 500);
        font.draw(hudBatch, "COLLIDING " + player.getCharacterComponent().getIsColliding().toString(), Gdx.graphics.getWidth()/2f - 250, Gdx.graphics.getHeight()/2f + 400);
        font.draw(hudBatch, "JUMPING " + player.getCharacterComponent().getIsJumping().toString(), Gdx.graphics.getWidth()/2f - 250, Gdx.graphics.getHeight()/2f + 300);
        font.draw(hudBatch, "RUNNING " + player.getCharacterComponent().getIsRunning().toString(), Gdx.graphics.getWidth()/2f - 250, Gdx.graphics.getHeight()/2f + 200);
        font.draw(hudBatch, "WALKING " + player.getCharacterComponent().getIsStepping().toString(), Gdx.graphics.getWidth()/2f - 250, Gdx.graphics.getHeight()/2f + 100);

        hudBatch.end();
    }

    public static void pause(Level level) {
        SpriteBatch hudBatch = new SpriteBatch();
        BitmapFont font = new BitmapFont();

        hudBatch.begin();
        font.getData().setScale(5);
        font.draw(hudBatch, "PAUSED", Gdx.graphics.getWidth()/2f - 250, Gdx.graphics.getHeight()/2f + 500);
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            level.paused = false;
        }
        hudBatch.end();
    }
}
