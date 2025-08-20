package com.zordo.systems.physics.terrain.surfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.zordo.components.physics.terrain.surfaces.PlatformComponent;
import com.zordo.entities.characters.Character;

import java.util.ArrayList;

public class PlatformSystem {

    public static void solidPlatform(ArrayList<PlatformComponent> platforms, Character character) {
        for (PlatformComponent platform : platforms) {
            Vector2 plat = new Vector2(platform.getPlatform().getX(), platform.getPlatform().getY());
            Vector2 chara = new Vector2(character.getCharacterComponent().getCollider().getX(), character.getCharacterComponent().getCollider().getY());
            Vector2 platCenter = platform.getPlatform().getCenter(plat);
            Vector2 charCenter = character.getCharacterComponent().getCollider().getCenter(chara);
            if (!character.getCharacterComponent().getIsColliding() && platform.getPlatform().overlaps(character.getCharacterComponent().getCollider())) {
                if ((platCenter.y < charCenter.y) || platformIsBelow(character,platform)) {
                    character.getCharacterComponent().setIsColliding(true);
                    character.getCharacterComponent().setIsAirborne(false);
                    character.getCharacterComponent().setIsJumping(false);
                    character.getCharacterComponent().setIsDescending(false);
                    character.getCharacterComponent().setJumps(0);

                    platform.setHoldsCharacter(true);
                    platform.setCharacterRelativePosition("Character is above");
                    character.getCharacterComponent().setY(platform.getY() + platform.getPlatform().getHeight() - 2);
                } else if ((platCenter.y > charCenter.y) || (platformIsAbove(character,platform) && !platformIsOnRight(character,platform) && !platformisOnLeft(character,platform))) {
                    character.getCharacterComponent().setIsColliding(true);
                    character.getCharacterComponent().setIsAirborne(true);
                    character.getCharacterComponent().setIsDescending(true);
                    character.getCharacterComponent().setIsAscending(false);

                    platform.setHoldsCharacter(false);
                    platform.setCharacterRelativePosition("Character is below");
                    character.getCharacterComponent().setY(platform.getY() - character.getCharacterComponent().getCollider().getHeight() - 5);
                } else if ((platCenter.x < charCenter.x) && !platformIsAbove(character,platform)
                        && !platformIsBelow(character,platform)
                        && platformisOnLeft(character,platform)) {
                    character.getCharacterComponent().setIsColliding(true);
                    platform.setHoldsCharacter(false);
                    platform.setCharacterRelativePosition("Character is left");
                    character.getCharacterComponent().setX(platform.getX() + platform.getWidth() + 10);
                } else if ((platCenter.x > charCenter.x) && !platformIsAbove(character,platform)
                        && !platformIsBelow(character,platform)
                        && platformIsOnRight(character,platform)) {
                    character.getCharacterComponent().setIsColliding(true);
                    platform.setCharacterRelativePosition("Character is right");
                    platform.setHoldsCharacter(false);
                    character.getCharacterComponent().setX(platform.getX() - character.getCharacterComponent().getCollider().getWidth() - 10);
                }
                character.getCharacterComponent().setIsColliding(true);
            }
        }

        if(!character.getCharacterComponent().getIsColliding()) {
            character.getCharacterComponent().setIsAirborne(true);
        }
    }


    public static Boolean platformIsAbove(Character character, PlatformComponent platform) {
       return platform.getPlatform().getY() > character.getCharacterComponent().getCollider().getY() + character.getCharacterComponent().getCollider().getHeight()- 30;
    };

    public static Boolean platformIsBelow(Character character, PlatformComponent platform) {
        return platform.getPlatform().getY() + platform.getHeight() - 30 < character.getCharacterComponent().getCollider().getY();
    };

    public static Boolean platformisOnLeft(Character character, PlatformComponent platform) {
        return platform.getPlatform().getX() + platform.getWidth() - 30 < character.getCharacterComponent().getCollider().getX();
    };

    public static Boolean platformIsOnRight(Character character, PlatformComponent platform) {
        return platform.getPlatform().getX() > character.getCharacterComponent().getCollider().getX() + character.getCharacterComponent().getCollider().getWidth() - 30;
    };

    public static void render(ArrayList<PlatformComponent> platforms, SpriteBatch batch) {
        BitmapFont font = new BitmapFont();

//        font.getData().setScale(2);

        for (PlatformComponent platform : platforms) {
            font.draw(batch, platform.getCharacterRelativePosition(), platform.getX(), platform.getY());
            batch.draw(platform.getPlatformTexture(), platform.getX(), platform.getY(), platform.getPlatform().getWidth(), platform.getPlatform().getHeight());
        }
    }
}
