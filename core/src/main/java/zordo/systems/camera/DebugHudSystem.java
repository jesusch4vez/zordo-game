package zordo.systems.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import zordo.models.gamePad.ControllerComponent;
import zordo.entities.characters.Character;
import zordo.entities.world.level.Level;

public class DebugHudSystem {
    public static void renderDebugHud(Character player, Level level, OrthographicCamera camera) {
        SpriteBatch hudBatch = new SpriteBatch();
        hudBatch.begin();
        BitmapFont font = new BitmapFont();

        Vector2 currentVelocity = player.getCharacterComponent().getCharacterBody().getLinearVelocity();

        int X_MARGIN = 500;
        font.getData().setScale(2);

        font.draw(hudBatch, "AIRBORNE      " + player.getCharacterComponent().getIsAirborne().toString(), Gdx.graphics.getWidth() - X_MARGIN, Gdx.graphics.getHeight() - 50);
        font.draw(hudBatch, "JUMPING     " + player.getCharacterComponent().getIsJumping().toString(), Gdx.graphics.getWidth() -X_MARGIN, Gdx.graphics.getHeight() - 150);
        font.draw(hudBatch, "RUNNING     " + player.getCharacterComponent().getIsRunning().toString(), Gdx.graphics.getWidth()-X_MARGIN, Gdx.graphics.getHeight() - 200);
        font.draw(hudBatch, "WALKING     " + player.getCharacterComponent().getIsStepping().toString(), Gdx.graphics.getWidth()-X_MARGIN, Gdx.graphics.getHeight() - 250);

        font.draw(hudBatch, "LEVEL ", Gdx.graphics.getWidth()-X_MARGIN, Gdx.graphics.getHeight() - 450);
        font.draw(hudBatch, "SIZE " + level.getLevelSize().getWidth() + "x" + level.getLevelSize().getHeight(), Gdx.graphics.getWidth()-X_MARGIN, Gdx.graphics.getHeight() - 500);

        font.draw(hudBatch, "COORDINATES", Gdx.graphics.getWidth()-X_MARGIN, Gdx.graphics.getHeight() - 650);
        font.draw(hudBatch, player.getCharacterComponent().getPosition().x + " , "+ player.getCharacterComponent().getPosition().y, Gdx.graphics.getWidth()-X_MARGIN, Gdx.graphics.getHeight() - 700);

        font.draw(hudBatch, "VELOCITY: " + currentVelocity, Gdx.graphics.getWidth()-X_MARGIN, Gdx.graphics.getHeight() - 750);
        if(!ControllerComponent.getPressedButtons().isEmpty()) {
            float x = (Gdx.graphics.getWidth() / 2.0f) - X_MARGIN;
            int listMargin = 55;
            for(String button: ControllerComponent.getPressedButtons().keySet()) {
                hudBatch.draw(ControllerComponent.getPressedButtons().get(button).getTexture(), x+= listMargin, Gdx.graphics.getHeight() - 100, 50, 50);
            }
        }

        if(!ControllerComponent.getMovedAxes().isEmpty()) {
            float x = (Gdx.graphics.getWidth() / 2.0f) - X_MARGIN;
            int listMargin = 55;
            for(String axis: ControllerComponent.getMovedAxes().keySet()) {
                hudBatch.draw(ControllerComponent.getMovedAxes().get(axis).getTexture(), x+= listMargin, Gdx.graphics.getHeight() - 150, 50, 50);
            }
        }

        font.draw(hudBatch, "CameraSystem Zoom: " + camera.zoom, Gdx.graphics.getWidth()-X_MARGIN, Gdx.graphics.getHeight() - 800);

        hudBatch.end();
    }
}
