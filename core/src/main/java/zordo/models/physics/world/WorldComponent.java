package zordo.models.physics.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import zordo.models.debug.DebugCollision;
import zordo.models.physics.terrain.surfaces.LevelBoundaryComponent;
import zordo.models.physics.terrain.surfaces.PlatformComponent;

import java.util.ArrayList;
import java.util.Random;

public class WorldComponent {
    public ArrayList<PlatformComponent> platforms;
    public DebugCollision platformIntersection;

    public LevelBoundaryComponent ceiling;
    public LevelBoundaryComponent floor;
    public LevelBoundaryComponent leftWall;
    public LevelBoundaryComponent rightWall;

    int platformCount;

    private World world;

//    BodyDef bodyDef;
//    Body body;
//    CircleShape circle;
//    FixtureDef fixtureDef;

    BodyDef boundaryBodyDef;
    PolygonShape boundaryBox;
    public Body boundaryBody;

//    BodyDef characterBodyDef;
//    Body characterBody;
//    PolygonShape characterShape;
//    FixtureDef characterFixtureDef;

    private ArrayList<DebugCollision> debugCollisions;

    public WorldComponent() {
        this.world = new World(new Vector2(0, -1), true);
        platforms = new ArrayList<>();
        platformIntersection = new DebugCollision(this.world);

        floor = new LevelBoundaryComponent(false, false, true,this);
        floor.setHeight(100);
        floor.setWidth(5000);
        floor.setCoordinates(0, 0);
        boundaryBodyDef = new BodyDef();
        boundaryBodyDef.position.set(floor.getX(), floor.getY());
        boundaryBody = world.createBody(boundaryBodyDef);
        boundaryBox = new PolygonShape();
        boundaryBox.setAsBox(floor.getWidth(), floor.getHeight());
        boundaryBody.createFixture(boundaryBox,0);
        floor.setPlatformBodyDef(boundaryBodyDef);

        boundaryBox.dispose();

        ceiling = new LevelBoundaryComponent(false,true,false,this);
        ceiling.setHeight(100);
        ceiling.setWidth(5000);

        ceiling.setCoordinates(0, 1000);
        // Create our body definition
        boundaryBodyDef = new BodyDef();
// Set its world position
        boundaryBodyDef.position.set(ceiling.getX(), ceiling.getY());

// Create a body from the definition and add it to the world
        boundaryBody = world.createBody(boundaryBodyDef);

// Create a polygon shape
        boundaryBox = new PolygonShape();
// Set the polygon shape as a box which is twice the size of our view port and 20 high
// (setAsBox takes half-width and half-height as arguments)
        boundaryBox.setAsBox(ceiling.getWidth(), ceiling.getHeight());
// Create a fixture from our polygon shape and add it to our ground body
        boundaryBody.createFixture(boundaryBox, 0.0f);
// Clean up after ourselves
        boundaryBox.dispose();
        ceiling.setPlatformBodyDef(boundaryBodyDef);

        leftWall = new LevelBoundaryComponent(true,false,false,this);
        leftWall.setHeight(5000);
        leftWall.setWidth(100);

        leftWall.setCoordinates(0, 0);

        boundaryBodyDef = new BodyDef();
        boundaryBodyDef.position.set(leftWall.getX(), leftWall.getY());

        boundaryBody = world.createBody(boundaryBodyDef);
        boundaryBox = new PolygonShape();
        boundaryBox.setAsBox(leftWall.getWidth(), leftWall.getHeight());
        boundaryBody.createFixture(boundaryBox, 0.0f);
        boundaryBox.dispose();
        leftWall.setPlatformBodyDef(boundaryBodyDef);

        rightWall = new LevelBoundaryComponent(true,false,false,this);
        rightWall.setHeight(5000);
        rightWall.setWidth(100);
        rightWall.setCoordinates(5000, 0);

        boundaryBodyDef = new BodyDef();
        boundaryBodyDef.position.set(rightWall.getX(), rightWall.getY());
        boundaryBody = world.createBody(boundaryBodyDef);
        boundaryBox = new PolygonShape();
        boundaryBox.setAsBox(rightWall.getWidth(), rightWall.getHeight());
        boundaryBody.createFixture(boundaryBox, 0.0f);
        boundaryBox.dispose();
        rightWall.setPlatformBodyDef(boundaryBodyDef);

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
