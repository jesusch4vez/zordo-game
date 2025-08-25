package zordo.components.physics.terrain.surfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import zordo.components.Component;

public class PlatformComponent extends Component {

    private Texture platformTexture;
    private Rectangle platform;
    private Boolean holdsCharacter;
    private Boolean isJumpable;
    private String characterRelativePosition;

    public PlatformComponent() {
        super();
        this.platform = new Rectangle();
        platform.setWidth(100);
        platform.setHeight(10);
        platformTexture = new Texture("environment/platform-square.png");
        holdsCharacter = false;
        isJumpable = false;
        characterRelativePosition = "";
    }

    public PlatformComponent(int height, int width) {
        super();
        this.platform = new Rectangle();
        platform.setWidth(width);
        platform.setHeight(height);
        platformTexture = new Texture("environment/platform-square.png");
        holdsCharacter = false;
        isJumpable = false;
        characterRelativePosition = "";
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

    public Boolean getHoldsCharacter() {
        return this.holdsCharacter;
    }

    public void setHoldsCharacter(Boolean holdsCharacter) {}

    public void setWidth(int width) {
        platform.setWidth(width);
    }

    public float getWidth() {
        return platform.getWidth();
    }

    public void setHeight(int height) {
        platform.setHeight(height);
    }

    public float getHeight() {
        return platform.getHeight();
    }

    public void setIsJumpable(Boolean isJumpable) {
        this.isJumpable = isJumpable;
    }

    public Boolean getIsJumpable() {
        return isJumpable;
    }

    public void setCharacterRelativePosition(String characterRelativePosition) {
        this.characterRelativePosition = characterRelativePosition;
    }
    public String getCharacterRelativePosition() {
        return characterRelativePosition;
    }
}
