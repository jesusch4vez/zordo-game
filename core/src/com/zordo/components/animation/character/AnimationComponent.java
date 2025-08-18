package com.zordo.components.animation.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.zordo.components.Component;

public class AnimationComponent extends Component {

    com.badlogic.gdx.graphics.g2d.Animation<Sprite> runningRightAnimation;
    com.badlogic.gdx.graphics.g2d.Animation<Sprite> runningLeftAnimation;
    com.badlogic.gdx.graphics.g2d.Animation<Sprite> walkingRightAnimation;
    com.badlogic.gdx.graphics.g2d.Animation<Sprite> walkingLeftAnimation;
    com.badlogic.gdx.graphics.g2d.Animation<Sprite> animation;

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

    public AnimationComponent() {
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

        // setting the animations
        runningRightAnimation = new com.badlogic.gdx.graphics.g2d.Animation<>(1f / 6f, runningRightFrames);
        runningLeftAnimation = new com.badlogic.gdx.graphics.g2d.Animation<>(1f / 6f, runningLeftFrames);
        walkingRightAnimation = new com.badlogic.gdx.graphics.g2d.Animation<>(1f / 3f, walkingRightFrames);
        walkingLeftAnimation = new com.badlogic.gdx.graphics.g2d.Animation<>(1f / 3f, walkingLeftFrames);

        animation = walkingRightAnimation;
    }

    public void setWalkingRightAnimation(com.badlogic.gdx.graphics.g2d.Animation<Sprite> walkingRightAnimation) {
        this.walkingRightAnimation = walkingRightAnimation;
    }

    public com.badlogic.gdx.graphics.g2d.Animation<Sprite> getWalkingRightAnimation () {
        return this.walkingRightAnimation;
    }

    public void setWalkingLeftAnimation(com.badlogic.gdx.graphics.g2d.Animation<Sprite> walkingLeftAnimation) {
        this.walkingLeftAnimation = walkingLeftAnimation;
    }

    public com.badlogic.gdx.graphics.g2d.Animation<Sprite> getWalkingLeftAnimation () {
        return this.walkingLeftAnimation;
    }

    public void setRunningRightAnimation(com.badlogic.gdx.graphics.g2d.Animation<Sprite> runningRightAnimation) {
        this.runningRightAnimation = runningRightAnimation;
    };

    public com.badlogic.gdx.graphics.g2d.Animation<Sprite> getRunningRightAnimation() {
        return this.runningRightAnimation;
    };

    public void setRunningLeftAnimation(com.badlogic.gdx.graphics.g2d.Animation<Sprite> runningLeftAnimation) {
        this.runningLeftAnimation = runningLeftAnimation;
    };

    public com.badlogic.gdx.graphics.g2d.Animation<Sprite> getRunningLeftAnimation() {
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
}
