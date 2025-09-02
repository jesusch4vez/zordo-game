package zordo.models.physics.terrain.surfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import zordo.models.Component;
import zordo.models.physics.world.WorldComponent;

public class PlatformComponent extends Component {

    private Texture platformTexture;
    private Rectangle platform;
    private String characterRelativePosition;

    BodyDef platformBodyDef;
    PolygonShape platformBox;
    Body platformBody;

    public PlatformComponent(World world) {
        super();
        this.platform = new Rectangle();
        platform.setWidth(10);
        platform.setHeight(1);
        platformTexture = new Texture("environment/platform-square.png");

        characterRelativePosition = "";

        platformBodyDef = new BodyDef();
        platformBodyDef.position.set(this.platform.getX(), this.platform.getY());

        platformBody = world.createBody(platformBodyDef);

        platformBox = new PolygonShape();
        platformBox.setAsBox(platform.getWidth(), platform.getHeight());
        platformBody.createFixture(platformBox, 0.0f);
        platformBox.dispose();

        platformBody.setUserData(this);
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
