package com.zordo.entities.world.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zordo.LegendOfZordo;
import com.zordo.components.Component;
import com.zordo.components.camera.CameraComponent;
import com.zordo.components.physics.terrain.surfaces.LevelBoundaryComponent;
import com.zordo.components.physics.terrain.surfaces.PlatformComponent;
import com.zordo.entities.characters.player.Player;
import com.zordo.systems.camera.CameraSystem;
import com.zordo.systems.camera.HudSystem;
import com.zordo.systems.character.movement.PlayerMovementSystem;
import com.zordo.systems.physics.terrain.surfaces.PlatformSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class Level implements Screen {
    final LegendOfZordo game;

    OrthographicCamera camera;

    public SpriteBatch batch;
    private final Texture backgroundTexture;

    Player player;

    public boolean paused;
    float elapsed;

    HashMap<String, Component> components;
    ArrayList<PlatformComponent> platforms;

    public Level(final LegendOfZordo game) {
        elapsed = 0.0f;
        this.game = game;
        platforms = new ArrayList<>();
        PlatformComponent platform = new PlatformComponent(10,1920);
        platform.setCoordinates(0,0);

        PlatformComponent platform2 = new PlatformComponent(10,500);
        platform2.setCoordinates(100,100);

        LevelBoundaryComponent ceiling = new LevelBoundaryComponent(false,true,false);
        ceiling.setCoordinates(0,1080);
        ceiling.getPlatform().setHeight(100);
        ceiling.getPlatform().setWidth(1920);

        LevelBoundaryComponent leftWall = new LevelBoundaryComponent(true,false,false);
        leftWall.setCoordinates(-100,0);
        leftWall.getPlatform().setHeight(1080);
        leftWall.getPlatform().setWidth(100);

        platforms.add(platform);
        platforms.add(platform2);
        platforms.add(ceiling);
        platforms.add(leftWall);

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
        elapsed += Gdx.graphics.getDeltaTime();
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

        PlayerMovementSystem.move(player, batch, this);

        batch.end();

        if (this.paused || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            this.paused = true;
            this.pause();
        }

        if (!this.paused) {
            CameraSystem.follow(player, camera);
        }
        camera.update();
        HudSystem.renderHUD(player);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {
        HudSystem.pause(this);
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