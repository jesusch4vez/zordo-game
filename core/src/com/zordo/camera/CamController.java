package com.zordo.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.zordo.game.characters.player.linko.Linko;

public class CamController {
    public void handleInput(Linko linko, OrthographicCamera camera) {
        camera.position.set(linko.getPosition().x, linko.getPosition().y, 0);
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
