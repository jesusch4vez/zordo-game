package zordo.models.physics.terrain.surfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import zordo.models.Component;
import zordo.models.physics.world.WorldComponent;

public class PlatformComponent extends Component {

    private Texture platformTexture;
    private Rectangle platform;
    private Boolean holdsCharacter;
    private Boolean isJumpable;
    private String characterRelativePosition;

    BodyDef platformBodyDef;
    PolygonShape platformBox;
    Body platformBody;

    public PlatformComponent(World world) {
        super();
        this.platform = new Rectangle();
        platform.setWidth(100);
        platform.setHeight(10);
        platformTexture = new Texture("environment/platform-square.png");
        holdsCharacter = false;
        isJumpable = false;
        characterRelativePosition = "";

        // Create our body definition
        platformBodyDef = new BodyDef();
// Set its world position
        platformBodyDef.position.set(this.platform.getX(), this.platform.getY());

// Create a body from the definition and add it to the world
        platformBody = world.createBody(platformBodyDef);

// Create a polygon shape
        platformBox = new PolygonShape();
// Set the polygon shape as a box which is twice the size of our view port and 20 high
// (setAsBox takes half-width and half-height as arguments)
        platformBox.setAsBox(platform.getWidth(), platform.getHeight());
// Create a fixture from our polygon shape and add it to our ground body
        platformBody.createFixture(platformBox, 0.0f);
// Clean up after ourselves
        platformBox.dispose();

        platformBody.setUserData(this);
    }

    public PlatformComponent(float height, float width, WorldComponent world) {
        super();
        this.platform = new Rectangle();
        platform.setWidth(width);
        platform.setHeight(height);
        platformTexture = new Texture("environment/platform-square.png");
        holdsCharacter = false;
        isJumpable = false;
        characterRelativePosition = "";

        // Create our body definition
        platformBodyDef = new BodyDef();
// Set its world position
        platformBodyDef.position.set(this.platform.getX(), this.platform.getY());

// Create a body from the definition and add it to the world
        platformBody = world.getWorld().createBody(platformBodyDef);

// Create a polygon shape
        platformBox = new PolygonShape();
// Set the polygon shape as a box which is twice the size of our view port and 20 high
// (setAsBox takes half-width and half-height as arguments)
        platformBox.setAsBox(platform.getWidth(), platform.getHeight());
// Create a fixture from our polygon shape and add it to our ground body
        platformBody.createFixture(platformBox, 0.0f);
// Clean up after ourselves
        platformBox.dispose();
    }

    public void setCoordinates(float x, float y) {
        platform.setX(x);
        platform.setY(y);
        this.platformBodyDef.position.set(this.platform.getX(), this.platform.getY());
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
        platformBox.setAsBox(width,  this.platform.getHeight());
    }

    public float getWidth() {
        return platform.getWidth();
    }

    public void setHeight(int height) {
        platform.setHeight(height);
        platformBox.setAsBox(this.platform.getWidth(),  height);
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

    public Body getPlatformBody() {
        return platformBody;
    }

    public void setPlatformBody(Body platformBody) {
        this.platformBody = platformBody;
    }

    public BodyDef getPlatformBodyDef() {
        return platformBodyDef;
    }

    public void setPlatformBodyDef(BodyDef platformBodyDef) {
        this.platformBodyDef = platformBodyDef;
    }

    public PolygonShape getPlatformBox() {
        return platformBox;
    }

    public void setPlatformBox(PolygonShape platformBox) {
        this.platformBox = platformBox;
    }
}
