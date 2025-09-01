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

    BodyDef bodyDef;
    Body body;

    private ArrayList<DebugCollision> debugCollisions;

    public WorldComponent() {
        this.world = new World(new Vector2(0, -10), true);
        platforms = new ArrayList<>();
        platformIntersection = new DebugCollision();
        platformCount = 100;

        for(int i = 1; i <= platformCount; i++) {
            Random random = new Random();
            int min = 1; // Minimum value (inclusive)
            int max = 1920*3; // Maximum value (inclusive)
            int randx = random.nextInt(max - min + 1) + min;
            int randy = random.nextInt(max - min + 1) + min;
            PlatformComponent platform = new PlatformComponent();
            if(i % 2 == 0) {
                platform.setCoordinates(randx, randy);
                platform.setHeight(200);
                platform.setWidth(100);
            } else {
                platform.setCoordinates(randx, randy);
                platform.setHeight(100);
                platform.setWidth(200);
            }
            platforms.add(platform);
        }

        floor = new LevelBoundaryComponent(false, false, true);
        ceiling = new LevelBoundaryComponent(false,true,false);
        leftWall = new LevelBoundaryComponent(true,false,false);
        rightWall = new LevelBoundaryComponent(true,false,false);

        // First we create a body definition
        bodyDef = new BodyDef();
// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
// Set our body's starting position in the world
        bodyDef.position.set(5, 10);

// Create our body in the world using our body definition
        body = world.createBody(bodyDef);

// Create a circle shape and set its radius to 6
        CircleShape circle = new CircleShape();
        circle.setRadius(6f);

// Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit

// Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);

// Remember to dispose of any shapes after you're done with them!
// BodyDef and FixtureDef don't need disposing, but shapes do.
//        circle.dispose();

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
