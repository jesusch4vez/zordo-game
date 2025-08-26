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
import com.badlogic.gdx.utils.ScreenUtils;
import zordo.LegendOfZordo;

import java.util.concurrent.TimeUnit;

import zordo.components.Component;
import zordo.components.camera.CameraComponent;
import zordo.components.menu.LevelItem;
import zordo.components.world.levels.LevelComponent;
import zordo.systems.menu.MenuSystem;

import java.util.HashMap;

public class LevelMenu implements Screen {
    final LegendOfZordo game;
    LevelComponent loadLevel;
    private final Texture backgroundTexture;
    OrthographicCamera camera;

    HashMap<String, Component> components;
    HashMap<String, LevelComponent> levels;
    int selectedLevel = 1;
    int maxSelectable = 4;

    public LevelMenu(final LegendOfZordo game) {
        this.game = game;
        int levelCount = 10;
        loadLevel = new LevelComponent();
        components = new HashMap<>();
        components.put("Camera", new CameraComponent());

        levels = new HashMap<>();
        for(int i = 1; i <= levelCount; i++) {
            LevelComponent level = new LevelComponent();
            level.setName("Level " + i);
            if( i % 3 == 0) {
                level.setLevelSize(game.small);
            } else if ( i % 2 == 0) {
                level.setLevelSize(game.large);
            } else {
                level.setLevelSize(game.medium);
            }

            levels.put(level.getName(), level);
            if(i < maxSelectable) {
                levels.get("Level " + i).setIsSelectable(true);
            }
        }
        levels.get("Level " + selectedLevel).setIsSelected(true);
        components.putAll(levels);


        ScreenUtils.clear(0, 0, 0.2f, 1);

        TextureRegion background = new TextureRegion();
        backgroundTexture = new Texture("environment/background_32.png");
        background.setTexture(backgroundTexture);

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
    public void render(float v) {
        BitmapFont font = new BitmapFont();
        Gdx.gl20.glClearColor(0, 0, 0.2f, 0.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        SpriteBatch batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundTexture,0,0,1920,1080);

        try {
            if( selectedLevel >= 1 && selectedLevel < levels.size() ) {
                if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && selectedLevel < levels.size() - 1) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    levels.get("Level " + selectedLevel).setIsSelected(false);
                    selectedLevel += 1;
                    levels.get("Level " + selectedLevel).setIsSelected(true);
                    loadLevel = levels.get("Level " + selectedLevel);
                }
                else if ( ( Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.LEFT) ) && selectedLevel > 0) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    levels.get("Level " + selectedLevel).setIsSelected(false);
                    selectedLevel -= 1;
                    if(selectedLevel <= 1) {
                        selectedLevel = 1;
                    }
                    levels.get("Level " + selectedLevel).setIsSelected(true);
                    loadLevel = levels.get("Level " + selectedLevel);
                } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)  && selectedLevel < levels.size() - 1) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    levels.get("Level " + selectedLevel).setIsSelected(false);
                    selectedLevel += 1;
                    levels.get("Level " + selectedLevel).setIsSelected(true);
                    loadLevel = levels.get("Level " + selectedLevel);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int listX = 100;
        int listY = 350;
        for (int i = 1; i < levels.size(); i ++) {
            LevelItem levelItem = levels.get("Level " + i);
            if (levelItem.getIsSelected()) {
                font.draw(batch, " > " + levelItem.getName(), listX, listY);
            } else {
                font.draw(batch, levelItem.getName(), listX, listY);
            }
            listY -= 20;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            MenuSystem.debugModeMenu(this.game, levels.get("Level " + selectedLevel));
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

    }
}
