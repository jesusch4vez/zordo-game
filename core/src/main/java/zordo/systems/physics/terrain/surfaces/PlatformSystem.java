package zordo.systems.physics.terrain.surfaces;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import zordo.components.debug.DebugCollision;
import zordo.components.gamePad.ControllerComponent;
import zordo.components.physics.terrain.surfaces.PlatformComponent;
import zordo.entities.characters.Character;
import zordo.entities.characters.player.Player;
import zordo.entities.world.level.Level;
import zordo.systems.character.movement.PlayerMovementSystem;

import java.util.ArrayList;

public class PlatformSystem {

    public static void solidPlatform(ArrayList<PlatformComponent> platforms, Character character, Level level) {
        for (PlatformComponent platform : platforms) {
            Rectangle intersector = new Rectangle();
            if (!character.getCharacterComponent().getIsColliding() && Intersector.intersectRectangles(character.getCharacterComponent().getCollider(), platform.getPlatform(), intersector)) {
                character.getCharacterComponent().setPosition(character.getCharacterComponent().getPreviousPosition());
                character.getCharacterComponent().setIsColliding(true);
                level.platformIntersection.setPlatform(intersector);
                if (platformIsBelow(character,platform)) {
                    character.getCharacterComponent().setIsAirborne(false);
                    character.getCharacterComponent().setIsJumping(false);
                    character.getCharacterComponent().setIsDescending(false);

                    platform.setCharacterRelativePosition("Character is above");
                    character.getCharacterComponent().setY(platform.getY() + platform.getHeight());
                    break;
                } else if (platformIsAbove(character,platform)) {
                    character.getCharacterComponent().setIsAirborne(true);
                    character.getCharacterComponent().setIsDescending(true);
                    character.getCharacterComponent().setIsAscending(false);

                    platform.setCharacterRelativePosition("Character is below");
                    character.getCharacterComponent().setY(platform.getY() - character.getCharacterComponent().getCollider().getHeight());
                    PlayerMovementSystem.handleLeftRight((Player)character,level);
                    break;
                } else if (platformisOnLeft(character,platform)) {
                    platform.setCharacterRelativePosition("Character is right");
                    character.getCharacterComponent().setX(platform.getX() + platform.getWidth());
                    break;
                } else if (platformIsOnRight(character,platform)) {
                    platform.setCharacterRelativePosition("Character is left");
                    character.getCharacterComponent().setX(platform.getX() - character.getCharacterComponent().getCollider().getWidth());
                    break;
                }
            } else {
                character.getCharacterComponent().setIsColliding(false);
                if (platformIsBelow(character,platform)) {
                    platform.setCharacterRelativePosition("Character is above");
                } else if (platformIsAbove(character,platform)) {
                    platform.setCharacterRelativePosition("Character is below");
                } else if (platformisOnLeft(character,platform)) {
                    platform.setCharacterRelativePosition("Character is right");
                } else if (platformIsOnRight(character,platform)) {
                    platform.setCharacterRelativePosition("Character is left");
                }
            }
        }
    }


    public static Boolean platformIsAbove(Character character, PlatformComponent platform) {
       return platform.getPlatform().getY() >= character.getCharacterComponent().getCollider().getY() + character.getCharacterComponent().getCollider().getHeight();
    };

    public static Boolean platformIsBelow(Character character, PlatformComponent platform) {
        return platform.getPlatform().getY() + platform.getHeight() <= character.getCharacterComponent().getCollider().getY();
    };

    public static Boolean platformisOnLeft(Character character, PlatformComponent platform) {
        return (platform.getPlatform().getX() + platform.getWidth() <= character.getCharacterComponent().getCollider().getX()) && !platformIsAbove(character,platform) && !platformIsBelow(character,platform);
    };

    public static Boolean platformIsOnRight(Character character, PlatformComponent platform) {
        return platform.getPlatform().getX() >= character.getCharacterComponent().getCollider().getX() + character.getCharacterComponent().getCollider().getWidth() && !platformIsAbove(character,platform) && !platformIsBelow(character,platform);
    };

    public static void render(ArrayList<PlatformComponent> platforms, SpriteBatch batch) {
        for (PlatformComponent platform : platforms) {
            batch.draw(platform.getPlatformTexture(), platform.getX(), platform.getY(), platform.getPlatform().getWidth(), platform.getPlatform().getHeight());
        }
    }

    public static void renderCollisionDebugPlatform(DebugCollision intersection, ArrayList<PlatformComponent> platforms, SpriteBatch batch) {
        BitmapFont font = new BitmapFont();

        font.getData().setScale(2);

        for (PlatformComponent platform : platforms) {
            long widthCoordinate = (long) platform.getX() + (long) platform.getWidth();
            long heightCoordinate = (long) platform.getY() + (long) platform.getHeight();
            font.draw(batch, platform.getX() + ", " + platform.getY(), platform.getX(), platform.getY() - 25);

            font.draw(batch, widthCoordinate + ", " + heightCoordinate, platform.getX() + platform.getWidth(), platform.getY() + platform.getHeight() + 25);

            font.draw(batch, platform.getCharacterRelativePosition(), platform.getX(), platform.getY());
            batch.draw(platform.getPlatformTexture(), platform.getX(), platform.getY(), platform.getPlatform().getWidth(), platform.getPlatform().getHeight());
        }
        long intersectWidthCoordinate = (long) intersection.getX() + (long) intersection.getWidth();
        long intersectHeightCoordinate = (long) intersection.getY() + (long) intersection.getHeight();

        font.draw(batch, intersection.getX() + ", " + intersection.getY(), intersection.getX(), intersection.getY());
        font.draw(batch, intersectWidthCoordinate + ", " + intersectHeightCoordinate, intersection.getX() + intersection.getWidth(), intersection.getY() + intersection.getHeight() + 25);

        batch.draw(intersection.getPlatformTexture(), intersection.getX(), intersection.getY(), intersection.getPlatform().getWidth(), intersection.getPlatform().getHeight());
    }
}
