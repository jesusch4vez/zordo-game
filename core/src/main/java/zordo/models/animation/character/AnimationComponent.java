package zordo.models.animation.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import zordo.models.Component;

public class AnimationComponent extends Component {

    Animation<Sprite> runningRightAnimation;
    Animation<Sprite> runningLeftAnimation;
    Animation<Sprite> walkingRightAnimation;
    Animation<Sprite> walkingLeftAnimation;
    Animation<Sprite> standingRightAnimation;
    Animation<Sprite> standingLeftAnimation;
    Animation<Sprite> jumpingRightAnimation;
    Animation<Sprite> jumpingLeftAnimation;
    Animation<Sprite> duckingRightAnimation;
    Animation<Sprite> duckingLeftAnimation;

    Animation<Sprite> animation;

    Sprite[] runningRightFrames;
    Sprite[] runningLeftFrames;
    Sprite[] walkingRightFrames;
    Sprite[] walkingLeftFrames;
    Sprite[] standingRightFrames;
    Sprite[] standingLeftFrames;
    Sprite[] jumpingRightFrames;
    Sprite[] jumpingLeftFrames;

    Sprite [] duckingRightFrames;
    Sprite[] duckingLeftFrames;


    // accessing sprite assets from the directory files
    String standuri = "character/player/link-standing-sprites/";
    String runuri = "character/player/link-running-sprites/";
    String jumpuri = "character/player/link-jumping-sprites/";

    String duckinguri = "character/player/link-ducking-sprites/";

    public AnimationComponent() {
        // animation objects
        runningRightFrames = new Sprite[6];
        runningLeftFrames = new Sprite[6];
        walkingRightFrames = new Sprite[4];
        walkingLeftFrames = new Sprite[4];

        standingRightFrames = new Sprite[1];
        standingLeftFrames = new Sprite[1];
        jumpingRightFrames = new Sprite[1];
        jumpingLeftFrames = new Sprite[1];

        duckingRightFrames = new Sprite[1];
        duckingLeftFrames = new Sprite[1];

        // standing and jumping sprite creation
        standingRightFrames[0] = new Sprite(new Texture(standuri + "link-standing-0.png"));
        standingLeftFrames[0] = new Sprite(standingRightFrames[0]);
        standingRightFrames[0].setScale(0.05f);
        standingLeftFrames[0].setScale(0.05f);
        jumpingRightFrames[0] = new Sprite(new Texture(jumpuri + "link-jumping-0.png"));
        jumpingLeftFrames[0] = new Sprite(jumpingRightFrames[0]);
        jumpingRightFrames[0].setScale(0.05f);
        jumpingLeftFrames[0].setScale(0.05f);
        duckingRightFrames[0] = new Sprite(new Texture(duckinguri + "link-ducking.png"));
        duckingLeftFrames[0] = new Sprite(duckingRightFrames[0]);
        standingLeftFrames[0].flip(true, false);
        jumpingLeftFrames[0].flip(true, false);
        duckingLeftFrames[0].flip(true, false);


        // running animation construction
        int j = 0;
        for(int i = 0; i < 6; i++) {
            runningRightFrames[i] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
            runningLeftFrames[i] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
            runningRightFrames[i].setScale(0.05f);
            runningLeftFrames[i].setScale(0.05f);
            if( i != 2 && i != 5) {
                walkingRightFrames[j] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
                walkingLeftFrames[j] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
                walkingLeftFrames[j].flip(true, false);
                j++;
            }
            runningLeftFrames[i].flip(true, false);
        }

        // setting the animations
        runningRightAnimation = new Animation<>(1f / 6f, runningRightFrames);
        runningLeftAnimation = new Animation<>(1f / 6f, runningLeftFrames);
        walkingRightAnimation = new Animation<>(1f / 3f, walkingRightFrames);
        walkingLeftAnimation = new Animation<>(1f / 3f, walkingLeftFrames);

        standingRightAnimation = new Animation<>(1f / 6f, standingRightFrames);
        standingLeftAnimation = new Animation<>(1f / 6f, standingLeftFrames);
        jumpingRightAnimation = new Animation<>(1f / 6f, jumpingRightFrames);
        jumpingLeftAnimation = new Animation<>(1f / 6f, jumpingLeftFrames);

        duckingRightAnimation = new Animation<>(1f / 3f, duckingRightFrames);
        duckingLeftAnimation = new Animation<>(1f / 3f, duckingLeftFrames);

        animation = walkingRightAnimation;
    }

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

    public void setJumpingRightAnimation(Animation<Sprite> jumpingRightAnimation) {
        this.jumpingRightAnimation = jumpingRightAnimation;
    }

    public Animation<Sprite> getJumpingRightAnimation() {
        return this.jumpingRightAnimation;
    }

    public void setJumpingLeftAnimation(Animation<Sprite> jumpingLeftAnimation) {
        this.jumpingLeftAnimation = jumpingLeftAnimation;
    }

    public Animation<Sprite> getJumpingLeftAnimation() {
        return this.jumpingLeftAnimation;
    }

    public void setStandingRightAnimation(Animation<Sprite> standingRightAnimation) {
        this.standingRightAnimation = standingRightAnimation;
    }

    public Animation<Sprite> getStandingRightAnimation() {
        return this.standingRightAnimation;
    }

    public void setStandingLeftAnimation(Animation<Sprite> standingLeftAnimation) {
        this.standingLeftAnimation = standingLeftAnimation;
    }

    public Animation<Sprite> getStandingLeftAnimation() {
        return this.standingLeftAnimation;
    }

    public Animation<Sprite> getDuckingRightAnimation() {
        return this.duckingRightAnimation;
    }

    public void setDuckingRightAnimation(Animation<Sprite> duckingRightAnimation) {
        this.duckingRightAnimation = duckingRightAnimation;
    }

    public Animation<Sprite> getDuckingLeftAnimation() {
        return this.duckingLeftAnimation;
    }
    public void setDuckingLeftAnimation(Animation<Sprite> duckingLeftAnimation) {
        this.duckingLeftAnimation = duckingLeftAnimation;
    }
}
