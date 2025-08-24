package com.zordo.systems.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.LegendOfZordo;
import com.zordo.entities.characters.Character;
import com.zordo.entities.player_interface.menu.title.TitleMenu;
import com.zordo.entities.world.level.Level;
import com.zordo.systems.health.HealthSystem;
import com.zordo.systems.menu.MenuSystem;

import java.util.concurrent.TimeUnit;

public class HudSystem {

    public static void renderHUD(Character player) {
        SpriteBatch hudBatch = new SpriteBatch();
        hudBatch.begin();
        HealthSystem.healthRender(player.getCharacterComponent().getHearts(), hudBatch);

        hudBatch.end();
    }

    public static void pause(LegendOfZordo game, Level level) {
        SpriteBatch hudBatch = new SpriteBatch();
        BitmapFont font = new BitmapFont();

        hudBatch.begin();
        font.getData().setScale(5);
        font.draw(hudBatch, "PAUSED", Gdx.graphics.getWidth()/2f - 250, Gdx.graphics.getHeight()/2f + 500);
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            level.paused = false;
        }

        if(level.paused) {
            MenuSystem.pauseMenu(game, level);
        }
        hudBatch.end();
    }
}
