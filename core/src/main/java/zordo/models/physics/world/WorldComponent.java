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

    int platformCount;

    private World world;

    BodyDef boundaryBodyDef;
    PolygonShape boundaryBox;
    public Body boundaryBody;

    public WorldComponent() {
        this.world = new World(new Vector2(0, -1), true);
        platforms = new ArrayList<>();

        floor = new LevelBoundaryComponent(this);
        floor.setHeight(50);
        floor.setWidth(2500);
        floor.setCoordinates(0, 0);
        boundaryBodyDef = new BodyDef();
        boundaryBodyDef.position.set(floor.getX(), floor.getY());
        boundaryBody = world.createBody(boundaryBodyDef);
        boundaryBox = new PolygonShape();
        boundaryBox.setAsBox(floor.getWidth(), floor.getHeight());
        boundaryBody.createFixture(boundaryBox,0);
        boundaryBody.setUserData(floor);
        floor.setPlatformBodyDef(boundaryBodyDef);

        boundaryBox.dispose();

        ceiling =  new LevelBoundaryComponent(this);
        ceiling.setHeight(50);
        ceiling.setWidth(2500);

        ceiling.setCoordinates(0, 1080);
        boundaryBodyDef = new BodyDef();
        boundaryBodyDef.position.set(0, 1080);

        boundaryBody = world.createBody(boundaryBodyDef);

        boundaryBox = new PolygonShape();
        boundaryBox.setAsBox(ceiling.getWidth(), ceiling.getHeight());
        boundaryBody.createFixture(boundaryBox, 0.0f);
        boundaryBox.dispose();
        ceiling.setPlatformBodyDef(boundaryBodyDef);
        boundaryBody.setUserData(ceiling);

        leftWall =  new LevelBoundaryComponent(this);
        leftWall.setHeight(2500);
        leftWall.setWidth(50);

        leftWall.setCoordinates(0, 0);

        boundaryBodyDef = new BodyDef();
        boundaryBodyDef.position.set(leftWall.getX(), leftWall.getY());

        boundaryBody = world.createBody(boundaryBodyDef);
        boundaryBox = new PolygonShape();
        boundaryBox.setAsBox(leftWall.getWidth(), leftWall.getHeight());
        boundaryBody.createFixture(boundaryBox, 0.0f);
        boundaryBox.dispose();
        leftWall.setPlatformBodyDef(boundaryBodyDef);
        boundaryBody.setUserData(leftWall);

        rightWall = new LevelBoundaryComponent(this);
        rightWall.setHeight(2500);
        rightWall.setWidth(50);
        rightWall.setCoordinates(1920, 0);

        boundaryBodyDef = new BodyDef();
        boundaryBodyDef.position.set(rightWall.getX(), rightWall.getY());
        boundaryBody = world.createBody(boundaryBodyDef);
        boundaryBox = new PolygonShape();
        boundaryBox.setAsBox(rightWall.getWidth(), rightWall.getHeight());
        boundaryBody.createFixture(boundaryBox, 0.0f);
        boundaryBox.dispose();
        rightWall.setPlatformBodyDef(boundaryBodyDef);
        boundaryBody.setUserData(rightWall);

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
