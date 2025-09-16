package zordo.game.systems.animation.character;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import zordo.game.entities.characters.player.Player;
import zordo.game.entities.world.level.Level;
import zordo.game.models.physics.BodyLoader;

public class AnimationSystem {
    static Animation<Sprite> animation;
    static Sprite nextFrame;

    public static void standRender(Player player) {
        if(player.getCharacterComponent().getIsFlippedRight() && !player.getCharacterComponent().getIsJumping()) {
            if(!player.getCharacterComponent().getIsStanding()) {
                BodyLoader.destroyFixture(player.characterBody);
                BodyLoader.attachFixture(player.characterBody, "standing", player.characterFixtureDef, 1.8f);
            }
            animation = player.getCharacterComponent().getAnimation().getStandingRightAnimation();
        } else if (!player.getCharacterComponent().getIsJumping()) {
            if(!player.getCharacterComponent().getIsStanding() && !player.getCharacterComponent().getIsFlippedRight()) {
                BodyLoader.destroyFixture(player.characterBody);
                BodyLoader.attachFixture(player.characterBody, "standing-left", player.characterFixtureDef, 1.8f);
            }
            animation = player.getCharacterComponent().getAnimation().getStandingLeftAnimation();
        }
    }

    public static void walkRender(Player player) {
        BodyLoader.destroyFixture(player.characterBody);

        if (player.getCharacterComponent().getIsFlippedRight()) {
            BodyLoader.attachFixture(player.characterBody, "running-0", player.characterFixtureDef, 1.8f*1.5f);
            animation = player.getCharacterComponent().getAnimation().getWalkingRightAnimation();
        } else {
            BodyLoader.attachFixture(player.characterBody, "running-0-left", player.characterFixtureDef,1.8f*1.5f);
            animation = player.getCharacterComponent().getAnimation().getWalkingLeftAnimation();
        }
    }

    public static void runRender(Player player) {
        BodyLoader.destroyFixture(player.characterBody);
        if (player.getCharacterComponent().getIsFlippedRight()) {
            BodyLoader.attachFixture(player.characterBody, "running-1", player.characterFixtureDef,1.8f*1.5f);
            animation = player.getCharacterComponent().getAnimation().getRunningRightAnimation();
        } else {
            BodyLoader.attachFixture(player.characterBody, "running-1-left", player.characterFixtureDef,1.8f*1.5f);
            animation = player.getCharacterComponent().getAnimation().getRunningLeftAnimation();
        }
    }


    public static void jumpRender(Player player) {
        BodyLoader.destroyFixture(player.characterBody);

        if (player.getCharacterComponent().getIsFlippedRight()) {
            BodyLoader.attachFixture(player.characterBody, "jumping-0", player.characterFixtureDef,1.8f*1.5f);
            animation = player.getCharacterComponent().getAnimation().getJumpingRightAnimation();
        } else {
            BodyLoader.attachFixture(player.characterBody, "jumping-0-left", player.characterFixtureDef,1.8f*1.5f);
            animation = player.getCharacterComponent().getAnimation().getJumpingLeftAnimation();
        }
    }

    public static void duckRender(Player player) {
        BodyLoader.destroyFixture(player.characterBody);
        if (player.getCharacterComponent().getIsFlippedRight()) {
            animation = player.getCharacterComponent().getAnimation().getDuckingRightAnimation();
        } else  {
            animation = player.getCharacterComponent().getAnimation().getDuckingLeftAnimation();
        }
    }

    public static void animate(Player player, SpriteBatch batch, float elapsed, Level level) {
        if(!level.paused) {
            nextFrame = animation.getKeyFrame(elapsed, true);
        }
        batch.draw(nextFrame, player.getCharacterComponent().getPosition().x, player.getCharacterComponent().getPosition().y, nextFrame.getWidth()/12, nextFrame.getHeight()/12);
    }
}
