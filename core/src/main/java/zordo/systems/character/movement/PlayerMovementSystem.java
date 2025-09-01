package zordo.systems.character.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import zordo.models.gamePad.ControllerComponent;
import zordo.entities.characters.player.Player;
import zordo.entities.world.level.Level;
import zordo.models.physics.world.WorldComponent;
import zordo.systems.animation.character.AnimationSystem;
import zordo.systems.physics.terrain.surfaces.PlatformSystem;

public class PlayerMovementSystem extends CharacterMovementSystem {
    static float elapsed;
    static float gravity = -98f;

    public static void move(Player character, SpriteBatch batch, Level level, WorldComponent world) {
        if (!level.paused) {
            elapsed += Gdx.graphics.getDeltaTime();
            character.getCharacterComponent().setPosition(character.getCharacterComponent().getCollider().x, character.getCharacterComponent().getCollider().y);
            character.getCharacterComponent().setPreviousPosition(character.getCharacterComponent().getPosition());

            if(!character.getCharacterComponent().getIsAirborne()) {
                character.getCharacterComponent().setIsStepping(false);
                character.getCharacterComponent().setIsRunning(false);
                character.getCharacterComponent().setIsJumping(false);
                character.getCharacterComponent().setIsDescending(false);
                character.getCharacterComponent().setIsAscending(false);
                AnimationSystem.standRender(character);
            } else {
                AnimationSystem.jumpRender(character);
            }

            character.getCharacterComponent().setIsAscending(false);
            character.getCharacterComponent().setIsDescending(true);
            character.getCharacterComponent().getCollider().y += gravity * Gdx.graphics.getDeltaTime();
            PlatformSystem.solidPlatform(world.platforms, character, level, world);

            handleLeftRight(character, level, world);
            handleCrouch(character, level, world);
            if (ControllerComponent.A_BUTTON.isPressed() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                character.getCharacterComponent().setIsStanding(false);
                character.getCharacterComponent().setIsJumping(true);
                character.getCharacterComponent().setIsAirborne(true);
                character.getCharacterComponent().setIsAscending(true);
                character.getCharacterComponent().setIsDescending(false);
                character.getCharacterComponent().setIsColliding(false);
                character.getCharacterComponent().setIsRunning(false);
                AnimationSystem.jumpRender(character);
                character.getCharacterComponent().getCollider().y += 175 * Gdx.graphics.getDeltaTime();
                PlatformSystem.solidPlatform(world.platforms, character, level, world);

            } else {
                if(character.getCharacterComponent().getIsDescending()) {
                    AnimationSystem.jumpRender(character);
                    character.getCharacterComponent().setIsAscending(false);
                    character.getCharacterComponent().setIsDescending(true);
                    character.getCharacterComponent().getCollider().y += gravity * Gdx.graphics.getDeltaTime();
                    PlatformSystem.solidPlatform(world.platforms, character, level, world);

                }
                character.getCharacterComponent().setIsJumping(false);
            }
        }
        AnimationSystem.animate(character, batch, elapsed, level);
    }

    public static void handleLeftRight(Player character, Level level, WorldComponent world) {
        float axis = ControllerComponent.LEFT_STICK_X.getAxisValue();

        if ((ControllerComponent.D_PAD_RIGHT.isPressed()) || (axis > 0) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if(axis > 0) {
                character.getCharacterComponent().getCollider().x += 100 * (axis) * Gdx.graphics.getDeltaTime();
            } else {
                character.getCharacterComponent().getCollider().x += 100 * Gdx.graphics.getDeltaTime();
            }
            PlatformSystem.solidPlatform(world.platforms, character, level, world);

            character.getCharacterComponent().setIsStepping(true);
            character.getCharacterComponent().setIsFlippedRight(true);
            character.getCharacterComponent().setIsRunning(false);
            character.getCharacterComponent().setIsColliding(true);

            if(!character.getCharacterComponent().getIsAirborne()) {
                AnimationSystem.walkRender(character);
            }
            if(character.getCharacterComponent().getIsAirborne()) {
                character.getCharacterComponent().setIsRunning(false);
                character.getCharacterComponent().setIsStepping(false);
                AnimationSystem.jumpRender(character);
            }

            if(ControllerComponent.X_BUTTON.isPressed() || Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                if(axis > 0) {
                    character.getCharacterComponent().getCollider().x += 125 * (axis) * Gdx.graphics.getDeltaTime();
                } else {
                    character.getCharacterComponent().getCollider().x += 125 * Gdx.graphics.getDeltaTime();
                }
                PlatformSystem.solidPlatform(world.platforms, character, level, world);

                character.getCharacterComponent().setIsStepping(false);
                character.getCharacterComponent().setIsFlippedRight(true);
                character.getCharacterComponent().setIsRunning(true);

                if(!character.getCharacterComponent().getIsAirborne()) {
                    character.getCharacterComponent().setIsColliding(true);
                    character.getCharacterComponent().setIsRunning(true);
                    character.getCharacterComponent().setIsStepping(false);
                    AnimationSystem.runRender(character);
                }
                if(character.getCharacterComponent().getIsAirborne()) {
                    character.getCharacterComponent().setIsRunning(false);
                    character.getCharacterComponent().setIsStepping(false);
                    AnimationSystem.jumpRender(character);
                }
            }
        }
        if (ControllerComponent.D_PAD_LEFT.isPressed() || (axis < 0) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(axis < 0) {
                character.getCharacterComponent().getCollider().x -= 100 * (-axis) * Gdx.graphics.getDeltaTime();
            } else {
                character.getCharacterComponent().getCollider().x -= 100 * Gdx.graphics.getDeltaTime();
            }
            PlatformSystem.solidPlatform(world.platforms, character, level, world);


            if(!character.getCharacterComponent().getIsAirborne()) {
                character.getCharacterComponent().setIsStepping(true);
                character.getCharacterComponent().setIsFlippedRight(false);
                character.getCharacterComponent().setIsRunning(false);
                character.getCharacterComponent().setIsColliding(true);
                AnimationSystem.walkRender(character);
            } else {
                character.getCharacterComponent().setIsStepping(false);
                character.getCharacterComponent().setIsFlippedRight(false);
                character.getCharacterComponent().setIsRunning(false);
                AnimationSystem.jumpRender(character);
            }

            if(ControllerComponent.X_BUTTON.isPressed() || Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
                character.getCharacterComponent().setIsRunning(true);
                if(axis < 0) {
                    character.getCharacterComponent().getCollider().x -= 125 * (-axis) * Gdx.graphics.getDeltaTime();
                } else {
                    character.getCharacterComponent().getCollider().x -= 125 * Gdx.graphics.getDeltaTime();
                }
                PlatformSystem.solidPlatform(world.platforms, character, level, world);

                character.getCharacterComponent().setIsStepping(false);
                character.getCharacterComponent().setIsFlippedRight(false);

                if(!character.getCharacterComponent().getIsAirborne()) {
                    character.getCharacterComponent().setIsStepping(false);
                    character.getCharacterComponent().setIsFlippedRight(false);
                    character.getCharacterComponent().setIsRunning(true);
                    character.getCharacterComponent().setIsColliding(true);
                    AnimationSystem.runRender(character);
                }
                if(character.getCharacterComponent().getIsAirborne()) {
                    character.getCharacterComponent().setIsStepping(false);
                    character.getCharacterComponent().setIsFlippedRight(false);
                    character.getCharacterComponent().setIsRunning(false);
                    AnimationSystem.jumpRender(character);
                }
            }
        }
    }

    public static void handleCrouch(Player character, Level level, WorldComponent world) {
        float axisY = ControllerComponent.LEFT_STICK_Y.getAxisValue();
        float axisX = ControllerComponent.LEFT_STICK_X.getAxisValue();
        if((ControllerComponent.D_PAD_DOWN.isPressed() || (axisY > 0))
            && (Math.abs(axisX) == 0)
            && (!ControllerComponent.D_PAD_LEFT.isPressed()
            && !ControllerComponent.D_PAD_RIGHT.isPressed())) {
                character.getCharacterComponent().setIsDucking(true);
                AnimationSystem.duckRender(character);
            PlatformSystem.solidPlatform(world.platforms, character, level, world);

        }
    }
}
