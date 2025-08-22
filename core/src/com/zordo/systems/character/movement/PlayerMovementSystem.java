package com.zordo.systems.character.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.entities.characters.Character;
import com.zordo.entities.world.level.Level;
import com.zordo.systems.character.animation.AnimationSystem;
import com.zordo.systems.physics.terrain.surfaces.PlatformSystem;

public class PlayerMovementSystem extends CharacterMovementSystem {
    static float elapsed;

    public static void move(Character character, SpriteBatch batch, Level level) {
        if (!level.paused) {

            elapsed += Gdx.graphics.getDeltaTime();
            character.getCharacterComponent().setPosition(character.getCharacterComponent().getCollider().x, character.getCharacterComponent().getCollider().y);

            character.getCharacterComponent().setIsStepping(false);
            character.getCharacterComponent().setIsRunning(false);
            character.getCharacterComponent().setIsJumping(false);
            AnimationSystem.standRender(character);
            character.getCharacterComponent().getCollider().y -= 100 * Gdx.graphics.getDeltaTime();
            PlatformSystem.solidPlatform(level.platforms, character);
//            if (!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//                character.getCharacterComponent().setIsStepping(false);
//                character.getCharacterComponent().setIsRunning(false);
//                character.getCharacterComponent().setIsJumping(false);
//                AnimationSystem.standRender(character);
//            }

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                character.getCharacterComponent().setPreviousPosition(character.getCharacterComponent().getPosition());
                character.getCharacterComponent().getCollider().x += 100 * Gdx.graphics.getDeltaTime();
                PlatformSystem.solidPlatform(level.platforms, character);
                character.getCharacterComponent().setIsStepping(true);
                character.getCharacterComponent().setIsFlippedRight(true);
                character.getCharacterComponent().setIsRunning(false);
                AnimationSystem.walkRender(character);
                if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                    character.getCharacterComponent().getCollider().x += 125 * Gdx.graphics.getDeltaTime();
                    PlatformSystem.solidPlatform(level.platforms, character);
                    character.getCharacterComponent().setIsStepping(false);
                    character.getCharacterComponent().setIsFlippedRight(true);
                    character.getCharacterComponent().setIsRunning(true);
                    AnimationSystem.runRender(character);
                }
            }

            else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                character.getCharacterComponent().setPreviousPosition(character.getCharacterComponent().getPosition());
                character.getCharacterComponent().getCollider().x -= 100 * Gdx.graphics.getDeltaTime();
                PlatformSystem.solidPlatform(level.platforms, character);
                character.getCharacterComponent().setIsStepping(true);
                character.getCharacterComponent().setIsFlippedRight(false);
                character.getCharacterComponent().setIsRunning(false);
                AnimationSystem.walkRender(character);
                if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
                    character.getCharacterComponent().setIsRunning(true);
                    character.getCharacterComponent().getCollider().x -= 125 * Gdx.graphics.getDeltaTime();
                    PlatformSystem.solidPlatform(level.platforms, character);
                    character.getCharacterComponent().setIsStepping(false);
                    character.getCharacterComponent().setIsFlippedRight(false);
                    AnimationSystem.runRender(character);
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                character.getCharacterComponent().setPreviousPosition(character.getCharacterComponent().getPosition());
                character.getCharacterComponent().setIsStanding(false);
                character.getCharacterComponent().setIsJumping(true);
                character.getCharacterComponent().setIsAirborne(true);
                character.getCharacterComponent().setIsAscending(true);
                character.getCharacterComponent().setIsDescending(false);
                character.getCharacterComponent().setOnSurface(false);
                character.getCharacterComponent().setIsColliding(false);
                character.getCharacterComponent().getCollider().y += 175 * Gdx.graphics.getDeltaTime();
                PlatformSystem.solidPlatform(level.platforms, character);
                AnimationSystem.jumpRender(character);
            }

//            else if(!Gdx.input.isKeyPressed(Input.Keys.SPACE) && character.getCharacterComponent().getIsAirborne()) {
//                character.getCharacterComponent().setPreviousPosition(character.getCharacterComponent().getPosition());
//                character.getCharacterComponent().setIsDescending(true);
//                character.getCharacterComponent().setIsAscending(false);
//                character.getCharacterComponent().setIsJumping(false);
//                AnimationSystem.jumpRender(character);
//
//                PlatformSystem.solidPlatform(level.platforms, character);
//            }
        }
        AnimationSystem.animate(character, batch, elapsed, level);
    }
}
