package com.zordo.systems.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.zordo.entities.characters.player.Player;

public class CameraSystem {
    public static void follow(Player linko, OrthographicCamera camera) {
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
