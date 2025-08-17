package com.zordo.systems.character.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.zordo.entities.characters.player.Player;
import com.zordo.systems.health.HealthSystem;

import static com.zordo.systems.character.animation.AnimationSystem.jumpRender;

public class PlayerMovementSystem {
    static int jumps = 0;
    static float jump2 = 0;
    static Boolean stepping = false;
    static float elapsed;

    static Animation<Sprite> animation;

    public static void move(Player player, SpriteBatch batch) {
        elapsed += Gdx.graphics.getDeltaTime();
        player.setPosition(player.getCollider().getX(), player.getCollider().getY());

        HealthSystem.healthRender(player.getHearts(), batch);
        if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            stepping = false;
            if(player.getFlippedRight() && !player.getJumping()) {
                player.setStandRight(player.getStandRight());
                batch.draw(player.getStandRight(), player.getCollider().x, player.getCollider().y);
            } else if (!player.getJumping()) {
                batch.draw(player.getStandLeft(), player.getCollider().x, player.getCollider().y);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if(!player.getFlippedRight()) {
                player.setFlippedRight(true);
            }
            if(!player.getJumping()){
                animation = player.getWalkingRightAnimation();
            }
            if(!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                player.getCollider().x += 100 * Gdx.graphics.getDeltaTime();
            } else {
                animation = player.getRunningRightAnimation();
                player.getCollider().x += 150 * Gdx.graphics.getDeltaTime();
            }
        } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(player.getFlippedRight()) {
                player.setFlippedRight(false);
            }
            if(!player.getJumping()){
                animation = player.getWalkingLeftAnimation();
            }
            if(!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                player.getCollider().x -= 100 * Gdx.graphics.getDeltaTime();
            } else {
                animation = player.getRunningLeftAnimation();
                player.getCollider().x -= 150 * Gdx.graphics.getDeltaTime();
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && jumps < 2) {
            player.setJumping(true);
            if(jumps == 0) {
                player.getCollider().y += (100 * Gdx.graphics.getDeltaTime()) + 75;
                if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                    player.getCollider().y += 50;
                }
                jump2 = player.getCollider().y * 1.5f;
            } else {
                player.getCollider().y = jump2;
            }
            jumps++;
        }

        if(player.getJumping()) {
            stepping = false;
            jumpRender(player, batch);
        }

        if(player.getCollider().y > 11) {
            player.getCollider().y -= 120 * Gdx.graphics.getDeltaTime();
        } else if (player.getCollider().y < 11) {
            jumps = 0;
            player.setJumping(false);
        }

        if(stepping) {
            batch.draw(animation.getKeyFrame(elapsed,true), player.getCollider().x,player.getCollider().y);
        }
    }
}
