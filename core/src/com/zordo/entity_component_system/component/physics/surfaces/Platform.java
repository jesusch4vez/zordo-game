package com.zordo.entity_component_system.component.physics.surfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.zordo.entity_component_system.component.Component;

public class Platform extends Component {

    private final Texture platformTexture;
    private Rectangle platform;

    public Platform() {
        super();
        this.platform = new Rectangle();
        platform.setWidth(100);
        platform.setHeight(10);
        platformTexture = new Texture("environment/platform-square.png");
    }

    public void setCoordinates(int x, int y) {
        platform.setX(x);
        platform.setY(y);
    }

    public float getX() {
        return platform.getX();
    }

    public float getY() {
        return platform.getY();
    }

    public Rectangle getPlatform() {
        return this.platform;
    }

    public void setPlatform(Rectangle platform) {
        this.platform = platform;
    }

    public void render(SpriteBatch batch) {
        batch.draw(platformTexture, getX(), getY(),platform.getWidth(),platform.getHeight());
    }
}