package com.zordo.systems.character.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.entities.characters.Character;
import com.zordo.entities.world.level.Level;
import com.zordo.systems.character.animation.AnimationSystem;

public class PlayerMovementSystem extends CharacterMovementSystem {
    static float jump2 = 0;
    static float elapsed;

    public static void move(Character character, SpriteBatch batch, Level level) {
        if (!level.paused) {
            elapsed += Gdx.graphics.getDeltaTime();
            character.getCharacterComponent().setPosition(character.getCharacterComponent().getCollider().x, character.getCharacterComponent().getCollider().y);

            if (!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                character.getCharacterComponent().setIsStepping(false);
                character.getCharacterComponent().setIsRunning(false);
                character.getCharacterComponent().setIsJumping(false);
                AnimationSystem.standRender(character);
            }

            else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                character.getCharacterComponent().getCollider().x += 100 * Gdx.graphics.getDeltaTime();
                character.getCharacterComponent().setIsStepping(true);
                character.getCharacterComponent().setIsFlippedRight(true);
                character.getCharacterComponent().setIsRunning(false);
                AnimationSystem.walkRender(character);
                if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                    character.getCharacterComponent().getCollider().x += 125 * Gdx.graphics.getDeltaTime();
                    character.getCharacterComponent().setIsStepping(false);
                    character.getCharacterComponent().setIsFlippedRight(true);
                    character.getCharacterComponent().setIsRunning(true);
                    AnimationSystem.runRender(character);
                }
            }

            else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                character.getCharacterComponent().getCollider().x -= 100 * Gdx.graphics.getDeltaTime();
                character.getCharacterComponent().setIsStepping(true);
                character.getCharacterComponent().setIsFlippedRight(false);
                character.getCharacterComponent().setIsRunning(false);
                AnimationSystem.walkRender(character);
                if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
                    character.getCharacterComponent().setIsRunning(true);
                    character.getCharacterComponent().getCollider().x -= 125 * Gdx.graphics.getDeltaTime();
                    character.getCharacterComponent().setIsStepping(false);
                    character.getCharacterComponent().setIsFlippedRight(false);
                    AnimationSystem.runRender(character);
                }
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && character.getCharacterComponent().getJumps() < 2) {
                character.getCharacterComponent().setIsStanding(false);
                character.getCharacterComponent().setIsJumping(true);
                character.getCharacterComponent().setIsAirborne(true);
                if (character.getCharacterComponent().getJumps() == 0) {
                    character.getCharacterComponent().getCollider().y += (100 * Gdx.graphics.getDeltaTime()) + 75;
//                    if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
//                        character.getCharacterComponent().getCollider().y += 50;
//                    }
                    jump2 = character.getCharacterComponent().getCollider().y * 1.5f;
                } else {
                    character.getCharacterComponent().getCollider().y = jump2;
                }
                character.getCharacterComponent().setJumps(character.getCharacterComponent().getJumps() + 1);
            }

            if(character.getCharacterComponent().getIsAirborne()) {
                AnimationSystem.jumpRender(character);
                character.getCharacterComponent().getCollider().y -= 120 * Gdx.graphics.getDeltaTime();
            }
        }
        AnimationSystem.animate(character, batch, elapsed, level);
    }
}
