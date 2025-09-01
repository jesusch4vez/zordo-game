package zordo.systems.animation.title;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import zordo.components.animation.title.TitleAnimation;

public class TitleAnimationSystem {
    static Animation<Sprite> animation;
    static Sprite nextFrame;

    public static void renderTitle(TitleAnimation titleAnimation) {
        animation = titleAnimation.getTitleAnimation();
    }

    public static void animate(SpriteBatch batch, float elapsed) {
        nextFrame = animation.getKeyFrame(elapsed, true);
        batch.draw(nextFrame, 0,0);
    }
}
