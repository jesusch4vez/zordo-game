package com.zordo.systems.character.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.entities.characters.Character;

public class AnimationSystem {

    public static void jumpRender(Character character, SpriteBatch batch) {
        if(character.getFlippedRight()) {
            batch.draw(character.getJumpRight(), character.getCollider().x, character.getCollider().y);
        } else {
            batch.draw(character.getJumpLeft(), character.getCollider().x, character.getCollider().y);
        }
    }
}
