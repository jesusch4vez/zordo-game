package zordo.game.entities.world.level;

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
import zordo.game.models.Component;
import zordo.game.models.camera.CameraComponent;
import zordo.game.models.gamePad.ControllerComponent;
import zordo.game.models.levels.LevelSize;
import zordo.game.models.physics.terrain.surfaces.LevelBoundaryComponent;
import zordo.game.models.physics.terrain.surfaces.PlatformComponent;
import zordo.game.models.physics.world.WorldComponent;
import zordo.game.models.levels.LevelComponent;
import zordo.game.entities.characters.player.Player;
import zordo.game.systems.camera.CameraSystem;
import zordo.game.systems.camera.DebugHudSystem;
import zordo.game.systems.camera.HudSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    public LevelBoundaryComponent floor;
    public LevelBoundaryComponent ceiling;
    public LevelBoundaryComponent leftWall;
    public LevelBoundaryComponent rightWall;

    WorldComponent world;
    Box2DDebugRenderer debugRenderer;

    public Level(final LegendOfZordo game) throws IOException {
        this.game = game;
        this.game.isOnLevelMenu = false;
        this.game.isOnDebugMenu = false;
        this.game.isOnTitleMenu = false;
        this.game.isOnLevel = true;
        this.world = new WorldComponent();
        this.debugRenderer = new Box2DDebugRenderer();

        this.levelDimensions = new Vector2();

        elapsed = 0.0f;

        components = new HashMap<>();
        components.put("Camera", new CameraComponent());

        ScreenUtils.clear(0, 0, 0.2f, 1);
        player = new Player(world);

        bodies = new Array<>();
        List<List<String>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("environment/level_layouts/level_layout-" + game.levelMenu.selectedLevel + ".csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }

        int j = 0;
        int platJ = records.size() + 1;
        int floorWidth = 0;
        int ceilingWidth = 0;
        int leftWallHeight = 0;
        int rightWallHeight = 0;

        for(List<String> record: records) {
            int i = 0;
            for(String value: record) {
                if (value.equals("_") || value.equals("-")) {
                    PlatformComponent platform = new PlatformComponent(this.world, 15f, 3f, (25f * i) + 15f, platJ * 10);
                    bodies.add(platform.getPlatformBody());
                }
                if (value.equals("TB")) {
                    ceilingWidth = 25 * i;
                }
                if (value.equals("LB")) {
                    leftWallHeight = 25 * j;
                }
                if (value.equals("RB")) {
                    rightWallHeight = 25 * j;
                }
                if (value.equals("BB")) {
                    floorWidth = 25 * i;
                }
                i++;
            }
            platJ--;
            j++;
        }

        floor = new LevelBoundaryComponent(world, floorWidth + 50.0f/2f, 25.5f, 0, 0);
        ceiling = new LevelBoundaryComponent(world, ceilingWidth + 50.0f/2f, 25.5f, 0, leftWallHeight);
        leftWall = new LevelBoundaryComponent(world, 25.5f, leftWallHeight + 50.0f/2f, 0, 0);
        rightWall = new LevelBoundaryComponent(world,  25.5f,  rightWallHeight + 50.0f/2f, floorWidth, 0);

        CameraComponent cam = (CameraComponent) components.get("Camera");
        camera = cam.getCamera();
        camera.zoom = 0.045f;
        camera.position.set(player.getCharacterComponent().getPosition(), 0);
        camera.position.y += 10;
        this.levelDimensions = new Vector2();

        LevelSize levelSize = new LevelSize(floorWidth, leftWallHeight);
        this.setLevelSize(levelSize);
    }

    @Override
    public void show() {
        world.getWorld().setContactListener(player);
        TextureRegion background = new TextureRegion();
        backgroundTexture = new Texture("environment/background_32.png");
        background.setTexture(backgroundTexture);
        background.setRegionWidth(this.getLevelSize().getWidth());
        background.setRegionHeight(this.getLevelSize().getHeight());

        this.levelDimensions.x = this.getLevelSize().getWidth();
        this.levelDimensions.y = this.getLevelSize().getHeight();
    }

    @Override
    public void render(float v) {
        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl20.glClearColor(0, 0, 0.2f, 0.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        try {
            bodies.add(player.characterBody);

            bodies.add(floor.getPlatformBody());
            bodies.add(ceiling.getPlatformBody());
            bodies.add(leftWall.getPlatformBody());
            bodies.add(rightWall.getPlatformBody());

            world.getWorld().getBodies(bodies);
            batch = new SpriteBatch();

            batch.setProjectionMatrix(camera.combined);
            batch.begin();

            batch.draw(backgroundTexture, 0, 0, this.getLevelSize().getWidth(), this.getLevelSize().getHeight());

            for (Body gameBody : bodies) {
                if(gameBody.getUserData() instanceof Player) {
                    Player e = (Player) gameBody.getUserData();
                    e.getCharacterComponent().setPosition(gameBody.getPosition().x, gameBody.getPosition().y);
                } else {
                    if(gameBody.getUserData() instanceof LevelBoundaryComponent) {
                        LevelBoundaryComponent p = (LevelBoundaryComponent) gameBody.getUserData();
                        p.setHeight((int) ((LevelBoundaryComponent) gameBody.getUserData()).getHeight());
                        p.setWidth((int) ((LevelBoundaryComponent) gameBody.getUserData()).getWidth());
                        p.getPlatform().setPosition(gameBody.getPosition().x, gameBody.getPosition().y);
                        p.getPlatform().setSize(((LevelBoundaryComponent) gameBody.getUserData()).getWidth(),((LevelBoundaryComponent) gameBody.getUserData()).getHeight());
                        p.setPlatformTexture(((PlatformComponent) gameBody.getUserData()).getPlatformTexture());
                        batch.draw(p.getPlatformTexture(), p.getPlatform().getX() - p.getWidth()/2f, p.getPlatform().getY() - p.getHeight()/2f, p.getPlatform().getWidth(), p.getPlatform().getHeight() + 0.5f);
                    } else {
                        PlatformComponent p = (PlatformComponent) gameBody.getUserData();
                        p.setHeight((int) ((PlatformComponent) gameBody.getUserData()).getHeight());
                        p.setWidth((int) ((PlatformComponent) gameBody.getUserData()).getWidth());
                        p.getPlatform().setPosition(gameBody.getPosition().x, gameBody.getPosition().y);
                        p.getPlatform().setSize(p.getPlatform().getWidth(), p.getPlatform().getHeight());
                        p.setPlatformTexture(((PlatformComponent) gameBody.getUserData()).getPlatformTexture());
                        batch.draw(p.getPlatformTexture(), p.getPlatform().getX() - p.getWidth()/2f, p.getPlatform().getY() - p.getHeight()/2f, p.getPlatform().getWidth(), p.getPlatform().getHeight() + 0.25f);
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
