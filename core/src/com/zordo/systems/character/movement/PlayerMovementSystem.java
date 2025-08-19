package com.zordo.systems.character.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.entities.characters.Character;
import com.zordo.systems.character.animation.AnimationSystem;

public class PlayerMovementSystem extends CharacterMovementSystem {
    static float jump2 = 0;
    static float elapsed;

    public static void move(Character character, SpriteBatch batch) {
        elapsed += Gdx.graphics.getDeltaTime();
        character.getCharacterComponent().setPosition(character.getCharacterComponent().getCollider().x, character.getCharacterComponent().getCollider().y);

        if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            character.getCharacterComponent().setIsStepping(false);
            AnimationSystem.standRender(character,batch);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            character.getCharacterComponent().setIsStepping(true);
            AnimationSystem.moveRightRender(character,batch);
        } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            character.getCharacterComponent().setIsStepping(true);
            AnimationSystem.moveLeftRender(character,batch);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && character.getCharacterComponent().getJumps() < 2) {
            character.getCharacterComponent().setIsJumping(true);
            if(character.getCharacterComponent().getJumps() == 0) {
                character.getCharacterComponent().getCollider().y += (100 * Gdx.graphics.getDeltaTime()) + 75;
                if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                    character.getCharacterComponent().getCollider().y += 50;
                }
                jump2 = character.getCharacterComponent().getCollider().y * 1.5f;
            } else {
                character.getCharacterComponent().getCollider().y = jump2;
            }
            character.getCharacterComponent().setJumps(character.getCharacterComponent().getJumps()+1);
        }

        if(character.getCharacterComponent().getIsJumping()) {
            character.getCharacterComponent().setIsStepping(false);
            AnimationSystem.jumpRender(character, batch);
        }

        character.getCharacterComponent().getCollider().y -= 120 * Gdx.graphics.getDeltaTime();

        if(character.getCharacterComponent().getIsStepping()) {
            AnimationSystem.animate(character, batch, elapsed);
        }
    }
}
