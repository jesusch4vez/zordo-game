package zordo.entities.world.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import zordo.LegendOfZordo;
import zordo.components.Component;
import zordo.components.camera.CameraComponent;
import zordo.components.debug.DebugCollision;
import zordo.components.gamePad.ControllerComponent;
import zordo.components.physics.terrain.surfaces.LevelBoundaryComponent;
import zordo.components.physics.terrain.surfaces.PlatformComponent;
import zordo.components.world.levels.LevelComponent;
import zordo.entities.characters.player.Player;
import zordo.systems.camera.CameraSystem;
import zordo.systems.camera.DebugHudSystem;
import zordo.systems.camera.HudSystem;
import zordo.systems.physics.terrain.surfaces.PlatformSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Level extends LevelComponent implements Screen {
    public final LegendOfZordo game;

    OrthographicCamera camera;

    public SpriteBatch batch;
    private Texture backgroundTexture;

    public Player player;

    public boolean paused;
    float elapsed;

    public HashMap<String, Component> components;
    public ArrayList<PlatformComponent> platforms;
    public DebugCollision platformIntersection;

    LevelBoundaryComponent ceiling;
    LevelBoundaryComponent floor;
    LevelBoundaryComponent leftWall;
    LevelBoundaryComponent rightWall;

    PlatformComponent platform;
    int platformCount;

    public Level(final LegendOfZordo game) {
        this.game = game;
        this.game.level = this;
        this.game.isOnLevelMenu = false;
        this.game.isOnDebugMenu = false;
        this.game.isOnTitleMenu = false;
        this.game.isOnLevel = true;

        elapsed = 0.0f;
        platforms = new ArrayList<>();
        platformIntersection = new DebugCollision();
        platformCount = 100;

        for(int i = 1; i <= platformCount; i++) {
            Random random = new Random();
            int min = 1; // Minimum value (inclusive)
            int max = 1920*3; // Maximum value (inclusive)
            int randx = random.nextInt(max - min + 1) + min;
            int randy = random.nextInt(max - min + 1) + min;
            PlatformComponent platform = new PlatformComponent();
            if(i % 2 == 0) {
                platform.setCoordinates(randx, randy);
                platform.setHeight(200);
                platform.setWidth(100);
            } else {
                platform.setCoordinates(randx, randy);
                platform.setHeight(100);
                platform.setWidth(200);
            }
            platforms.add(platform);
        }

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
        platform = new PlatformComponent(50,500);
        platform.setCoordinates(500,500);

        platforms.add(platform);

        floor.getPlatform().setHeight(800);
        floor.getPlatform().setWidth(this.getLevelSize().getWidth());
        floor.setCoordinates(0,-(int)floor.getPlatform().getHeight());

        ceiling.getPlatform().setHeight(50);
        ceiling.getPlatform().setWidth(this.getLevelSize().getWidth());
        ceiling.setCoordinates(0,this.getLevelSize().getHeight());

        leftWall.getPlatform().setHeight(this.getLevelSize().getHeight());
        leftWall.getPlatform().setWidth(50);
        leftWall.setCoordinates(-(int) leftWall.getWidth(),0);

        rightWall.getPlatform().setHeight(this.getLevelSize().getHeight());
        rightWall.getPlatform().setWidth(50);
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
        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl20.glClearColor(0, 0, 0.2f, 0.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        try {
            batch = new SpriteBatch();
            CameraComponent cam = (CameraComponent) components.get("Camera");
            camera = cam.getCamera();
            batch.setProjectionMatrix(camera.combined);

            batch.begin();
            batch.draw(backgroundTexture, 0, 0, this.getLevelSize().getWidth(), this.getLevelSize().getHeight());
            this.game.controllerListener.handleInput(player, batch, elapsed, this.game);

            PlatformSystem.render(platforms, batch);

            if(this.getDebugMode()) {
                PlatformSystem.renderCollisionDebugPlatform(platformIntersection, platforms, batch);
            }
            batch.end();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (this.paused || Gdx.input.isKeyPressed(Input.Keys.ESCAPE) || ControllerComponent.START_BUTTON.isPressed()) {
            this.paused = true;
            ControllerComponent.START_BUTTON.release();
            this.pause();
        }

        if (!this.paused) {
            CameraSystem.follow(player, camera);
        }
        HudSystem.renderHUD(player);
        if(this.getDebugMode()) {
            DebugHudSystem.renderDebugHud(player, this, camera);
        }
        camera.update();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {
        HudSystem.pause(this.game, this);
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
