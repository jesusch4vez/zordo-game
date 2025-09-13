package zordo.entities.world.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import zordo.LegendOfZordo;
import zordo.models.Component;
import zordo.models.camera.CameraComponent;
import zordo.models.character.CharacterComponent;
import zordo.models.gamePad.ControllerComponent;
import zordo.models.physics.terrain.surfaces.LevelBoundaryComponent;
import zordo.models.physics.terrain.surfaces.PlatformComponent;
import zordo.models.physics.world.WorldComponent;
import zordo.models.world.levels.LevelComponent;
import zordo.entities.characters.player.Player;
import zordo.systems.camera.CameraSystem;
import zordo.systems.camera.DebugHudSystem;
import zordo.systems.camera.HudSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class Level extends LevelComponent implements Screen {
    public final LegendOfZordo game;

    OrthographicCamera camera;

    public SpriteBatch batch;
    private Texture backgroundTexture;

    public Player player;

    public boolean paused;
    float elapsed;

    public HashMap<String, Component> components;
    Array<Body> bodies;

    public Vector2 levelDimensions;
    ArrayList<PlatformComponent> platforms;

    public LevelBoundaryComponent floor;
    public LevelBoundaryComponent ceiling;
    public LevelBoundaryComponent leftWall;
    public LevelBoundaryComponent rightWall;

    WorldComponent world;
    Box2DDebugRenderer debugRenderer;

    int platformCount=100;

    public Level(final LegendOfZordo game) {
        this.game = game;
        this.game.isOnLevelMenu = false;
        this.game.isOnDebugMenu = false;
        this.game.isOnTitleMenu = false;
        this.game.isOnLevel = true;
        this.world = new WorldComponent();
        this.debugRenderer = new Box2DDebugRenderer();

        elapsed = 0.0f;

        components = new HashMap<>();
        components.put("Camera", new CameraComponent());

        ScreenUtils.clear(0, 0, 0.2f, 1);
        player = new Player(world);

        bodies = new Array<>();

        CameraComponent cam = (CameraComponent) components.get("Camera");
        camera = cam.getCamera();
        camera.zoom = 0.25f;
        camera.position.set(player.getCharacterComponent().getPosition(), 0);
        this.levelDimensions = new Vector2();

        platforms = new ArrayList<>();

        for(int i = 2; i < platformCount; i++) {
            PlatformComponent platform = new PlatformComponent(this.world, 30f,5f, 38 * i, 100);
            platforms.add(platform);
            bodies.add(platform.getPlatformBody());
        }
    }

    @Override
    public void show() {
        TextureRegion background = new TextureRegion();
        backgroundTexture = new Texture("environment/background_32.png");
        background.setTexture(backgroundTexture);
        background.setRegionWidth(this.getLevelSize().getWidth());
        background.setRegionHeight(this.getLevelSize().getHeight());

        this.levelDimensions.x = this.getLevelSize().getWidth();
        this.levelDimensions.y = this.getLevelSize().getHeight();

        floor = new LevelBoundaryComponent(world, levelDimensions.x + 50.0f/2f, 50.5f, 0, 0);
        ceiling = new LevelBoundaryComponent(world, levelDimensions.x + 50.0f/2f, 50.5f, 0, levelDimensions.y);
        leftWall = new LevelBoundaryComponent(world, 50.5f, levelDimensions.y + 50.0f/2f, 0, 0);
        rightWall = new LevelBoundaryComponent(world,  50.5f, levelDimensions.y + 50.0f/2f, levelDimensions.x, 0);

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
            bodies.add(player.getCharacterComponent().characterBody);

            bodies.add(floor.getPlatformBody());
            bodies.add(ceiling.getPlatformBody());
            bodies.add(leftWall.getPlatformBody());
            bodies.add(rightWall.getPlatformBody());

            world.getWorld().getBodies(bodies);
            batch = new SpriteBatch();

            batch.setProjectionMatrix(camera.combined);
            batch.begin();

            batch.draw(backgroundTexture, 0, 0, this.getLevelSize().getWidth(), this.getLevelSize().getHeight());

            for (Body b : bodies) {
                if(b.getUserData() instanceof CharacterComponent) {
                    CharacterComponent e = (CharacterComponent) b.getUserData();
                    e.setPosition(b.getPosition().x + 60.0f, b.getPosition().y + 60.0f);
                } else {
                    if(b.getUserData() instanceof LevelBoundaryComponent) {
                        LevelBoundaryComponent p = (LevelBoundaryComponent) b.getUserData();
                        p.setHeight((int) ((LevelBoundaryComponent) b.getUserData()).getHeight());
                        p.setWidth((int) ((LevelBoundaryComponent) b.getUserData()).getWidth());
                        p.getPlatform().setPosition(b.getPosition().x, b.getPosition().y);
                        p.getPlatform().setSize(((LevelBoundaryComponent) b.getUserData()).getWidth(),((LevelBoundaryComponent) b.getUserData()).getHeight());
                        p.setPlatformTexture(((PlatformComponent) b.getUserData()).getPlatformTexture());
                        batch.draw(p.getPlatformTexture(), p.getPlatform().getX() - p.getWidth()/2f, p.getPlatform().getY() - p.getHeight()/2f, p.getPlatform().getWidth(), p.getPlatform().getHeight());
                    } else {
                        PlatformComponent p = (PlatformComponent) b.getUserData();
                        p.setHeight((int) ((PlatformComponent) b.getUserData()).getHeight());
                        p.setWidth((int) ((PlatformComponent) b.getUserData()).getWidth());
                        p.getPlatform().setPosition(b.getPosition().x, b.getPosition().y);
                        p.getPlatform().setSize(p.getPlatform().getWidth(), p.getPlatform().getHeight());
                        p.setPlatformTexture(((PlatformComponent) b.getUserData()).getPlatformTexture());
                        batch.draw(p.getPlatformTexture(), p.getPlatform().getX() - p.getWidth()/2f, p.getPlatform().getY() - p.getHeight()/2f, p.getPlatform().getWidth(), p.getPlatform().getHeight());
                    }
                }
            }

            this.game.controllerListener.handleInput(player, batch, elapsed, this.game, this.world);

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
            CameraSystem.follow(player, camera, this.game);
        }
        HudSystem.renderHUD(player);
        if(this.getDebugMode()) {
            DebugHudSystem.renderDebugHud(player, this, camera);
            debugRenderer.render(world.getWorld(), camera.combined);
        }
        camera.update();
        this.world.getWorld().step(1/60f, 6, 2);
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
