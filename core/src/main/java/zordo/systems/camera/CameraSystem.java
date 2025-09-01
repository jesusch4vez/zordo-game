package zordo.systems.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import zordo.entities.characters.player.Player;
import zordo.models.gamePad.ControllerComponent;

public class CameraSystem {
    public static void follow(Player player, OrthographicCamera camera) {
        camera.position.set(player.getCharacterComponent().getPosition().x, player.getCharacterComponent().getPosition().y, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.A) || ControllerComponent.LEFT_STICK.isPressed()) {
            camera.zoom += 0.02F;
            //If the A Key is pressed, add 0.02 to the Camera's Zoom
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q) || ControllerComponent.RIGHT_STICK.isPressed()) {
            camera.zoom -= 0.02F;
            //If the Q Key is pressed, subtract 0.02 from the Camera's Zoom
        }
    }
}
