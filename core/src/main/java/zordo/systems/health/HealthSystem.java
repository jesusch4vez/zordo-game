package zordo.systems.health;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import zordo.components.health.HeartComponent;
import zordo.entities.characters.Character;

import java.util.ArrayList;

public class HealthSystem {

    public static void healthRender(ArrayList<HeartComponent> hearts, SpriteBatch batch) {
        int HEART_WIDTH = 40;
        int HEART_HEIGHT = 40;
        int HEART_MARGIN = 5;
        int SCREEN_MARGIN = 10;

        for( int i = 0; i < hearts.size(); i++) {
            batch.draw(hearts.get(i).getHeartState(), SCREEN_MARGIN + i*(HEART_WIDTH+HEART_MARGIN), Gdx.graphics.getHeight()-HEART_HEIGHT-SCREEN_MARGIN, HEART_WIDTH, HEART_HEIGHT);
        }
    }

    public static void healthRender(ArrayList<HeartComponent> hearts, SpriteBatch batch, int x, int y) {
        if(!hearts.isEmpty()) {
            for( int i = 0; i < hearts.size(); i++) {
                batch.draw(hearts.get(i).getHeartState(),x + i * 10,y);
            }
        }
    }

    public static void damageHeart(HeartComponent heart) {
        heart.setHeartHealth(heart.getHeartHealth()-1);
        if(heart.getHeartHealth() >= 0) {
            heart.setHeartState(heart.getHeartSegments()[heart.getHeartHealth()]);
        }
    }

    public static void damage(ArrayList<HeartComponent> hearts, Character character) {
        damageHeart(topHeart(hearts));
        if(topHeart(hearts).getHeartHealth() <= 0) {
            character.getCharacterComponent().health--;
        }
    }

    private static HeartComponent topHeart(ArrayList<HeartComponent> hearts) {
        return hearts.get(topHeartIndex(hearts));
    }

    private static int topHeartIndex(ArrayList<HeartComponent> hearts) {
        if(!hearts.isEmpty()) {
            return hearts.size() - 1;
        } else {
            return 0;
        }
    }
}
