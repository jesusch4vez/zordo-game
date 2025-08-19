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
        hudBatch.begin();
        HealthSystem.healthRender(player.getCharacterComponent().getHearts(), hudBatch);
        hudBatch.end();
    }

    public static void pause(Level level) {
        SpriteBatch hudBatch = new SpriteBatch();
        BitmapFont font = new BitmapFont();

        hudBatch.begin();
        font.draw(hudBatch, ">>>PAUSED<<<", 200, 200);
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            level.paused = false;
        }
        hudBatch.end();
    }
}
