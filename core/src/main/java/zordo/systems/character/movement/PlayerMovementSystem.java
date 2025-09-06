package zordo.systems.character.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import zordo.models.gamePad.ControllerComponent;
import zordo.entities.characters.player.Player;
import zordo.entities.world.level.Level;
import zordo.systems.animation.character.AnimationSystem;

public class PlayerMovementSystem {
    static float elapsed;

    public static void move(Player character, SpriteBatch batch, Level level) {
        if (!level.paused) {
            elapsed += Gdx.graphics.getDeltaTime();
            if(!character.getCharacterComponent().getIsAirborne()) {
                character.getCharacterComponent().setIsStepping(false);
                character.getCharacterComponent().setIsRunning(false);
                character.getCharacterComponent().setIsJumping(false);
                AnimationSystem.standRender(character);
            } else {
                AnimationSystem.jumpRender(character);
            }
            handleLeftRight(character, level);
            if (ControllerComponent.A_BUTTON.isPressed() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                character.getCharacterComponent().setIsStanding(false);
                character.getCharacterComponent().setIsJumping(true);
                character.getCharacterComponent().setIsAirborne(true);
                character.getCharacterComponent().setIsRunning(false);
                AnimationSystem.jumpRender(character);
                character.getCharacterComponent().characterBody.applyLinearImpulse(new Vector2(0f, 15), character.getCharacterComponent().getPosition(), true);
            } else {
                character.getCharacterComponent().setIsJumping(false);
                character.getCharacterComponent().characterBody.applyLinearImpulse(new Vector2(0f, -9.8f), character.getCharacterComponent().getPosition(), true);

            }
        }
        character.getCharacterComponent().setIsAirborne(false);
        AnimationSystem.animate(character, batch, elapsed, level);
    }

    public static void handleLeftRight(Player character, Level level) {
        float axis = ControllerComponent.LEFT_STICK_X.getAxisValue();

        Vector2 currentVelocity = character.getCharacterComponent().getCharacterBody().getLinearVelocity();
        Vector2 pos = character.getCharacterComponent().getCharacterBody().getPosition();

        if(currentVelocity.x < level.game.MAX_VELOCITY) {
            if ((ControllerComponent.D_PAD_RIGHT.isPressed()) || (axis > 0) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                character.getCharacterComponent().setIsStepping(true);
                character.getCharacterComponent().setIsFlippedRight(true);
                AnimationSystem.walkRender(character);
                character.getCharacterComponent().characterBody.applyLinearImpulse(2f, 0, pos.x * 2, pos.y * 2, true);
                if ((ControllerComponent.X_BUTTON.isPressed()) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                    character.getCharacterComponent().setIsRunning(true);
                    character.getCharacterComponent().setIsStepping(false);
                    AnimationSystem.runRender(character);
                    character.getCharacterComponent().characterBody.applyLinearImpulse(5f, 0, pos.x * 2, pos.y * 2, true);
                }
            } else {
                if(Math.abs(currentVelocity.x) > 0f && currentVelocity.x > 0) {
                    character.getCharacterComponent().characterBody.applyLinearImpulse(-2f, 0, pos.x * 2, pos.y * 2, true);
                }
            }
        }
        if(currentVelocity.x > - level.game.MAX_VELOCITY) {
            if (ControllerComponent.D_PAD_LEFT.isPressed() || (axis < 0) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                character.getCharacterComponent().setIsStepping(true);
                character.getCharacterComponent().setIsFlippedRight(false);
                AnimationSystem.walkRender(character);
                character.getCharacterComponent().characterBody.applyLinearImpulse(-2f, 0, pos.x * 2, pos.y * 2, true);
                if ((ControllerComponent.X_BUTTON.isPressed()) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                    character.getCharacterComponent().setIsRunning(true);
                    character.getCharacterComponent().setIsStepping(false);
                    AnimationSystem.runRender(character);
                    character.getCharacterComponent().characterBody.applyLinearImpulse(-5f, 0, character.getCharacterComponent().characterBody.getPosition().x * 2, character.getCharacterComponent().characterBody.getPosition().y * 2, true);
                }
            } else {
                if (Math.abs(currentVelocity.x) > 0f && currentVelocity.x < 0) {
                    character.getCharacterComponent().characterBody.applyLinearImpulse(2f, 0, pos.x * 2, pos.y * 2, true);
                }
            }
        }
    }
}
