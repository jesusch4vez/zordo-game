package zordo.entities.player_interface.menu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import zordo.LegendOfZordo;
import zordo.components.Component;
import zordo.components.camera.CameraComponent;
import zordo.components.world.levels.LevelComponent;

import java.util.HashMap;

public class DebugModeMenu implements Screen {
    final LegendOfZordo game;
    public LevelComponent selectedLevel;
    private final Texture backgroundTexture;

    public int debug;

    OrthographicCamera camera;

    HashMap<String, Component> components;

    public DebugModeMenu(LegendOfZordo game, LevelComponent selectedLevel) {
        this.game = game;
        this.selectedLevel = selectedLevel;
        this.game.debugModeMenu = this;
        this.game.isOnDebugMenu = true;
        this.game.isOnLevelMenu = false;
        this.game.isOnTitleMenu = false;

        components = new HashMap<>();
        components.put("Camera", new CameraComponent());

        TextureRegion background = new TextureRegion();
        backgroundTexture = new Texture("environment/background_32.png");
        background.setTexture(backgroundTexture);

        debug = 0;
    }

    @Override
    public void show() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render(float delta) {

        BitmapFont font = new BitmapFont();
        Gdx.gl20.glClearColor(0, 0, 0.2f, 0.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        CameraComponent tempCam = (CameraComponent) components.get("Camera");
        this.camera = tempCam.getCamera();

        SpriteBatch batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundTexture,0,0, camera.viewportWidth,camera.viewportHeight);


        try {
            this.game.controllerListener.handleInput(Gdx.graphics.getDeltaTime(), this.game);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int listX = 100;
        float listY = camera.viewportHeight - 100;
        font.getData().setScale(2);
        if (debug == 0) {
            font.draw(batch, " > DebugMode - OFF", listX, listY);
        } else {
            font.draw(batch, " > DebugMode - ON", listX, listY);
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
