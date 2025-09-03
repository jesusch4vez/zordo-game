package zordo.models.physics.terrain.surfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import zordo.models.Component;
import zordo.models.physics.world.WorldComponent;

public class PlatformComponent extends Component {

    private Texture platformTexture;
    private Rectangle platform;
    private String characterRelativePosition;

    BodyDef platformBodyDef;
    FixtureDef collisionSensor;
    PolygonShape platformBox;
    Body platformBody;

    public PlatformComponent(WorldComponent world, float width, float height) {
        super();
        platformTexture = new Texture("environment/platform-square.png");

        // Physics platform
        platform = new Rectangle();
        platform.setHeight(height);
        platform.setWidth(width);

        platformBodyDef = new BodyDef();
        collisionSensor = new FixtureDef();

        platform.setPosition(0, 0);
        platformBodyDef.position.set(platform.getWidth()/2, platform.getHeight()/2);

        platformBody = world.getWorld().createBody(platformBodyDef);
        platformBox = new PolygonShape();
        platformBox.setAsBox(platform.getWidth()/2, platform.getHeight()/2);

        collisionSensor.shape = platformBox;
        collisionSensor.isSensor = true;

        platformBody.createFixture(platformBox,0);
        platformBody.createFixture(collisionSensor);
        platformBody.setUserData(platform);

        platformBox.dispose();
    }

    public void setCoordinates(float x, float y) {
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

    public FixtureDef getCollisionSensor() {
        return collisionSensor;
    }

    public void setCollisionSensor(FixtureDef platformFixtureDef) {
        this.collisionSensor = platformFixtureDef;
    }
}
