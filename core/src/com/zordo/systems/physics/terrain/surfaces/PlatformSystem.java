package com.zordo.systems.physics.terrain.surfaces;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.components.physics.terrain.surfaces.PlatformComponent;
import com.zordo.entities.characters.Character;

import java.util.ArrayList;

public class PlatformSystem {

    public static void solidPlatform(ArrayList<PlatformComponent> platforms, Character character) {
        for (PlatformComponent platform : platforms) {
            if (!character.getCharacterComponent().getIsColliding() && platform.getPlatform().overlaps(character.getCharacterComponent().getCollider())) {
                character.getCharacterComponent().setPosition(character.getCharacterComponent().getPreviousPosition());
                if (platformIsBelow(character,platform)) {
                    character.getCharacterComponent().setIsColliding(true);
                    character.getCharacterComponent().setIsAirborne(false);
                    character.getCharacterComponent().setIsJumping(false);
                    character.getCharacterComponent().setIsDescending(false);
                    character.getCharacterComponent().setJumps(0);

                    platform.setHoldsCharacter(true);
                    platform.setCharacterRelativePosition("Character is above");
                    character.getCharacterComponent().setY(platform.getY() + platform.getHeight());
                    break;
                } else if (platformIsAbove(character,platform)) {
                    character.getCharacterComponent().setIsColliding(true);
                    character.getCharacterComponent().setIsAirborne(true);
                    character.getCharacterComponent().setIsDescending(true);
                    character.getCharacterComponent().setIsAscending(false);

                    platform.setHoldsCharacter(false);
                    platform.setCharacterRelativePosition("Character is below");
                    character.getCharacterComponent().setY(platform.getY() - character.getCharacterComponent().getCollider().getHeight());
                    break;
                } else if (platformisOnLeft(character,platform)) {
                    character.getCharacterComponent().setIsColliding(true);
                    platform.setHoldsCharacter(false);
                    platform.setCharacterRelativePosition("Character is right");
                    character.getCharacterComponent().setX(platform.getX() + platform.getWidth());
                    break;
                } else if (platformIsOnRight(character,platform)) {
                    character.getCharacterComponent().setIsColliding(true);
                    platform.setCharacterRelativePosition("Character is left");
                    platform.setHoldsCharacter(false);
                    character.getCharacterComponent().setX(platform.getX() - character.getCharacterComponent().getCollider().getWidth());
                    break;
                }
                character.getCharacterComponent().setIsColliding(true);
            } else {
                character.getCharacterComponent().setIsColliding(false);
                if (platformIsBelow(character,platform)) {
                    platform.setCharacterRelativePosition("Character is above");
                } else if (platformIsAbove(character,platform)) {
                    platform.setCharacterRelativePosition("Character is below");
                } else if (platformisOnLeft(character,platform)) {
                    platform.setCharacterRelativePosition("Character is right");
                } else if (platformIsOnRight(character,platform)) {
                    platform.setCharacterRelativePosition("Character is left");
                }
            }
        }

        if(!character.getCharacterComponent().getIsColliding()) {
            character.getCharacterComponent().setIsAirborne(true);
        }
    }


    public static Boolean platformIsAbove(Character character, PlatformComponent platform) {
       return platform.getPlatform().getY() >= character.getCharacterComponent().getCollider().getY() + character.getCharacterComponent().getCollider().getHeight();
    };

    public static Boolean platformIsBelow(Character character, PlatformComponent platform) {
        return platform.getPlatform().getY() + platform.getHeight() <= character.getCharacterComponent().getCollider().getY();
    };

    public static Boolean platformisOnLeft(Character character, PlatformComponent platform) {
        return (platform.getPlatform().getX() + platform.getWidth() <= character.getCharacterComponent().getCollider().getX()) && !platformIsAbove(character,platform) && !platformIsBelow(character,platform);
    };

    public static Boolean platformIsOnRight(Character character, PlatformComponent platform) {
        return platform.getPlatform().getX() >= character.getCharacterComponent().getCollider().getX() + character.getCharacterComponent().getCollider().getWidth() && !platformIsAbove(character,platform) && !platformIsBelow(character,platform);
    };

    public static void render(ArrayList<PlatformComponent> platforms, SpriteBatch batch) {
        BitmapFont font = new BitmapFont();

        font.getData().setScale(2);

        for (PlatformComponent platform : platforms) {
            long widthCoordinate = (long) platform.getX() + (long) platform.getWidth();
            long heightCoordinate = (long) platform.getY() + (long) platform.getHeight();
            font.draw(batch, platform.getX() + ", " + platform.getY(), platform.getX(), platform.getY() - 25);

            font.draw(batch, widthCoordinate + ", " + heightCoordinate, platform.getX() + platform.getWidth(), platform.getY() + platform.getHeight() + 25);

            font.draw(batch, platform.getCharacterRelativePosition(), platform.getX(), platform.getY());
            batch.draw(platform.getPlatformTexture(), platform.getX(), platform.getY(), platform.getPlatform().getWidth(), platform.getPlatform().getHeight());
        }
    }
}
