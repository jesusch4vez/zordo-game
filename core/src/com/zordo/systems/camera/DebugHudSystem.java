package com.zordo.systems.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.entities.characters.Character;
import com.zordo.entities.world.level.Level;

public class DebugHudSystem {
    public static void renderDebugHud(Character player, Level level, OrthographicCamera camera) {
        SpriteBatch hudBatch = new SpriteBatch();
        hudBatch.begin();
        BitmapFont font = new BitmapFont();

        font.getData().setScale(2);
        font.draw(hudBatch, "AIRBORNE      " + player.getCharacterComponent().getIsAirborne().toString(), camera.viewportWidth, camera.viewportHeight - 50);
        font.draw(hudBatch, "COLLIDING     " + player.getCharacterComponent().getIsColliding().toString(), camera.viewportWidth, camera.viewportHeight - 100);
        font.draw(hudBatch, "JUMPING     " + player.getCharacterComponent().getIsJumping().toString(), camera.viewportWidth, camera.viewportHeight - 150);
        font.draw(hudBatch, "RUNNING     " + player.getCharacterComponent().getIsRunning().toString(), camera.viewportWidth, camera.viewportHeight - 200);
        font.draw(hudBatch, "WALKING     " + player.getCharacterComponent().getIsStepping().toString(), camera.viewportWidth, camera.viewportHeight - 250);
        font.draw(hudBatch, "ASCENDING   " + player.getCharacterComponent().getIsAscending().toString(), camera.viewportWidth, camera.viewportHeight - 300);
        font.draw(hudBatch, "DESCENDING  " + player.getCharacterComponent().getIsDescending().toString(), camera.viewportWidth, camera.viewportHeight - 350);

        font.draw(hudBatch, "LEVEL ", camera.viewportWidth, camera.viewportHeight - 450);
        font.draw(hudBatch, "SIZE " + level.getLevelSize().getWidth() + "x" + level.getLevelSize().getHeight(), camera.viewportWidth, camera.viewportHeight - 500);

        font.draw(hudBatch, "PRIOR COORDINATES", camera.viewportWidth, camera.viewportHeight - 550);
        font.draw(hudBatch, player.getCharacterComponent().getPreviousPosition().x + " , "+ player.getCharacterComponent().getPreviousPosition().y, camera.viewportWidth, camera.viewportHeight - 600);

        font.draw(hudBatch, "COORDINATES", camera.viewportWidth, camera.viewportHeight - 650);
        font.draw(hudBatch, player.getCharacterComponent().getPosition().x + " , "+ player.getCharacterComponent().getPosition().y, camera.viewportWidth, camera.viewportHeight - 700);
        hudBatch.end();
    }
}
