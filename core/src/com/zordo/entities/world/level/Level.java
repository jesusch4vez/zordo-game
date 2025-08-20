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
import com.zordo.components.world.levels.LevelComponent;
import com.zordo.entities.characters.player.Player;
import com.zordo.systems.camera.CameraSystem;
import com.zordo.systems.camera.DebugHudSystem;
import com.zordo.systems.camera.HudSystem;
import com.zordo.systems.character.movement.PlayerMovementSystem;
import com.zordo.systems.physics.terrain.surfaces.PlatformSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class Level extends LevelComponent implements Screen {
    final LegendOfZordo game;

    OrthographicCamera camera;

    public SpriteBatch batch;
    private Texture backgroundTexture;

    Player player;

    public boolean paused;
    float elapsed;

    HashMap<String, Component> components;
    ArrayList<PlatformComponent> platforms;

    LevelBoundaryComponent ceiling;
    LevelBoundaryComponent floor;
    LevelBoundaryComponent leftWall;
    LevelBoundaryComponent rightWall;

    PlatformComponent platform;

    public Level(final LegendOfZordo game) {
        elapsed = 0.0f;
        this.game = game;
        platforms = new ArrayList<>();

        floor = new LevelBoundaryComponent(false, false, true);
        ceiling = new LevelBoundaryComponent(false,true,false);
        leftWall = new LevelBoundaryComponent(true,false,false);
        rightWall = new LevelBoundaryComponent(true,false,false);

        components = new HashMap<>();
        components.put("Camera", new CameraComponent());

        ScreenUtils.clear(0, 0, 0.2f, 1);
        player = new Player();
    }

    @Override
    public void show() {
        platform = new PlatformComponent(10,500);
        platform.setCoordinates(100,100);

        platforms.add(platform);

        floor.getPlatform().setHeight(800);
        floor.getPlatform().setWidth(this.getLevelSize().getWidth());
        floor.setCoordinates(0,-(int)floor.getPlatform().getHeight());

        ceiling.getPlatform().setHeight(10);
        ceiling.getPlatform().setWidth(this.getLevelSize().getWidth());
        ceiling.setCoordinates(0,this.getLevelSize().getHeight());

        leftWall.getPlatform().setHeight(this.getLevelSize().getHeight());
        leftWall.getPlatform().setWidth(10);
        leftWall.setCoordinates(-(int) leftWall.getWidth(),0);

        rightWall.getPlatform().setHeight(this.getLevelSize().getHeight());
        rightWall.getPlatform().setWidth(10);
        rightWall.setCoordinates(this.getLevelSize().getWidth() - (int) rightWall.getWidth(),0);

        TextureRegion background = new TextureRegion();
        backgroundTexture = new Texture("environment/background_32.png");
        background.setTexture(backgroundTexture);
        background.setRegionWidth(this.getLevelSize().getWidth());
        background.setRegionHeight(this.getLevelSize().getHeight());

        platforms.add(floor);
        platforms.add(ceiling);
        platforms.add(leftWall);
        platforms.add(rightWall);
    }

    @Override
    public void render(float v) {
        player.getCharacterComponent().setIsColliding(false);
        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl20.glClearColor(0, 0, 0.2f, 0.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch = new SpriteBatch();
        CameraComponent cam = (CameraComponent) components.get("Camera");
        camera = cam.getCamera();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, this.getLevelSize().getWidth(), this.getLevelSize().getHeight());

        PlayerMovementSystem.move(player, batch, this);

        PlatformSystem.render(platforms, batch);
        PlatformSystem.solidPlatform(platforms, player);

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
        DebugHudSystem.renderDebugHud(player, this);
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