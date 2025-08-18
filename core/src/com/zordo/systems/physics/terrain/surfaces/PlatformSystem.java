package com.zordo.systems.physics.terrain.surfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.components.physics.terrain.surfaces.PlatformComponent;

import java.util.ArrayList;

public class PlatformSystem {
    public static void render(ArrayList<PlatformComponent> platforms, SpriteBatch batch) {
        for (PlatformComponent platform : platforms) {
            batch.draw(platform.getPlatformTexture(), platform.getX(), platform.getY(), platform.getPlatform().getWidth(), platform.getPlatform().getHeight());
        }
    }
}
