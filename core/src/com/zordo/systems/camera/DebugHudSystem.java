package com.zordo.systems.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.entities.characters.Character;
import com.zordo.entities.world.level.Level;

public class DebugHudSystem {
    public static void renderDebugHud(Character player, Level level) {
        SpriteBatch hudBatch = new SpriteBatch();
        hudBatch.begin();
        BitmapFont font = new BitmapFont();

        font.getData().setScale(2);
        font.draw(hudBatch, "AIRBORNE " + player.getCharacterComponent().getIsAirborne().toString(), Gdx.graphics.getWidth()- 250, Gdx.graphics.getHeight() - 50);
        font.draw(hudBatch, "COLLIDING " + player.getCharacterComponent().getIsColliding().toString(), Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight() - 100);
        font.draw(hudBatch, "JUMPING " + player.getCharacterComponent().getIsJumping().toString(), Gdx.graphics.getWidth()- 250, Gdx.graphics.getHeight() - 150);
        font.draw(hudBatch, "RUNNING " + player.getCharacterComponent().getIsRunning().toString(), Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight() - 200);
        font.draw(hudBatch, "WALKING " + player.getCharacterComponent().getIsStepping().toString(), Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight() - 250);

        font.draw(hudBatch, "LEVEL ", Gdx.graphics.getWidth() - 300, Gdx.graphics.getHeight() - 350);
        font.draw(hudBatch, "SIZE " + level.getLevelSize().getWidth() + "x" + level.getLevelSize().getHeight(), Gdx.graphics.getWidth() - 300, Gdx.graphics.getHeight() - 400);

        hudBatch.end();
    }
}
