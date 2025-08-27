package zordo.systems.character.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import zordo.components.gamePad.ControllerComponent;
import zordo.entities.characters.player.Player;
import zordo.entities.world.level.Level;
import zordo.systems.character.animation.AnimationSystem;
import zordo.systems.physics.terrain.surfaces.PlatformSystem;

public class PlayerMovementSystem extends CharacterMovementSystem {
    static float elapsed;
    static float gravity = -98f;

    public static void move(Player character, SpriteBatch batch, Level level) {
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
            PlatformSystem.solidPlatform(level.platforms, character, level);

            float axis = ControllerComponent.LEFT_STICK_X.getAxisValue();

            character.getCharacterComponent().setIsFlippedRight(!(axis < 0));

            if ((ControllerComponent.D_PAD_RIGHT.isPressed()) || (axis > 0) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                if(axis > 0) {
                    character.getCharacterComponent().getCollider().x += 100 * (axis) * Gdx.graphics.getDeltaTime();
                } else {
                    character.getCharacterComponent().getCollider().x += 100 * Gdx.graphics.getDeltaTime();
                }
                PlatformSystem.solidPlatform(level.platforms, character, level);
                character.getCharacterComponent().setIsStepping(true);
                character.getCharacterComponent().setIsFlippedRight(true);
                character.getCharacterComponent().setIsRunning(false);

                if(!character.getCharacterComponent().getIsAirborne()) {
                    character.getCharacterComponent().setIsColliding(true);
                    AnimationSystem.walkRender(character);
                } else {
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
                    character.getCharacterComponent().getCollider().x += 125 * (axis) * Gdx.graphics.getDeltaTime();
                    PlatformSystem.solidPlatform(level.platforms, character, level);
                    character.getCharacterComponent().setIsStepping(false);
                    character.getCharacterComponent().setIsFlippedRight(true);
                    character.getCharacterComponent().setIsRunning(true);

                    if(!character.getCharacterComponent().getIsAirborne()) {
                        character.getCharacterComponent().setIsColliding(true);
                        character.getCharacterComponent().setIsRunning(true);
                        character.getCharacterComponent().setIsStepping(false);
                        AnimationSystem.runRender(character);
                    } else {
                        character.getCharacterComponent().setIsRunning(false);
                        character.getCharacterComponent().setIsStepping(false);
                        AnimationSystem.jumpRender(character);
                    }
                }
            } else if (ControllerComponent.D_PAD_LEFT.isPressed() || (axis < 0) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                if(axis < 0) {
                    character.getCharacterComponent().getCollider().x -= 100 * (-axis) * Gdx.graphics.getDeltaTime();
                } else {
                    character.getCharacterComponent().getCollider().x -= 100 * Gdx.graphics.getDeltaTime();
                }
                PlatformSystem.solidPlatform(level.platforms, character, level);

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
                    PlatformSystem.solidPlatform(level.platforms, character, level);
                    character.getCharacterComponent().setIsStepping(false);
                    character.getCharacterComponent().setIsFlippedRight(false);

                    if(!character.getCharacterComponent().getIsAirborne()) {
                        character.getCharacterComponent().setIsStepping(false);
                        character.getCharacterComponent().setIsFlippedRight(false);
                        character.getCharacterComponent().setIsRunning(true);
                        character.getCharacterComponent().setIsColliding(true);
                        AnimationSystem.runRender(character);
                    } else {
                        character.getCharacterComponent().setIsStepping(false);
                        character.getCharacterComponent().setIsFlippedRight(false);
                        character.getCharacterComponent().setIsRunning(false);
                        AnimationSystem.jumpRender(character);
                    }
                }
            }

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
                PlatformSystem.solidPlatform(level.platforms, character, level);
            } else {
                if(character.getCharacterComponent().getIsDescending()) {
                    AnimationSystem.jumpRender(character);
                    character.getCharacterComponent().setIsAscending(false);
                    character.getCharacterComponent().setIsDescending(true);
                    character.getCharacterComponent().getCollider().y += gravity * Gdx.graphics.getDeltaTime();
                    PlatformSystem.solidPlatform(level.platforms, character, level);
                }
                character.getCharacterComponent().setIsJumping(false);
            }
        }
        AnimationSystem.animate(character, batch, elapsed, level);
    }
}
