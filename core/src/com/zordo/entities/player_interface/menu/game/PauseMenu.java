package com.zordo.entities.player_interface.menu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.zordo.LegendOfZordo;
import com.zordo.components.Component;
import com.zordo.components.camera.CameraComponent;
import com.zordo.components.world.levels.LevelComponent;
import com.zordo.entities.world.level.Level;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class PauseMenu implements Screen {

    int selection;
    final LegendOfZordo game;
    LevelComponent selectedLevel;
    private final Texture backgroundTexture;
    Level level;
    private SpriteBatch batch;
    BitmapFont font;

    OrthographicCamera camera;

    HashMap<String, Component> components;

    public PauseMenu(LegendOfZordo game, Level level) {
        this.game = game;

        components = new HashMap<>();
        components.put("Camera", new CameraComponent());

        TextureRegion background = new TextureRegion();
        backgroundTexture = new Texture("environment/background_32.png");
        background.setTexture(backgroundTexture);

        CameraComponent tempCam = (CameraComponent) components.get("Camera");
        this.camera = tempCam.getCamera();
        this.camera.setToOrtho(false, 800,400);

        selection = 0;
        this.level = level;

        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        BitmapFont font = new BitmapFont();
        Gdx.gl20.glClearColor(0, 0, 0.2f, 0.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundTexture,0,0,1920,1080);
        try {
            if(selection >= 0) {
                if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    selection += 1;
                    if(selection >= 3) {
                        selection = 2;
                    }
                }
                else if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    selection -= 1;
                    if(selection <= 0) {
                        selection = 0;
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int listX = 100;
        int listY = 350;
        if (selection == 0) {
            font.draw(batch, " > Exit Game", listX, listY);
        } else if (selection == 1) {
            font.draw(batch, " > Back to Level Selection", listX, listY);
        }  else if (selection == 2) {
            font.draw(batch, " > Back to Level", listX, listY);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            if (selection == 0) {
                Gdx.app.exit();
            } else if (selection == 1) {
                game.setScreen(new LevelMenu(game));
            }  else if(selection == 2) {
                level.paused = false;
                game.setScreen(level);
            }
        }
        batch.end();
        camera.update();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
