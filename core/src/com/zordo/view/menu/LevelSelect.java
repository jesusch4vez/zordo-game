package com.zordo.view.menu;

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
import com.zordo.LegendOfZordo;
import com.zordo.view.levels.A_First.LevelOne;
import com.zordo.view.levels.B_Second.LevelTwo;
import com.zordo.view.levels.C_Third.LevelThree;
import com.zordo.view.levels.intro.LevelIntro;
import com.zordo.model.level.LevelItem;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
    int selectedLevel = 0;

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
        levelOne.setText("Level One");
        levelTwo.setText("Level Two");
        levelThree.setText("Level Three");

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

        try {
            if( selectedLevel >= 0 && selectedLevel <= 3) {
                if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && selectedLevel < 3) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    levels.get(selectedLevel).setSelected(false);
                    selectedLevel += 1;
                    levels.get(selectedLevel).setSelected(true);
                } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && selectedLevel > 0) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    levels.get(selectedLevel).setSelected(false);
                    selectedLevel -= 1;
                    levels.get(selectedLevel).setSelected(true);
                } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)  && selectedLevel > 0) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    levels.get(selectedLevel).setSelected(false);
                    selectedLevel -= 1;
                    levels.get(selectedLevel).setSelected(true);
                } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)  && selectedLevel < 3) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    levels.get(selectedLevel).setSelected(false);
                    selectedLevel += 1;
                    levels.get(selectedLevel).setSelected(true);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int listX = 100;
        int listY = 350;
        for (LevelItem levelItem : levels) {
            if (levelItem.isSelected()) {
                font.draw(batch, " > " + levelItem.getText(), listX, listY);
            } else {
                font.draw(batch, levelItem.getText(), listX, listY);
            }
            listY -= 20;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            this.game.setScreen(levels.get(selectedLevel).getLevel());
        }

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
