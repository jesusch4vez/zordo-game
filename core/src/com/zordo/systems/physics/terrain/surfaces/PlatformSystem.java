package com.zordo.systems.physics.terrain.surfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.components.physics.terrain.surfaces.PlatformComponent;
import com.zordo.entities.characters.Character;

import java.util.ArrayList;

public class PlatformSystem {

    public static void solidPlatform(ArrayList<PlatformComponent> platforms, Character character) {
        for (PlatformComponent platform : platforms) {
            if(platform.getPlatform().overlaps(character.getCharacterComponent().getCollider())) {
                character.getCharacterComponent().setY(platform.getY() + platform.getPlatform().getHeight());
                character.getCharacterComponent().setIsJumping(false);
                character.getCharacterComponent().setIsAirborne(false);
                character.getCharacterComponent().setJumps(0);
            }
        }
    }

    public static void render(ArrayList<PlatformComponent> platforms, SpriteBatch batch) {
        for (PlatformComponent platform : platforms) {
            batch.draw(platform.getPlatformTexture(), platform.getX(), platform.getY(), platform.getPlatform().getWidth(), platform.getPlatform().getHeight());
        }
    }
}
