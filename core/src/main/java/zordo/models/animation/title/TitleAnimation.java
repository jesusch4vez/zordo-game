package zordo.models.animation.title;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TitleAnimation {
    Animation<Sprite> titleAnimation;

    Sprite[] titleFrames;

    String titleUri = "environment/frames/";
    public TitleAnimation() {
        titleFrames = new Sprite[3];

        for(int i = 0; i < titleFrames.length; i++) {
            Texture frameTexture = new Texture(titleUri + "lozTitle-frame" + i + ".png");
            Sprite frame = new Sprite(frameTexture);
//            frame.setRegionHeight(Gdx.graphics.getHeight());
//            frame.setRegionWidth(Gdx.graphics.getWidth());
            titleFrames[i] = frame;
        }

        titleAnimation = new Animation<>(1f/6f, titleFrames);
    }

    public Animation<Sprite> getTitleAnimation() {
        return titleAnimation;
    }

    public void setTitleAnimation(Animation<Sprite> titleAnimation) {
        this.titleAnimation = titleAnimation;
    }
}
