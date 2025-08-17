package com.zordo.entities.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.zordo.components.health.Heart;

import java.util.ArrayList;

public class Character {
    Boolean flippedRight;
    Boolean jumping;
    Boolean stepping;

    Rectangle collider;
    Vector3 position;

    public ArrayList<Heart> hearts;
    public int health;

    Animation<Sprite> runningRightAnimation;
    Animation<Sprite> runningLeftAnimation;
    Animation<Sprite> walkingRightAnimation;
    Animation<Sprite> walkingLeftAnimation;
    Animation<Sprite> animation;

    Sprite[] runningRightFrames;
    Sprite[] runningLeftFrames;
    Sprite[] walkingRightFrames;
    Sprite[] walkingLeftFrames;
    Sprite standRight;
    Sprite standLeft;
    Sprite jumpRight;
    Sprite jumpLeft;


    // accessing sprite assets from the directory files
    String standuri = "character/player/link-standing-sprites/";
    String runuri = "character/player/link-running-sprites/";
    String jumpuri = "character/player/link-jumping-sprites/";


    public Character() {
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

        // animation objects
        runningRightFrames = new Sprite[6];
        runningLeftFrames = new Sprite[6];
        walkingRightFrames = new Sprite[4];
        walkingLeftFrames = new Sprite[4];

        // standing and jumping sprite creation
        standRight = new Sprite(new Texture(standuri + "link-standing-0.png"));
        standLeft = new Sprite(standRight);
        jumpRight = new Sprite(new Texture(jumpuri + "link-jumping-0.png"));
        jumpLeft = new Sprite(jumpRight);
        standLeft.flip(true, false);
        jumpLeft.flip(true, false);

        hearts = new ArrayList<>(8);

        // running animation construction
        int j = 0;
        for(int i = 0; i < 6; i++) {
            runningRightFrames[i] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
            runningLeftFrames[i] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
            if( i != 2 && i != 5) {
                walkingRightFrames[j] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
                walkingLeftFrames[j] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
                walkingLeftFrames[j].flip(true, false);
                j++;
            }
            runningLeftFrames[i].flip(true, false);
        }

        for(int i = 0; i < health; i++) {
            hearts.add(new Heart());
        }

        // setting the animations
        runningRightAnimation = new Animation<>(1f / 6f, runningRightFrames);
        runningLeftAnimation = new Animation<>(1f / 6f, runningLeftFrames);
        walkingRightAnimation = new Animation<>(1f / 3f, walkingRightFrames);
        walkingLeftAnimation = new Animation<>(1f / 3f, walkingLeftFrames);

        animation = walkingRightAnimation;
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

    public void damage() {
        topHeart(health-1).damageHeart();
        if(topHeart(health-1).getHeartHealth() <= 0) {
            health--;
        }
    }

    private Heart topHeart(int health) {
        if(health > -1) {
            return this.hearts.get(health);
        } else {
            return this.hearts.get(0);
        }
    }

    private Heart topHeart() {
        // TODO Auto-generated method stub
        return this.hearts.get(topHeartIndex());

    }

    private int topHeartIndex() {
        // TODO Auto-generated method stub
        if(!this.hearts.isEmpty()) {
            return this.hearts.size() - 1;
        } else {
            return 0;
        }
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

    public void setWalkingRightAnimation(Animation<Sprite> walkingRightAnimation) {
        this.walkingRightAnimation = walkingRightAnimation;
    }

    public Animation<Sprite> getWalkingRightAnimation () {
        return this.walkingRightAnimation;
    }

    public void setWalkingLeftAnimation(Animation<Sprite> walkingLeftAnimation) {
        this.walkingLeftAnimation = walkingLeftAnimation;
    }

    public Animation<Sprite> getWalkingLeftAnimation () {
        return this.walkingLeftAnimation;
    }

    public void setRunningRightAnimation(Animation<Sprite> runningRightAnimation) {
        this.runningRightAnimation = runningRightAnimation;
    };

    public Animation<Sprite> getRunningRightAnimation() {
        return this.runningRightAnimation;
    };

    public void setRunningLeftAnimation(Animation<Sprite> runningLeftAnimation) {
        this.runningLeftAnimation = runningLeftAnimation;
    };

    public Animation<Sprite> getRunningLeftAnimation() {
        return this.runningLeftAnimation;
    };

    public void setRunningRightFrames(Sprite[] runningRightFrames) {
        this.runningRightFrames = runningRightFrames;
    };

    public Sprite[] getRunningRightFrames() {
        return this.runningRightFrames;
    };

    public void setRunningLeftFrames(Sprite[] runningLeftFrames) {
        this.runningLeftFrames = runningLeftFrames;
    };

    public Sprite[] getRunningLeftFrames() {
        return this.runningLeftFrames;
    };

    public void setStandRight(Sprite standRight) {
        this.standRight = standRight;
    };

    public Sprite getStandRight() {
        return this.standRight;
    };

    public void setStandLeft(Sprite standLeft) {
        this.standLeft = standLeft;
    };

    public Sprite getStandLeft() {
        return this.standLeft;
    };

    public void setJumpRight(Sprite jumpRight) {
        this.jumpRight = jumpRight;
    };

    public Sprite getJumpRight() {
        return this.jumpRight;
    };

    public void setJumpLeft(Sprite jumpLeft) {
        this.jumpLeft = jumpLeft;
    };

    public Sprite getJumpLeft() {
        return this.jumpLeft;
    }

    public ArrayList<Heart> getHearts() {
        return this.hearts;
    }
}
