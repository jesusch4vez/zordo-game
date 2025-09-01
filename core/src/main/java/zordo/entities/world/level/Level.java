package zordo.entities.world.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import zordo.LegendOfZordo;
import zordo.models.Component;
import zordo.models.camera.CameraComponent;
import zordo.models.character.CharacterComponent;
import zordo.models.gamePad.ControllerComponent;
import zordo.models.physics.terrain.surfaces.PlatformComponent;
import zordo.models.physics.world.WorldComponent;
import zordo.models.world.levels.LevelComponent;
import zordo.entities.characters.player.Player;
import zordo.systems.camera.CameraSystem;
import zordo.systems.camera.DebugHudSystem;
import zordo.systems.camera.HudSystem;
import zordo.systems.physics.terrain.surfaces.PlatformSystem;

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

//    PlatformComponent platform;
    WorldComponent world;
    Box2DDebugRenderer debugRenderer;

    int platformCount;

    public Level(final LegendOfZordo game) {
        this.game = game;
        this.game.level = this;
        this.game.isOnLevelMenu = false;
        this.game.isOnDebugMenu = false;
        this.game.isOnTitleMenu = false;
        this.game.isOnLevel = true;
        this.world = new WorldComponent();
        this.debugRenderer = new Box2DDebugRenderer();
//        bodies = new Array<>();

        elapsed = 0.0f;

        components = new HashMap<>();
        components.put("Camera", new CameraComponent());

        ScreenUtils.clear(0, 0, 0.2f, 1);
        player = new Player(world);

        // Create an array to be filled with the bodies
        // (better don't create a new one every time though)

        bodies = new Array<>();
    }

    @Override
    public void show() {
//        platform = new PlatformComponent(5f,50f, this.world);
//        platform.setCoordinates(50,50);

//        world.platforms.add(platform);

        world.floor.getPlatform().setHeight(8);
        world.floor.getPlatform().setWidth(this.getLevelSize().getWidth());
        world.floor.setCoordinates(0,-(int) world.floor.getPlatform().getHeight() - 10);

        world.ceiling.getPlatform().setHeight(5);
        world.ceiling.getPlatform().setWidth(this.getLevelSize().getWidth());
        world.ceiling.setCoordinates(0,this.getLevelSize().getHeight());

//        world.leftWall.getPlatform().setHeight(this.getLevelSize().getHeight());
//        world.leftWall.getPlatform().setWidth(5);
//        world.leftWall.setCoordinates(-(int) world.leftWall.getWidth(),0);

//        world.rightWall.getPlatform().setHeight(this.getLevelSize().getHeight());
//        world.rightWall.getPlatform().setWidth(5);
//        world.rightWall.setCoordinates(this.getLevelSize().getWidth() - (int) world.rightWall.getWidth(),0);

        TextureRegion background = new TextureRegion();
        backgroundTexture = new Texture("environment/background_32.png");
        background.setTexture(backgroundTexture);
        background.setRegionWidth(this.getLevelSize().getWidth());
        background.setRegionHeight(this.getLevelSize().getHeight());

        world.platforms.add(world.floor);
        world.platforms.add(world.ceiling);
//        world.platforms.add(world.leftWall);
//        world.platforms.add(world.rightWall);
    }

    @Override
    public void render(float v) {
        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl20.glClearColor(0, 0, 0.2f, 0.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        try {
            bodies.add(player.getCharacterComponent().characterBody);
            bodies.add(world.floor.getPlatformBody());
// Now fill the array with all bodies
            world.getWorld().getBodies(bodies);

//            for (Body b : bodies) {
                // Get the body's user data - in this example, our user
                // data is an instance of the Entity class
//                CharacterComponent e = (CharacterComponent) b.getUserData();

//                if (e != null) {
                    // Update the entities/sprites position and angle
//                    e.setPosition(b.getPosition().x, b.getPosition().y);
                    // We need to convert our angle from radians to degrees
//                    e.setRotation(MathUtils.radiansToDegrees * b.getAngle());
//                }
//            }

            batch = new SpriteBatch();
            CameraComponent cam = (CameraComponent) components.get("Camera");
            camera = cam.getCamera();

            batch.setProjectionMatrix(camera.combined);
            batch.begin();
            batch.draw(backgroundTexture, 0, 0, this.getLevelSize().getWidth(), this.getLevelSize().getHeight());

            this.game.controllerListener.handleInput(player, batch, elapsed, this.game, this.world);

            PlatformSystem.render(world.platforms, batch);

            if(this.getDebugMode()) {
                PlatformSystem.renderCollisionDebugPlatform(world.platformIntersection, world.platforms, batch);
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

        debugRenderer.render(world.getWorld(), camera.combined);
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
