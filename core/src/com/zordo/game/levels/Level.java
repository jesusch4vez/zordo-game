package com.zordo.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zordo.camera.CamController;
import com.zordo.game.LegendOfZordo;
import com.zordo.game.characters.player.linko.Linko;
import com.zordo.game.platforms.Platform;

public class Level implements Screen {
    private SpriteBatch batch;
    private final Texture backgroundTexture;
    private final CamController cameraController;
    OrthographicCamera camera;

    final LegendOfZordo game;
    Linko linko;

    private final Platform slat;
    boolean paused;

    public Level(final LegendOfZordo game) {
        this.game = game;
        this.paused = false;
        cameraController = new CamController();

        this.slat = new Platform();
        this.slat.setCoordinates(50, 50);

        ScreenUtils.clear(0, 0, 0.2f, 1);

        TextureRegion background = new TextureRegion();
        backgroundTexture = new Texture("background_32.png");
        background.setTexture(backgroundTexture);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800,400);

        linko = new Linko(true);
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
    }

    @Override
    public void render(float delta) {

        Gdx.gl20.glClearColor(0, 0, 0.2f, 0.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, 1920, 1080);

        this.slat.render(batch);

        if (this.paused || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            this.paused = true;
            this.pause();
        }

        if (!this.paused) {
            linko.move(batch);
            cameraController.handleInput(linko, camera);

            linko.collisionWithPlatform();
            if (linko.getLinkoCollider().overlaps(this.slat)) {
                linko.setLinkoCollider(this.linko.getLinkoCollider().getX(), this.slat.getY() + slat.getHeight());
            }
            if (linko.health <= 0) {
                BitmapFont font = new BitmapFont();
                font.draw(batch, "GAME OVER", 400, 200);
            }
        }
        batch.end();
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

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
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        batch.dispose();
    }
}
