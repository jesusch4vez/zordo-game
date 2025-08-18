package com.zordo.components.physics.terrain.surfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.zordo.components.Component;

public class PlatformComponent extends Component {

    private Texture platformTexture;
    private Rectangle platform;

    public PlatformComponent() {
        super();
        this.platform = new Rectangle();
        platform.setWidth(100);
        platform.setHeight(10);
        platformTexture = new Texture("environment/platform-square.png");
    }

    public PlatformComponent(int height, int width) {
        super();
        this.platform = new Rectangle();
        platform.setWidth(width);
        platform.setHeight(height);
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

    public Texture getPlatformTexture() {
        return this.platformTexture;
    }

    public void setPlatformTexture(Texture platformTexture) {
        this.platformTexture = platformTexture;
    }
}