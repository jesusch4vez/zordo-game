package zordo.models.physics.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import zordo.models.physics.terrain.surfaces.LevelBoundaryComponent;
import zordo.models.physics.terrain.surfaces.PlatformComponent;

import java.util.ArrayList;

public class WorldComponent {
    public ArrayList<PlatformComponent> platforms;

    public LevelBoundaryComponent ceiling;
    public LevelBoundaryComponent floor;
    public LevelBoundaryComponent leftWall;
    public LevelBoundaryComponent rightWall;

    private World world;

    BodyDef boundaryBodyDef;
    FixtureDef collisionSensor;
    PolygonShape boundaryBox;
    public Body boundaryBody;

    public WorldComponent() {
        this.world = new World(new Vector2(0, -1), true);
        platforms = new ArrayList<>();

        // Floor platform
        floor = new LevelBoundaryComponent();
        floor.setHeight(50);
        floor.setWidth(1920);

        boundaryBodyDef = new BodyDef();
        collisionSensor = new FixtureDef();

        floor.setCoordinates(0, 0);
        boundaryBodyDef.position.set(floor.getWidth()/2, floor.getHeight()/2);

        boundaryBody = world.createBody(boundaryBodyDef);
        boundaryBox = new PolygonShape();
        boundaryBox.setAsBox(floor.getWidth()/2, floor.getHeight()/2);

        collisionSensor.shape = boundaryBox;
        collisionSensor.isSensor = true;

        boundaryBody.createFixture(boundaryBox,0);
        boundaryBody.createFixture(collisionSensor);
        boundaryBody.setUserData(floor);
        floor.setPlatformBodyDef(boundaryBodyDef);

        boundaryBox.dispose();

        // Ceiling platform
        ceiling =  new LevelBoundaryComponent();
        ceiling.setHeight(50);
        ceiling.setWidth(1920);

        boundaryBodyDef = new BodyDef();
        ceiling.setCoordinates(0, 1080);
        boundaryBodyDef.position.set(ceiling.getWidth()/2, ceiling.getY() - ceiling.getHeight()/2);

        boundaryBody = world.createBody(boundaryBodyDef);

        boundaryBox = new PolygonShape();
        boundaryBox.setAsBox(ceiling.getWidth()/2, ceiling.getHeight()/2);
        boundaryBody.createFixture(boundaryBox, 0.0f);

        collisionSensor.shape = boundaryBox;
        collisionSensor.isSensor = true;

        boundaryBody.createFixture(boundaryBox,0);
        boundaryBody.createFixture(collisionSensor);

        ceiling.setPlatformBodyDef(boundaryBodyDef);
        boundaryBody.setUserData(ceiling);

        boundaryBox.dispose();

        // Left wall platform
        leftWall =  new LevelBoundaryComponent();
        leftWall.setHeight(1080);
        leftWall.setWidth(50);

        boundaryBodyDef = new BodyDef();

        leftWall.setCoordinates(0, 0);
        boundaryBodyDef.position.set(leftWall.getWidth()/2, leftWall.getY() + leftWall.getHeight()/2);

        boundaryBody = world.createBody(boundaryBodyDef);
        boundaryBox = new PolygonShape();
        boundaryBox.setAsBox(leftWall.getWidth()/2, leftWall.getHeight()/2);


        collisionSensor.shape = boundaryBox;
        collisionSensor.isSensor = true;

        boundaryBody.createFixture(boundaryBox,0);
        boundaryBody.createFixture(collisionSensor);

        leftWall.setPlatformBodyDef(boundaryBodyDef);
        boundaryBody.setUserData(leftWall);

        boundaryBox.dispose();

        // Right wall platform
        rightWall = new LevelBoundaryComponent();
        rightWall.setHeight(1080);
        rightWall.setWidth(50);

        boundaryBodyDef = new BodyDef();

        rightWall.setCoordinates(1920, 0);
        boundaryBodyDef.position.set(rightWall.getX() - rightWall.getWidth()/2, rightWall.getHeight()/2);
        boundaryBody = world.createBody(boundaryBodyDef);
        boundaryBox = new PolygonShape();
        boundaryBox.setAsBox(rightWall.getWidth()/2, rightWall.getHeight()/2);

        collisionSensor.shape = boundaryBox;
        collisionSensor.isSensor = true;

        boundaryBody.createFixture(boundaryBox,0);
        boundaryBody.createFixture(collisionSensor);

        rightWall.setPlatformBodyDef(boundaryBodyDef);
        boundaryBody.setUserData(rightWall);

        boundaryBox.dispose();
    }

    public WorldComponent(World world) {
        this.world = world;
    }

    public World getWorld() {
        return this.world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
