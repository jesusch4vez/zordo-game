package com.zordo.components.character;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.zordo.components.animation.character.AnimationComponent;
import com.zordo.components.health.HeartComponent;

import java.util.ArrayList;

public class CharacterComponent {
    Boolean flippedRight;
    Boolean jumping;
    Boolean stepping;

    Rectangle collider;
    Vector3 position;

    public ArrayList<HeartComponent> hearts;
    public int health;
    AnimationComponent animation;

    public CharacterComponent() {
        position = new Vector3();
        collider = new Rectangle();
        collider.x = 10;
        collider.y = 10;
        collider.height = 53;
        collider.width = 23;
        position.x = collider.x;
        position.y = collider.y;

        // orientation and jumping status
        flippedRight = true;
        jumping = false;
        stepping = false;

        health = 8;

        hearts = new ArrayList<>(health);
        for(int i = 0; i < health; i++) {
            hearts.add(new HeartComponent());
        }
        animation = new AnimationComponent();
    }

    public int getHealth() {
        return this.health;
    }


    public void setCollider(Rectangle collider) {
        this.collider = collider;
    }

    public void setCollider(float x, float y) {
        this.collider.x = x;
        this.collider.y = y;
    }

    public Rectangle getCollider() {
        return this.collider;
    }

    public Vector3 getPosition() {
        return this.position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
    }

    public float getX() {
        return this.position.x;
    }

    public float getY() {
        return this.position.y;
    }

    public void setFlippedRight(Boolean flip) {
        this.flippedRight = flip;
    };

    public Boolean getFlippedRight() {
        return this.flippedRight;
    };

    public void setJumping(Boolean jumping) {
        this.jumping = jumping;
    };

    public Boolean getJumping() {
        return this.jumping;
    };

    public ArrayList<HeartComponent> getHearts() {
        return this.hearts;
    }

    public void setAnimation(AnimationComponent animation) {
        this.animation = animation;
    }

    public AnimationComponent getAnimation() {
        return this.animation;
    }
}
