package com.zordo.system.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.zordo.system.character.player.Character;

public class CamController {
    public void handleInput(Character character, OrthographicCamera camera) {
        camera.position.set(character.getPosition().x, character.getPosition().y, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-100 * Gdx.graphics.getDeltaTime(), 0, 0);
            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
                camera.translate(-50 * Gdx.graphics.getDeltaTime(), 0, 0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(100 * Gdx.graphics.getDeltaTime(), 0, 0);
            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
                camera.translate(50 * Gdx.graphics.getDeltaTime(), 0, 0);
            }
        }
    }
}
