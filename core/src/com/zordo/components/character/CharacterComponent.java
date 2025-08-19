package com.zordo.components.character;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.zordo.components.animation.character.AnimationComponent;
import com.zordo.components.health.HeartComponent;

import java.util.ArrayList;

public class CharacterComponent {
    Boolean isFlippedRight;
    Boolean isJumping;
    Boolean isStepping;
    Boolean isRunning;
    Boolean isAirborne;
    Boolean isStanding;

    Boolean isColliding;

    int jumps;

    Rectangle collider;
    Vector3 position;

    public ArrayList<HeartComponent> hearts;
    public int health;
    AnimationComponent animation;

    public CharacterComponent() {
        jumps = 0;
        position = new Vector3();
        collider = new Rectangle();
        collider.x = 10;
        collider.y = 10;
        collider.height = 53;
        collider.width = 23;
        position.x = collider.x;
        position.y = collider.y;

        // orientation and isJumping status
        isFlippedRight = true;
        isJumping = false;
        isStepping = false;
        isRunning = false;
        isAirborne = false;
        isColliding = false;
        isStanding = true;

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

    public void setX(float x) {
        this.position.x = x;
        this.collider.x = x;
    }

    public void setY(float y) {
        this.position.y = y;
        this.collider.y = y;
    }

    public void setIsFlippedRight(Boolean flip) {
        this.isFlippedRight = flip;
    };

    public Boolean getIsFlippedRight() {
        return this.isFlippedRight;
    };

    public void setIsJumping(Boolean isJumping) {
        this.isJumping = isJumping;
    };

    public Boolean getIsJumping() {
        return this.isJumping;
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

    public Boolean getIsStepping() {
        return isStepping;
    }

    public void setIsStepping(Boolean stepping) {
        this.isStepping = stepping;
    }

    public void setJumps(int jumps) {
        this.jumps = jumps;
    }

    public int getJumps() {
        return this.jumps;
    }

    public void setIsRunning(Boolean isRunning) {
        this.isRunning = isRunning;
    }

    public Boolean getIsRunning() {
        return this.isRunning;
    }

    public void setIsAirborne(Boolean isAirborne) {
        this.isAirborne = isAirborne;
    }

    public Boolean getIsAirborne() {
        return this.isAirborne;
    }

    public void setIsColliding(Boolean isColliding) {
        this.isColliding = isColliding;
    }

    public Boolean getIsColliding() {
        return this.isColliding;
    }

    public void setIsStanding(Boolean isStanding) {
        this.isStanding = isStanding;
    }

    public Boolean getIsStanding() {
        return this.isStanding;
    }
}
