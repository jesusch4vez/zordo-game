package com.zordo.systems.character.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.entities.characters.Character;

public class AnimationSystem {
    static Animation<Sprite> animation;

    public static void standRender(Character character) {
        if(character.getCharacterComponent().getIsFlippedRight() && !character.getCharacterComponent().getIsJumping()) {
            animation = character.getCharacterComponent().getAnimation().getStandingRightAnimation();
        } else if (!character.getCharacterComponent().getIsJumping()) {
            animation = character.getCharacterComponent().getAnimation().getStandingLeftAnimation();
        }
    }

    public static void walkRender(Character character) {
        if (character.getCharacterComponent().getIsFlippedRight()) {
            animation = character.getCharacterComponent().getAnimation().getWalkingRightAnimation();
        } else {
            animation = character.getCharacterComponent().getAnimation().getWalkingLeftAnimation();
        }
    }

    public static void runRender(Character character) {
        if (character.getCharacterComponent().getIsFlippedRight()) {
            animation = character.getCharacterComponent().getAnimation().getRunningRightAnimation();
        } else {
            animation = character.getCharacterComponent().getAnimation().getRunningLeftAnimation();
        }
    }


    public static void jumpRender(Character character) {
        if (character.getCharacterComponent().getIsFlippedRight()) {
            animation = character.getCharacterComponent().getAnimation().getJumpingRightAnimation();
        } else {
            animation = character.getCharacterComponent().getAnimation().getJumpingLeftAnimation();
        }
    }

    public static void animate(Character character, SpriteBatch batch, float elapsed ) {
        batch.draw(animation.getKeyFrame(elapsed,true), character.getCharacterComponent().getCollider().x,character.getCharacterComponent().getCollider().y);
    }
}
