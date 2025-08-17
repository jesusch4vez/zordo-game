package com.zordo.entity_component_system.entity.level;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zordo.LegendOfZordo;
import com.zordo.entity_component_system.component.Component;
import com.zordo.entity_component_system.component.camera.Camera;
import com.zordo.entity_component_system.component.physics.surfaces.Platform;

import java.util.HashMap;

public class Level implements Screen {
    final LegendOfZordo game;
    private String levelName;
    protected Platform slat;

    HashMap<String, Component> components;


    public Level(final LegendOfZordo game) {
        this.game = game;
        this.levelName = "";

        components = new HashMap<>();

        components.put("Camera", new Camera());
        components.put("Platform", new Platform());

        this.slat = (Platform) components.get("Platform");
        this.slat.getPlatform().setWidth(1920);
        this.slat.setCoordinates(0, 0);

        ScreenUtils.clear(0, 0, 0.2f, 1);
        TextureRegion background = new TextureRegion();
        Texture backgroundTexture = new Texture("environment/background_32.png");
        background.setTexture(backgroundTexture);
    }

    public Level(final LegendOfZordo game, String levelName) {
        this.game = game;
        this.levelName = levelName;

        components = new HashMap<>();

        components.put("Camera", new Camera());
        components.put("Platform", new Platform());

        this.slat = (Platform) components.get("Platform");
        this.slat.getPlatform().setWidth(1920);
        this.slat.setCoordinates(0, 0);

        ScreenUtils.clear(0, 0, 0.2f, 1);
        TextureRegion background = new TextureRegion();
        Texture backgroundTexture = new Texture("environment/background_32.png");
        background.setTexture(backgroundTexture);
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelName() {
        return this.levelName;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

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