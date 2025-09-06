package zordo.systems.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import zordo.LegendOfZordo;
import zordo.entities.characters.player.Player;

public class CameraSystem {
    public static void follow(Player player, OrthographicCamera camera, LegendOfZordo game) {
        if(camera.viewportWidth / camera.position.x < 8 && camera.viewportWidth / camera.position.x > 3 )  {
            camera.position.set(player.getCharacterComponent().getPosition().x, player.getCharacterComponent().getPosition().y, 0);
        }
        else if(camera.viewportWidth / camera.position.x >= 8) {
//        else if(player.getCharacterComponent().getPosition().x < game.activeLevel.rightWall.getX() - 300) {
            camera.position.y = player.getCharacterComponent().getPosition().y;
            camera.position.x = game.activeLevel.leftWall.getX() + 240;
//            camera.position.set(player.getCharacterComponent().getPosition().x, player.getCharacterComponent().getPosition().y, 0);
//        }
            if(player.getCharacterComponent().getPosition().x >= game.activeLevel.leftWall.getX() + 240) {
                camera.position.x = player.getCharacterComponent().getPosition().x;
            }
        }
        else if(camera.viewportWidth / camera.position.x <= 3) {
//          else if(player.getCharacterComponent().getPosition().x < game.activeLevel.rightWall.getX() - 300) {
                camera.position.y = player.getCharacterComponent().getPosition().y;
                camera.position.x = game.activeLevel.rightWall.getX() - 240;
//            camera.position.set(player.getCharacterComponent().getPosition().x, player.getCharacterComponent().getPosition().y, 0);
//        }
            if(player.getCharacterComponent().getPosition().x <= game.activeLevel.rightWall.getX() - 240) {
                camera.position.x = player.getCharacterComponent().getPosition().x;
            }
        }
//        else if()  {
//            camera.position.set(player.getCharacterComponent().getPosition().x, player.getCharacterComponent().getPosition().y, 0);
//        }
//        else if(camera.viewportWidth / camera.position.x <= 3) {
////        else if(player.getCharacterComponent().getPosition().x < game.activeLevel.rightWall.getX() - 300) {
//            camera.position.y = player.getCharacterComponent().getPosition().y;
//            camera.position.x = game.activeLevel.rightWall.getX() - 240;
////            camera.position.set(player.getCharacterComponent().getPosition().x, player.getCharacterComponent().getPosition().y, 0);
////        }
////            if(player.getCharacterComponent().getPosition().x <= game.activeLevel.rightWall.getX() - 240) {
////                camera.position.x = player.getCharacterComponent().getPosition().x;
////            }
//        }
    }
}
