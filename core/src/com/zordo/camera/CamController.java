package com.zordo.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class CamController {
    public void handleInput(OrthographicCamera camera) {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.zoom += 0.02;
            //If the A Key is pressed, add 0.02 to the Camera's Zoom
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.zoom -= 0.02;
            //If the Q Key is pressed, subtract 0.02 from the Camera's Zoom
        }
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
//        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//            camera.translate(0, -3, 0);
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//            camera.translate(0, 3, 0);
//        }

//        camera.zoom = MathUtils.clamp(camera.zoom, 0.1f, 100/camera.viewportWidth);
//
//        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
//        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;
//
//        camera.position.x = MathUtils.clamp(camera.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
//        camera.position.y = MathUtils.clamp(camera.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
    }

}
