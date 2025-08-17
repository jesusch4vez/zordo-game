package com.zordo.entities.world.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zordo.LegendOfZordo;
import com.zordo.components.Component;
import com.zordo.components.camera.Camera;
import com.zordo.components.physics.terrain.surfaces.Platform;
import com.zordo.entities.characters.player.Player;
import com.zordo.systems.camera.CameraSystem;
import com.zordo.systems.character.movement.PlayerMovementSystem;

import java.util.HashMap;

public class Level implements Screen {
    final LegendOfZordo game;

    OrthographicCamera camera;

    public SpriteBatch batch;
    protected Platform slat;
    private final Texture backgroundTexture;

    Player player;

    boolean paused;

    HashMap<String, Component> components;

    public Level(final LegendOfZordo game) {
        this.game = game;

        components = new HashMap<>();

        components.put("Camera", new Camera());
        components.put("Platform", new Platform());

        this.slat = (Platform) components.get("Platform");
        this.slat.getPlatform().setWidth(1920);
        this.slat.setCoordinates(0, 0);

        ScreenUtils.clear(0, 0, 0.2f, 1);

        TextureRegion background = new TextureRegion();
        backgroundTexture = new Texture("environment/background_32.png");
        background.setTexture(backgroundTexture);

        Camera cam = (Camera) components.get("Camera");
        this.camera = cam.getCamera();
        this.camera.setToOrtho(false, 1290,1080);

        player = new Player();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        Gdx.gl20.glClearColor(0, 0, 0.2f, 0.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, 1920, 1080);

        this.slat.render(batch);

        if (this.paused || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            this.paused = true;
            this.pause();
        }


        if (!this.paused) {
            PlayerMovementSystem.move(player, batch);
            CameraSystem.follow(player, camera);
            if (player.health <= 0) {
                BitmapFont font = new BitmapFont();
                font.draw(batch, "GAME OVER", 400, 200);
            }
        }

        batch.end();
        camera.update();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {
        BitmapFont font = new BitmapFont();
        font.draw(batch, ">>>PAUSED<<<", 200, 200);
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            this.paused = false;
        }
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