package com.zordo.game.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zordo.game.LegendOfZordo;
import com.zordo.game.menus.Objects.LevelItem;

public class LevelSelect implements Screen {
    private SpriteBatch batch;
    private final Texture backgroundTexture;

    OrthographicCamera camera;
    final LegendOfZordo game;

    LevelItem intro;
    LevelItem levelOne;
    LevelItem levelTwo;
    LevelItem levelThree;

    public LevelSelect(final LegendOfZordo game) {
        this.game = game;

        intro = new LevelItem();
        levelOne = new LevelItem();
        levelTwo = new LevelItem();
        levelThree = new LevelItem();

        intro.setText("Intro");
        intro.setEnabled(true);
        intro.setLevel(0);

        levelOne.setText("Level One");
        levelOne.setEnabled(true);
        levelOne.setLevel(1);

        levelTwo.setText("Level Two");
        levelTwo.setEnabled(true);
        levelTwo.setLevel(2);

        levelThree.setText("Level Three");
        levelThree.setEnabled(false);
        levelThree.setLevel(3);

        ScreenUtils.clear(0, 0, 0.2f, 1);

        TextureRegion background = new TextureRegion();
        backgroundTexture = new Texture("background_32.png");
        background.setTexture(backgroundTexture);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800,400);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        BitmapFont font = new BitmapFont();
        Gdx.gl20.glClearColor(0, 0, 0.2f, 0.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundTexture,0,0,1920,1080);

        font.draw(batch, "Test 123", 400, 200);



        batch.end();
        camera.update();
    }

    @Override
    public void resize(int i, int i1) {

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
        batch.dispose();
    }
}
