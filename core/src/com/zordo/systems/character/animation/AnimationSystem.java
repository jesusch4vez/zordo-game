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
        if(character.getFlippedRight() && !character.getJumping()) {
            character.getAnimation().setStandRight(character.getAnimation().getStandRight());
            batch.draw(character.getAnimation().getStandRight(), character.getCollider().x, character.getCollider().y);
        } else if (!character.getJumping()) {
            batch.draw(character.getAnimation().getStandLeft(), character.getCollider().x, character.getCollider().y);
        }
    }

    public static void jumpRender(Character character, SpriteBatch batch) {
        if (character.getFlippedRight()) {
            batch.draw(character.getAnimation().getJumpRight(), character.getCollider().x, character.getCollider().y);
        } else {
            batch.draw(character.getAnimation().getJumpLeft(), character.getCollider().x, character.getCollider().y);
        }
    }

    public static void walkLeftRender(Character character, SpriteBatch batch) {
        if(character.getFlippedRight()) {
            character.setFlippedRight(false);
        }
        if(!character.getJumping()){
            animation = character.getAnimation().getWalkingLeftAnimation();
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            character.getCollider().x -= 100 * Gdx.graphics.getDeltaTime();
        } else {
            animation = character.getAnimation().getRunningLeftAnimation();
            character.getCollider().x -= 150 * Gdx.graphics.getDeltaTime();
        }
    }

    public static void runLeftRender(Character character, SpriteBatch batch) {}

    public static void walkRightRender(Character character, SpriteBatch batch) {
        if(!character.getFlippedRight()) {
            character.setFlippedRight(true);
        }
        if(!character.getJumping()){
            animation = character.getAnimation().getWalkingRightAnimation();
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            character.getCollider().x += 100 * Gdx.graphics.getDeltaTime();
        } else {
            animation = character.getAnimation().getRunningRightAnimation();
            character.getCollider().x += 150 * Gdx.graphics.getDeltaTime();
        }
    }

    public static void runRightRender(Character character, SpriteBatch batch) {}

    public static void animate(Character character, SpriteBatch batch, float elapsed ) {
        batch.draw(animation.getKeyFrame(elapsed,true), character.getCollider().x,character.getCollider().y);
    }
}
