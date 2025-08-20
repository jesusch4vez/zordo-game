package com.zordo.systems.physics.terrain.surfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.zordo.components.physics.terrain.surfaces.PlatformComponent;
import com.zordo.entities.characters.Character;

import java.util.ArrayList;

public class PlatformSystem {

    public static void solidPlatform(ArrayList<PlatformComponent> platforms, Character character) {
        for (PlatformComponent platform : platforms) {
            Vector2 plat = new Vector2(platform.getX(), platform.getY());
            Vector2 chara = new Vector2(character.getCharacterComponent().getX(), character.getCharacterComponent().getY());
            Vector2 platCenter = platform.getPlatform().getCenter(plat);
            Vector2 charCenter = character.getCharacterComponent().getCollider().getCenter(chara);
            if (!character.getCharacterComponent().getIsColliding() && platform.getPlatform().overlaps(character.getCharacterComponent().getCollider())) {

                // platform is below character
                if(platCenter.y < charCenter.y) {
                    character.getCharacterComponent().setIsColliding(true);
                    character.getCharacterComponent().setIsAirborne(false);
                    character.getCharacterComponent().setIsJumping(false);
                    character.getCharacterComponent().setJumps(0);

                    platform.setHoldsCharacter(true);
                    character.getCharacterComponent().setY(platform.getY() + platform.getPlatform().getHeight() - 2);
                }
                // platform is above character


                // platform is to the left of character
                else if(platCenter.x < charCenter.x) {
                    character.getCharacterComponent().setIsColliding(true);

                    platform.setHoldsCharacter(false);
                    character.getCharacterComponent().setX(platform.getX() + character.getCharacterComponent().getCollider().getWidth() + 5);
                }

                // platform is to the right of character
                else if(platCenter.x >= charCenter.x) {
                    character.getCharacterComponent().setIsColliding(true);

                    platform.setHoldsCharacter(false);
                    character.getCharacterComponent().setX(platform.getX() - 5);
                }

                else if(!platform.getIsJumpable() && platCenter.y >= charCenter.y) {
                    character.getCharacterComponent().setIsColliding(true);
                    character.getCharacterComponent().setIsAirborne(true);

                    platform.setHoldsCharacter(false);
                    character.getCharacterComponent().setY(platform.getY() - character.getCharacterComponent().getCollider().getHeight() - 5);
                }

            }

//            if (!platform.getIsJumpable() && !character.getCharacterComponent().getIsColliding() && !platform.getHoldsCharacter() && platform.getPlatform().overlaps(character.getCharacterComponent().getCollider()) && platCenter.y > charCenter.y) {
//                character.getCharacterComponent().setY(platform.getY()-2);
//                character.getCharacterComponent().setIsColliding(true);
//            }
//
//            if (!character.getCharacterComponent().getIsColliding() && !platform.getHoldsCharacter() && platform.getPlatform().overlaps(character.getCharacterComponent().getCollider()) && platCenter.x > charCenter.x) {
//                character.getCharacterComponent().setX(platform.getX()+2);
//                character.getCharacterComponent().setIsColliding(true);
//            }
//
//            if (!character.getCharacterComponent().getIsColliding() && !platform.getHoldsCharacter() && platform.getPlatform().overlaps(character.getCharacterComponent().getCollider()) && platCenter.x < charCenter.x) {
//                character.getCharacterComponent().setX(platform.getX()-2);
//                character.getCharacterComponent().setIsColliding(true);
//            }
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
