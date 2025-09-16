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
            if (!character.getCharacterComponent().getIsAirborne()) {
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
                character.getCharacterComponent().setIsStepping(false);
                character.getCharacterComponent().setIsJumping(true);
                character.getCharacterComponent().setIsAirborne(true);
                character.getCharacterComponent().setIsRunning(false);
                AnimationSystem.jumpRender(character);
                character.characterBody.setLinearVelocity(character.characterBody.getLinearVelocity().x,0);
                character.characterBody.applyForceToCenter(0, 10000f, true);
            } else {
                character.getCharacterComponent().setIsJumping(false);
                if(character.getCharacterComponent().getIsAirborne()) {
                    character.characterBody.applyLinearImpulse(new Vector2(0f, -9.8f), character.getCharacterComponent().getPosition(), false);
                }
            }
            character.characterBody.setAwake(character.getCharacterComponent().getIsAirborne() || character.getCharacterComponent().getIsRunning() || character.getCharacterComponent().getIsStepping() || character.getCharacterComponent().getIsJumping());
        }
        AnimationSystem.animate(character, batch, elapsed, level);
    }

    public static void handleLeftRight(Player character, Level level) {
        float axis = ControllerComponent.LEFT_STICK_X.getAxisValue();

        Vector2 currentVelocity = character.getCharacterBody().getLinearVelocity();
        Vector2 pos = character.getCharacterBody().getPosition();

        character.getCharacterComponent().setIsAirborne(Math.abs(currentVelocity.y) > 0);

        if ((ControllerComponent.D_PAD_RIGHT.isPressed()) || (axis > 0) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            character.getCharacterComponent().setIsStepping(!character.getCharacterComponent().getIsAirborne());
            if(!character.getCharacterComponent().getIsFlippedRight() && !character.getCharacterComponent().getIsAirborne()) {
                character.getCharacterBody().setLinearVelocity(0f,0f);
            }
            character.getCharacterComponent().setIsFlippedRight(true);

            if (currentVelocity.x < level.game.MAX_WALK_VELOCITY) {
                if (axis > 0) {
                    character.characterBody.applyLinearImpulse(level.game.WALK_SPEED * axis, 0, pos.x, pos.y, true);
                } else {
                    character.characterBody.applyLinearImpulse(level.game.WALK_SPEED, 0, pos.x, pos.y, true);
                }
            }
            if ((ControllerComponent.X_BUTTON.isPressed()) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                if(!character.getCharacterComponent().getIsAirborne()) {
                    character.getCharacterComponent().setIsRunning(true);
                    character.getCharacterComponent().setIsStepping(false);
                    AnimationSystem.runRender(character);
                }
                if (currentVelocity.x < level.game.MAX_VELOCITY) {
                    if (axis > 0) {
                        character.characterBody.applyLinearImpulse(level.game.RUN_SPEED * axis, 0, pos.x, pos.y, true);
                    } else {
                        character.characterBody.applyLinearImpulse(level.game.RUN_SPEED, 0, pos.x, pos.y, true);
                    }
                }
            } else {
                if(!character.getCharacterComponent().getIsAirborne()) {
                    AnimationSystem.walkRender(character);
                }
            }
        }
        else if (ControllerComponent.D_PAD_LEFT.isPressed() || (axis < 0) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            character.getCharacterComponent().setIsStepping(!character.getCharacterComponent().getIsAirborne());
            if(character.getCharacterComponent().getIsFlippedRight() && !character.getCharacterComponent().getIsAirborne()) {
                character.getCharacterBody().setLinearVelocity(0f,0f);
                AnimationSystem.walkRender(character);
            }
            character.getCharacterComponent().setIsFlippedRight(false);
            if (axis < 0) {
                if (currentVelocity.x > -level.game.MAX_WALK_VELOCITY) {
                    character.characterBody.applyLinearImpulse(level.game.WALK_SPEED * axis, 0, pos.x, pos.y, true);
                }
            } else {
                if (currentVelocity.x > -level.game.MAX_WALK_VELOCITY) {
                    character.characterBody.applyLinearImpulse(-level.game.WALK_SPEED, 0, pos.x, pos.y, true);
                }
            }
            if ((ControllerComponent.X_BUTTON.isPressed()) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                if(!character.getCharacterComponent().getIsAirborne()) {
                    character.getCharacterComponent().setIsRunning(true);
                    character.getCharacterComponent().setIsStepping(false);
                    AnimationSystem.runRender(character);
                }
                if (axis < 0) {
                    if (currentVelocity.x > -level.game.MAX_VELOCITY) {
                        character.characterBody.applyLinearImpulse(level.game.WALK_SPEED * axis, 0, pos.x, pos.y, true);
                    }
                } else {
                    if (currentVelocity.x > -level.game.MAX_VELOCITY) {
                        character.characterBody.applyLinearImpulse(-level.game.WALK_SPEED, 0, pos.x, pos.y, true);
                    }
                }
            } else {
                if(!character.getCharacterComponent().getIsAirborne()) {
                    AnimationSystem.walkRender(character);
                }
            }
        }
        if(Math.abs(currentVelocity.x) > 0 && !character.getCharacterComponent().getIsAirborne()) {
            character.characterBody.applyLinearImpulse(-currentVelocity.x,0f,0f, 0f,false);
        }
    }
}



