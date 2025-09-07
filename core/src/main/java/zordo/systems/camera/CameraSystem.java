package zordo.systems.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import zordo.LegendOfZordo;
import zordo.entities.characters.player.Player;
import zordo.models.gamePad.ControllerComponent;

public class CameraSystem {
    public static void follow(Player player, OrthographicCamera camera, LegendOfZordo game) {
        if (Gdx.input.isKeyPressed(Input.Keys.A) || ControllerComponent.LEFT_STICK.isPressed()) {
            camera.zoom += 0.005F;
            //If the A Key is pressed, add 0.02 to the Camera's Zoom
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q) || ControllerComponent.RIGHT_STICK.isPressed()) {
            camera.zoom -= 0.005F;
            //If the Q Key is pressed, subtract 0.02 from the Camera's Zoom
        }

        if(camera.viewportWidth / camera.position.x < 8 && camera.viewportWidth / camera.position.x > 3 )  {
            camera.position.x = player.getCharacterComponent().getPosition().x;
        }
        else if(camera.viewportWidth / camera.position.x >= 8) {
            camera.position.x = game.activeLevel.leftWall.getX() + 24.0f;
            if(player.getCharacterComponent().getPosition().x >= game.activeLevel.leftWall.getX() + 24.0f) {
                camera.position.x = player.getCharacterComponent().getPosition().x;
            }
        }
        else if(camera.viewportWidth / camera.position.x <= 3) {
                camera.position.x = game.activeLevel.rightWall.getX() - 24.0f;
            if(player.getCharacterComponent().getPosition().x <= game.activeLevel.rightWall.getX() - 24.0f) {
                camera.position.x = player.getCharacterComponent().getPosition().x;
            }
        }

        if (camera.viewportHeight / camera.position.y < 8 && camera.viewportHeight / camera.position.y > 4 )  {
            camera.position.y = player.getCharacterComponent().getPosition().y;
        }
        else if(camera.viewportHeight / camera.position.y >= 8) {
            camera.position.y = game.activeLevel.floor.getY() + 1.2f;
            if(player.getCharacterComponent().getPosition().y >= game.activeLevel.floor.getY() + 1.20f) {
                camera.position.y = player.getCharacterComponent().getPosition().y;
            }
        }
        else if(camera.viewportHeight / camera.position.y <= 4) {
            camera.position.y = game.activeLevel.ceiling.getY() - 1.20f;
            if(player.getCharacterComponent().getPosition().y <= game.activeLevel.ceiling.getY() - 1.20f) {
                camera.position.y = player.getCharacterComponent().getPosition().y;
            }
        }
    }
}
