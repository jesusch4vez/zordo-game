package com.zordo.systems.physics.terrain.surfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.zordo.components.physics.terrain.surfaces.PlatformComponent;
import com.zordo.entities.characters.Character;

import java.util.ArrayList;

public class PlatformSystem {

    public static void solidPlatform(ArrayList<PlatformComponent> platforms, Character character) {
        Vector2 chara = new Vector2(character.getCharacterComponent().getCollider().getX(), character.getCharacterComponent().getCollider().getY());
        for (PlatformComponent platform : platforms) {
            Vector2 plat = new Vector2(platform.getX(), platform.getY());
            Vector2 platCenter = platform.getPlatform().getCenter(plat);
            Vector2 charCenter = character.getCharacterComponent().getCollider().getCenter(chara);
            if (!character.getCharacterComponent().getIsColliding() && !platform.getHoldsCharacter() && platform.getPlatform().overlaps(character.getCharacterComponent().getCollider()) && platCenter.y < charCenter.y) {
                character.getCharacterComponent().setY(platform.getY() + platform.getPlatform().getHeight()-2);
                character.getCharacterComponent().setIsColliding(true);
                character.getCharacterComponent().setIsAirborne(false);
                character.getCharacterComponent().setIsJumping(false);
                character.getCharacterComponent().setJumps(0);

                platform.setHoldsCharacter(true);
            }
        }

        if(!character.getCharacterComponent().getIsColliding()) {
            character.getCharacterComponent().setIsAirborne(true);
        }
    }

    public static void render(ArrayList<PlatformComponent> platforms, SpriteBatch batch) {
        for (PlatformComponent platform : platforms) {
            batch.draw(platform.getPlatformTexture(), platform.getX(), platform.getY(), platform.getPlatform().getWidth(), platform.getPlatform().getHeight());
        }
    }
}
