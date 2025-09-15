package zordo.systems.animation.character;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import zordo.entities.characters.player.Player;
import zordo.entities.world.level.Level;
import zordo.models.physics.BodyLoader;

public class AnimationSystem {
    static Animation<Sprite> animation;
    static Sprite nextFrame;

    public static void standRender(Player player) {
        BodyLoader.destroyFixture(player.characterBody);

        if(player.getCharacterComponent().getIsFlippedRight() && !player.getCharacterComponent().getIsJumping()) {
            BodyLoader.attachFixture(player.characterBody, "standing", player.characterFixtureDef, 1f);
            animation = player.getCharacterComponent().getAnimation().getStandingRightAnimation();
        } else if (!player.getCharacterComponent().getIsJumping()) {
            BodyLoader.attachFixture(player.characterBody, "standing", player.characterFixtureDef, 1f);
            animation = player.getCharacterComponent().getAnimation().getStandingLeftAnimation();
        }
    }

    public static void walkRender(Player player) {
        BodyLoader.destroyFixture(player.characterBody);

        if (player.getCharacterComponent().getIsFlippedRight()) {
            BodyLoader.attachFixture(player.characterBody, "running-0", player.characterFixtureDef, 1.5f);
            animation = player.getCharacterComponent().getAnimation().getWalkingRightAnimation();
        } else {
            BodyLoader.attachFixture(player.characterBody, "running-1", player.characterFixtureDef,1.5f);
            animation = player.getCharacterComponent().getAnimation().getWalkingLeftAnimation();
        }
    }

    public static void runRender(Player player) {
        BodyLoader.destroyFixture(player.characterBody);

        if (player.getCharacterComponent().getIsFlippedRight()) {
            BodyLoader.attachFixture(player.characterBody, "running-0", player.characterFixtureDef,1.5f);
            animation = player.getCharacterComponent().getAnimation().getRunningRightAnimation();
        } else {
            BodyLoader.attachFixture(player.characterBody, "running-0", player.characterFixtureDef,1.5f);
            animation = player.getCharacterComponent().getAnimation().getRunningLeftAnimation();
        }
    }


    public static void jumpRender(Player player) {
        BodyLoader.destroyFixture(player.characterBody);

        if (player.getCharacterComponent().getIsFlippedRight()) {
            BodyLoader.attachFixture(player.characterBody, "jumping-0", player.characterFixtureDef,1.5f);
            animation = player.getCharacterComponent().getAnimation().getJumpingRightAnimation();
        } else {
            BodyLoader.attachFixture(player.characterBody, "jumping-0", player.characterFixtureDef,1.5f);
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
        batch.draw(nextFrame, player.getCharacterComponent().getPosition().x, player.getCharacterComponent().getPosition().y, nextFrame.getWidth()/22, nextFrame.getHeight()/22);
    }
}
