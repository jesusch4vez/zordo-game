package com.zordo.systems.physics.terrain.surfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.components.physics.terrain.surfaces.PlatformComponent;

public class PlatformSystem {
    public static void render(PlatformComponent platform, SpriteBatch batch) {
        batch.draw(platform.getPlatformTexture(), platform.getX(), platform.getY(), platform.getPlatform().getWidth(), platform.getPlatform().getHeight());
    }
}
