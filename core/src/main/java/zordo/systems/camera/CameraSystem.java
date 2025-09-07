package zordo.systems.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import zordo.LegendOfZordo;
import zordo.entities.characters.player.Player;

public class CameraSystem {
    public static void follow(Player player, OrthographicCamera camera, LegendOfZordo game) {
        if(camera.viewportWidth / camera.position.x < 8 && camera.viewportWidth / camera.position.x > 3 )  {
            camera.position.x = player.getCharacterComponent().getPosition().x;
        }
        else if(camera.viewportWidth / camera.position.x >= 8) {
            camera.position.x = game.activeLevel.leftWall.getX() + 240;
            if(player.getCharacterComponent().getPosition().x >= game.activeLevel.leftWall.getX() + 240) {
                camera.position.x = player.getCharacterComponent().getPosition().x;
            }
        }
        else if(camera.viewportWidth / camera.position.x <= 3) {
                camera.position.x = game.activeLevel.rightWall.getX() - 240;
            if(player.getCharacterComponent().getPosition().x <= game.activeLevel.rightWall.getX() - 240) {
                camera.position.x = player.getCharacterComponent().getPosition().x;
            }
        }

        if (camera.viewportHeight / camera.position.y < 8 && camera.viewportHeight / camera.position.y > 4 )  {
            camera.position.y = player.getCharacterComponent().getPosition().y;
        }
        else if(camera.viewportHeight / camera.position.y >= 8) {
            camera.position.y = game.activeLevel.floor.getY() + 120;
            if(player.getCharacterComponent().getPosition().y >= game.activeLevel.floor.getY() + 120) {
                camera.position.y = player.getCharacterComponent().getPosition().y;
            }
        }
        else if(camera.viewportHeight / camera.position.y <= 4) {
            camera.position.y = game.activeLevel.ceiling.getY() - 120;
            if(player.getCharacterComponent().getPosition().y <= game.activeLevel.ceiling.getY() - 120) {
                camera.position.y = player.getCharacterComponent().getPosition().y;
            }
        }
    }
}
