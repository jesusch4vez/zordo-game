package com.zordo.systems.character.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.entities.characters.Character;
import com.zordo.systems.character.animation.AnimationSystem;
import com.zordo.systems.health.HealthSystem;

public class PlayerMovementSystem extends CharacterMovementSystem {
    static int jumps = 0;
    static float jump2 = 0;
    static Boolean stepping = true;
    static float elapsed;

    public static void move(Character character, SpriteBatch batch) {
        elapsed += Gdx.graphics.getDeltaTime();
        character.getCharacterComponent().setPosition(character.getCharacterComponent().getCollider().x, character.getCharacterComponent().getCollider().y);

        HealthSystem.healthRender(character.getCharacterComponent().getHearts(), batch);
        if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            stepping = false;
            AnimationSystem.standRender(character,batch);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            stepping = true;
            AnimationSystem.moveRightRender(character,batch);
        } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            stepping = true;
            AnimationSystem.moveLeftRender(character,batch);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && jumps < 2) {
            character.getCharacterComponent().setJumping(true);
            if(jumps == 0) {
                character.getCharacterComponent().getCollider().y += (100 * Gdx.graphics.getDeltaTime()) + 75;
                if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                    character.getCharacterComponent().getCollider().y += 50;
                }
                jump2 = character.getCharacterComponent().getCollider().y * 1.5f;
            } else {
                character.getCharacterComponent().getCollider().y = jump2;
            }
            jumps++;
        }

        if(character.getCharacterComponent().getJumping()) {
            stepping = false;
            AnimationSystem.jumpRender(character, batch);
        }

        if(character.getCharacterComponent().getCollider().y > 11) {
            character.getCharacterComponent().getCollider().y -= 120 * Gdx.graphics.getDeltaTime();
        } else if (character.getCharacterComponent().getCollider().y < 11) {
            jumps = 0;
            character.getCharacterComponent().setJumping(false);
        }

        if(stepping) {
            AnimationSystem.animate(character, batch, elapsed);
        }
    }
}
