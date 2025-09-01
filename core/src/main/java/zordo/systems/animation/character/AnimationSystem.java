package zordo.systems.animation.character;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import zordo.entities.characters.Character;
import zordo.entities.world.level.Level;

public class AnimationSystem {
    static Animation<Sprite> animation;
    static Sprite nextFrame;

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

    public static void duckRender(Character character) {
        if (character.getCharacterComponent().getIsFlippedRight()) {
            animation = character.getCharacterComponent().getAnimation().getDuckingRightAnimation();
        } else  {
            animation = character.getCharacterComponent().getAnimation().getDuckingLeftAnimation();
        }
    }

    public static void animate(Character character, SpriteBatch batch, float elapsed, Level level) {
        if(!level.paused) {
            nextFrame = animation.getKeyFrame(elapsed, true);
        }
        batch.draw(nextFrame, character.getCharacterComponent().getPosition().x, character.getCharacterComponent().getPosition().y);
    }
}
