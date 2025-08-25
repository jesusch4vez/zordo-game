package zordo.entities.player_interface.menu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import zordo.systems.menu.MenuSystem;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DebugModeMenu implements Screen {
    final LegendOfZordo game;
    LevelComponent selectedLevel;
    private final Texture backgroundTexture;

    int debug;

    private SpriteBatch batch;
    OrthographicCamera camera;

    HashMap<String, Component> components;

    public DebugModeMenu(LegendOfZordo game, LevelComponent selectedLevel) {
        this.game = game;
        this.selectedLevel = selectedLevel;

        components = new HashMap<>();
        components.put("Camera", new CameraComponent());

        TextureRegion background = new TextureRegion();
        backgroundTexture = new Texture("environment/background_32.png");
        background.setTexture(backgroundTexture);

        debug = 0;
        CameraComponent tempCam = (CameraComponent) components.get("Camera");
        this.camera = tempCam.getCamera();
        this.camera.setToOrtho(false, 800,400);
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

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundTexture,0,0,1920,1080);

        try {
            if( debug >= 0 && debug < 2 ) {
                if (( Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) ) && debug < 2) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    debug += 1;
                    if(debug >= 2) {
                        debug = 1;
                    }
                }
                else if ( ( Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.LEFT) ) && debug >= 0) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    debug -= 1;
                    if(debug <= 0) {
                        debug = 0;
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int listX = 100;
        int listY = 350;
        if (debug == 0) {
            font.draw(batch, " > DebugMode - OFF", listX, listY);
        } else {
            font.draw(batch, " > DebugMode - ON", listX, listY);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            selectedLevel.setDebugMode(debug == 1);
            MenuSystem.activateLevel(this.game, selectedLevel);
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
