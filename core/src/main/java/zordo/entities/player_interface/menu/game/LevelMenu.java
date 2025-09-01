package zordo.entities.player_interface.menu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import zordo.LegendOfZordo;

import zordo.components.Component;
import zordo.components.camera.CameraComponent;
import zordo.components.menu.LevelItem;
import zordo.components.world.levels.LevelComponent;

import java.util.HashMap;

public class LevelMenu implements Screen {

    final LegendOfZordo game;
    public LevelComponent loadLevel;
    private final Texture backgroundTexture;
    OrthographicCamera camera;

    SpriteBatch batch;

    HashMap<String, Component> components;
    public HashMap<String, LevelComponent> levels;
    public int selectedLevel = 1;
    int maxSelectable = 4;

    public LevelMenu(final LegendOfZordo game) {
        this.game = game;
        this.game.isOnLevelMenu = true;
        this.game.levelMenu = this;
        this.game.isOnDebugMenu = false;
        this.game.isOnTitleMenu = false;

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

        batch = new SpriteBatch();
        CameraComponent cam = (CameraComponent) components.get("Camera");
        camera = cam.getCamera();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        try {
            this.game.controllerListener.handleInput(Gdx.graphics.getDeltaTime(), this.game);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int listX = 100;
        int listY = Gdx.graphics.getHeight() - 100;
        for (int i = 1; i < levels.size(); i ++) {
            font.getData().setScale(5);
            LevelItem levelItem = levels.get("Level " + i);
            if (levelItem.getIsSelected()) {
                font.draw(batch, " > " + levelItem.getName(), listX, listY);
            } else {
                font.draw(batch, levelItem.getName(), listX, listY);
            }
            listY -= 100;
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

    public void setSelectedLevel(int selectedLevel) {
        this.selectedLevel = selectedLevel;
    }
}
