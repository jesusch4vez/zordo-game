package com.zordo.systems.character.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.entities.characters.Character;

public class AnimationSystem {
    static Animation<Sprite> animation;

    public static void standRender(Character character, SpriteBatch batch) {
        if(character.getCharacterComponent().getIsFlippedRight() && !character.getCharacterComponent().getIsJumping()) {
            character.getCharacterComponent().getAnimation().setStandRight(character.getCharacterComponent().getAnimation().getStandRight());
            batch.draw(character.getCharacterComponent().getAnimation().getStandRight(), character.getCharacterComponent().getCollider().x, character.getCharacterComponent().getCollider().y);
        } else if (!character.getCharacterComponent().getIsJumping()) {
            batch.draw(character.getCharacterComponent().getAnimation().getStandLeft(), character.getCharacterComponent().getCollider().x, character.getCharacterComponent().getCollider().y);
        }
    }

    public static void jumpRender(Character character, SpriteBatch batch) {
        if (character.getCharacterComponent().getIsFlippedRight()) {
            batch.draw(character.getCharacterComponent().getAnimation().getJumpRight(), character.getCharacterComponent().getCollider().x, character.getCharacterComponent().getCollider().y);
        } else {
            batch.draw(character.getCharacterComponent().getAnimation().getJumpLeft(), character.getCharacterComponent().getCollider().x, character.getCharacterComponent().getCollider().y);
        }
    }

    public static void moveLeftRender(Character character, SpriteBatch batch) {
        if(character.getCharacterComponent().getIsFlippedRight()) {
            character.getCharacterComponent().setIsFlippedRight(false);
        }
        if(!character.getCharacterComponent().getIsJumping()){
            animation = character.getCharacterComponent().getAnimation().getWalkingLeftAnimation();
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            character.getCharacterComponent().getCollider().x -= 100 * Gdx.graphics.getDeltaTime();
        } else {
            animation = character.getCharacterComponent().getAnimation().getRunningLeftAnimation();
            character.getCharacterComponent().getCollider().x -= 150 * Gdx.graphics.getDeltaTime();
        }
    }

    public static void moveRightRender(Character character, SpriteBatch batch) {
        if(!character.getCharacterComponent().getIsFlippedRight()) {
            character.getCharacterComponent().setIsFlippedRight(true);
        }
        if(!character.getCharacterComponent().getIsJumping()){
            animation = character.getCharacterComponent().getAnimation().getWalkingRightAnimation();
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            character.getCharacterComponent().getCollider().x += 100 * Gdx.graphics.getDeltaTime();
        } else {
            animation = character.getCharacterComponent().getAnimation().getRunningRightAnimation();
            character.getCharacterComponent().getCollider().x += 150 * Gdx.graphics.getDeltaTime();
        }
    }

    public static void animate(Character character, SpriteBatch batch, float elapsed ) {
        batch.draw(animation.getKeyFrame(elapsed,true), character.getCharacterComponent().getCollider().x,character.getCharacterComponent().getCollider().y);
    }
}
