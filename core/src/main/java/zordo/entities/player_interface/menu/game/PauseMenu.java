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
import zordo.models.Component;
import zordo.models.camera.CameraComponent;
import zordo.models.gamePad.ControllerComponent;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class PauseMenu implements Screen {

    int selection;
    final LegendOfZordo game;
    private final Texture backgroundTexture;
    private SpriteBatch batch;
    BitmapFont font;

    OrthographicCamera camera;

    HashMap<String, Component> components;

    public PauseMenu(LegendOfZordo game) {
        this.game = game;

        components = new HashMap<>();
        components.put("Camera", new CameraComponent());

        TextureRegion background = new TextureRegion();
        backgroundTexture = new Texture("environment/background_32.png");
        background.setTexture(backgroundTexture);

        selection = 0;

        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        BitmapFont font = new BitmapFont();
        Gdx.gl20.glClearColor(0, 0, 0.2f, 0.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        CameraComponent tempCam = (CameraComponent) components.get("Camera");
        this.camera = tempCam.getCamera();

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundTexture,0,0,camera.viewportWidth,camera.viewportHeight);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            float x_ls_value = ControllerComponent.LEFT_STICK_X.getAxisValue();
            float y_ls_value = ControllerComponent.LEFT_STICK_Y.getAxisValue();
            float x_rs_value = ControllerComponent.RIGHT_STICK_X.getAxisValue();
            float y_rs_value = ControllerComponent.RIGHT_STICK_Y.getAxisValue();
            if(selection >= 0) {
                if (y_ls_value > 0 || x_ls_value > 0 || x_rs_value > 0 || y_rs_value > 0 ||ControllerComponent.D_PAD_DOWN.isPressed() || ControllerComponent.D_PAD_RIGHT.isPressed() || Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    selection += 1;
                    if(selection >= 3) {
                        selection = 2;
                    }
                }
                else if (y_ls_value < 0 || x_ls_value < 0 || x_rs_value < 0 || y_rs_value < 0 ||ControllerComponent.D_PAD_UP.isPressed() || ControllerComponent.D_PAD_LEFT.isPressed() || Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    selection -= 1;
                    if(selection <= 0) {
                        selection = 0;
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int listX = 100;
        int listY = (int) camera.viewportHeight - 100;
        font.getData().setScale(2);
        if (selection == 0) {
            font.draw(batch, " > Exit Game", listX, listY);
        } else if (selection == 1) {
            font.draw(batch, " > Back to Level Selection", listX, listY);
        }  else if (selection == 2) {
            font.draw(batch, " > Back to Level", listX, listY);
        }

        if(ControllerComponent.START_BUTTON.isPressed() || Gdx.input.isKeyPressed(Input.Keys.ENTER) || ControllerComponent.A_BUTTON.isPressed()) {
            if (selection == 0) {
                Gdx.app.exit();
            } else if (selection == 1) {
                game.setScreen(new LevelMenu(game));
            }  else if(selection == 2) {
                game.activeLevel.paused = false;
                game.setScreen(game.activeLevel);
            }
            ControllerComponent.START_BUTTON.release();
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
