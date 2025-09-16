package zordo.game.systems.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import zordo.game.entities.characters.player.Player;
import zordo.game.models.gamePad.ControllerComponent;
import zordo.game.entities.world.level.Level;

public class DebugHudSystem {
    public static void renderDebugHud(Player player, Level level, OrthographicCamera camera) {
        SpriteBatch hudBatch = new SpriteBatch();
        hudBatch.begin();
        BitmapFont font = new BitmapFont();

        Vector2 currentVelocity = player.characterBody.getLinearVelocity();

        int X_MARGIN = 1000;
        font.getData().setScale(2);

        font.draw(hudBatch, "AWAKE             " + player.getCharacterBody().isAwake(), Gdx.graphics.getWidth() - X_MARGIN, Gdx.graphics.getHeight() - 50);
        font.draw(hudBatch, "AIRBORNE      " + player.getCharacterComponent().getIsAirborne().toString(), Gdx.graphics.getWidth() - X_MARGIN, Gdx.graphics.getHeight() - 150);
        font.draw(hudBatch, "JUMPING     " + player.getCharacterComponent().getIsJumping().toString(), Gdx.graphics.getWidth() -X_MARGIN, Gdx.graphics.getHeight() - 250);
        font.draw(hudBatch, "RUNNING     " + player.getCharacterComponent().getIsRunning().toString(), Gdx.graphics.getWidth()-X_MARGIN, Gdx.graphics.getHeight() - 300);
        font.draw(hudBatch, "WALKING     " + player.getCharacterComponent().getIsStepping().toString(), Gdx.graphics.getWidth()-X_MARGIN, Gdx.graphics.getHeight() - 350);

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
        font.draw(hudBatch, "CameraSystem Position: " + camera.position.x + ", " + camera.position.y, Gdx.graphics.getWidth()-X_MARGIN - 100, Gdx.graphics.getHeight() - 900);
        font.draw(hudBatch, "CameraSystem Zoom: " + camera.zoom, Gdx.graphics.getWidth()-X_MARGIN - 100, Gdx.graphics.getHeight() - 950);

        font.draw(hudBatch, "CameraSystem viewport width: " + camera.viewportWidth, Gdx.graphics.getWidth()-X_MARGIN - 100, Gdx.graphics.getHeight() - 1100);
        font.draw(hudBatch, "CameraSystem viewport height: " + camera.viewportHeight, Gdx.graphics.getWidth()-X_MARGIN - 100, Gdx.graphics.getHeight() - 1150);

        font.draw(hudBatch, "CameraSystem viewport width - camera.x: " + (camera.viewportWidth - camera.position.x), Gdx.graphics.getWidth()-X_MARGIN - 100, Gdx.graphics.getHeight() - 1200);
        font.draw(hudBatch, "CameraSystem viewport height - camera.y: " + (camera.viewportHeight - camera.position.y), Gdx.graphics.getWidth()-X_MARGIN - 100, Gdx.graphics.getHeight() - 1250);

        font.draw(hudBatch, "CameraSystem viewport width / camera.x: " + (camera.viewportWidth / camera.position.x), Gdx.graphics.getWidth()-X_MARGIN - 100, Gdx.graphics.getHeight() - 1300);
        font.draw(hudBatch, "CameraSystem viewport height / camera.y: " + (camera.viewportHeight / camera.position.y), Gdx.graphics.getWidth()-X_MARGIN - 100, Gdx.graphics.getHeight() - 1350);

        hudBatch.end();
    }
}
