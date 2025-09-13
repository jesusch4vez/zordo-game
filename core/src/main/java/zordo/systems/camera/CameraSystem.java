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
            if(game.activeLevel.getLevelSize().getWidth() > camera.viewportWidth * camera.zoom
            && game.activeLevel.getLevelSize().getHeight() > camera.viewportHeight * camera.zoom) {
                camera.zoom += 0.005F;
                //If the A Key is pressed, add 0.02 to the Camera's Zoom
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q) || ControllerComponent.RIGHT_STICK.isPressed()) {
            if(camera.zoom > 0.015) {
                camera.zoom -= 0.005F;
                //If the Q Key is pressed, subtract 0.02 from the Camera's Zoom
            }
        }

        if(game.activeLevel.leftWall.getX() + player.getCharacterComponent().getPosition().x > (camera.viewportWidth / 2) * camera.zoom && (player.getCharacterComponent().getPosition().x) < game.activeLevel.rightWall.getX() - (camera.viewportWidth / 2) * camera.zoom)  {
            camera.position.x = player.getCharacterComponent().getPosition().x;
        } else if(player.getCharacterComponent().getPosition().x <= game.activeLevel.leftWall.getX() + (camera.viewportWidth / 2) * camera.zoom ) {
            camera.position.x = game.activeLevel.leftWall.getX() + (camera.viewportWidth / 2) * camera.zoom - game.activeLevel.leftWall.getWidth();
        } else if(player.getCharacterComponent().getPosition().x >= game.activeLevel.rightWall.getX() - (camera.viewportWidth/2) * camera.zoom ) {
            camera.position.x = game.activeLevel.rightWall.getX() - (camera.viewportWidth / 2) * camera.zoom + game.activeLevel.rightWall.getWidth();
        }

        if(game.activeLevel.floor.getY() + player.getCharacterComponent().getPosition().y > (camera.viewportHeight / 2) * camera.zoom && (player.getCharacterComponent().getPosition().y) < game.activeLevel.ceiling.getY() - (camera.viewportHeight / 2) * camera.zoom)  {
            camera.position.y = player.getCharacterComponent().getPosition().y;
        } else if(player.getCharacterComponent().getPosition().y <= game.activeLevel.floor.getY() + (camera.viewportHeight / 2) * camera.zoom ) {
            camera.position.y = game.activeLevel.floor.getY() + (camera.viewportHeight / 2) * camera.zoom - game.activeLevel.floor.getHeight();
        } else if(player.getCharacterComponent().getPosition().y >= game.activeLevel.ceiling.getX() - (camera.viewportHeight / 2) * camera.zoom ) {
            camera.position.y = game.activeLevel.ceiling.getY() - (camera.viewportHeight / 2) * camera.zoom + game.activeLevel.ceiling.getHeight();
        }
    }
}
