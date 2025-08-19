package com.zordo.systems.camera;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.entities.characters.Character;
import com.zordo.systems.health.HealthSystem;

public class HudSystem {

    public static void renderHUD(Character player) {
        SpriteBatch hudBatch = new SpriteBatch();
        hudBatch.begin();
        HealthSystem.healthRender(player.getCharacterComponent().getHearts(), hudBatch);
        hudBatch.end();
    }
}
