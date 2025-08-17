package com.zordo.systems.character.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.entities.characters.player.Player;

public class AnimationSystem {

    public static void jumpRender(Player player, SpriteBatch batch) {
        if(player.getFlippedRight()) {
            batch.draw(player.getJumpRight(), player.getCollider().x, player.getCollider().y);
        } else {
            batch.draw(player.getJumpLeft(), player.getCollider().x, player.getCollider().y);
        }
    }
}
