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
import com.zordo.game.levels.A_First.LevelOne;
import com.zordo.game.levels.B_Second.LevelTwo;
import com.zordo.game.levels.C_Third.LevelThree;
import com.zordo.game.levels.intro.LevelIntro;
import com.zordo.game.menus.Objects.LevelItem;

import java.util.ArrayList;

public class LevelSelect implements Screen {
    private SpriteBatch batch;
    private final Texture backgroundTexture;

    OrthographicCamera camera;
    final LegendOfZordo game;

    LevelItem intro;
    LevelItem levelOne;
    LevelItem levelTwo;
    LevelItem levelThree;
    ArrayList<LevelItem> levels = new ArrayList<>();

    public LevelSelect(final LegendOfZordo game) {
        this.game = game;

        boolean defaultSelection = true;
        Screen LevelIntro = new LevelIntro(game);
        Screen LevelOne = new LevelOne(game);
        Screen LevelTwo = new LevelTwo(game);
        Screen LevelThree = new LevelThree(game);

        intro = new LevelItem(LevelIntro, defaultSelection);
        levelOne = new LevelItem(LevelOne);
        levelTwo = new LevelItem(LevelTwo);
        levelThree = new LevelItem(LevelThree);

        intro.setText("Intro");
        intro.setEnabled(true);

        levelOne.setText("Level One");
        levelOne.setEnabled(true);

        levelTwo.setText("Level Two");
        levelTwo.setEnabled(true);

        levelThree.setText("Level Three");
        levelThree.setEnabled(false);

        levels.add(intro);
        levels.add(levelOne);
        levels.add(levelTwo);
        levels.add(levelThree);

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

        int listX = 100;
        int listY = 350;
        for(LevelItem level: levels) {
            font.draw(batch, level.getText(), listX, listY);
            listY -= 20;
        }

        font.draw(batch, "Test 123", 400, 400);

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
