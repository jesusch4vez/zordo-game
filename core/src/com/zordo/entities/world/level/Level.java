package com.zordo.entities.world.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zordo.LegendOfZordo;
import com.zordo.components.Component;
import com.zordo.components.camera.CameraComponent;
import com.zordo.components.physics.terrain.surfaces.PlatformComponent;
import com.zordo.entities.characters.player.Player;
import com.zordo.systems.camera.CameraSystem;
import com.zordo.systems.camera.HudSystem;
import com.zordo.systems.character.movement.PlayerMovementSystem;
import com.zordo.systems.health.HealthSystem;
import com.zordo.systems.physics.terrain.surfaces.PlatformSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class Level implements Screen {
    final LegendOfZordo game;

    OrthographicCamera camera;

    public SpriteBatch batch;
    private final Texture backgroundTexture;

    Player player;

    boolean paused;

    HashMap<String, Component> components;
    ArrayList<PlatformComponent> platforms;

    public Level(final LegendOfZordo game) {
        this.game = game;
        platforms = new ArrayList<>();
        PlatformComponent platform = new PlatformComponent(10,1920);
        platform.setCoordinates(0,0);

        PlatformComponent platform2 = new PlatformComponent(10,500);
        platform2.setCoordinates(100,100);

        platforms.add(platform);
        platforms.add(platform2);

        components = new HashMap<>();
        components.put("Camera", new CameraComponent());

        ScreenUtils.clear(0, 0, 0.2f, 1);

        TextureRegion background = new TextureRegion();
        backgroundTexture = new Texture("environment/background_32.png");
        background.setTexture(backgroundTexture);

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
        CameraComponent cam = (CameraComponent) components.get("Camera");
        camera = cam.getCamera();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, 1920, 1080);

        PlatformSystem.render(platforms, batch);
        PlatformSystem.solidPlatform(platforms, player);

        if (this.paused || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            this.paused = true;
            this.pause();
        }

        if (!this.paused) {
            PlayerMovementSystem.move(player, batch);
            CameraSystem.follow(player, camera);
            if (player.getCharacterComponent().health <= 0) {
                BitmapFont font = new BitmapFont();
                font.draw(batch, "GAME OVER", 400, 200);
            }
        }

        batch.end();
        camera.update();
        HudSystem.renderHUD(player);
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