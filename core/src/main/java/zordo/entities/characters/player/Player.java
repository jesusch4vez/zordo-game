package zordo.entities.characters.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import zordo.models.gamePad.ControllerComponent;
import zordo.entities.characters.Character;
import zordo.models.physics.BodyLoader;
import zordo.models.physics.world.WorldComponent;

import java.util.HashMap;

public class Player extends Character {
    public ControllerComponent playerController;
    public BodyLoader bodyLoader;

    public BodyDef characterBodyDef;
    public HashMap<String, Body> playerBodies;
    public Body characterBody;
    public PolygonShape characterShape;
    public FixtureDef characterFixtureDef;

    int jumps;

    Vector2 position;

    public Player(WorldComponent world) {
        super(world);
        playerBodies = new HashMap<>();
        playerController = new ControllerComponent();
        BodyLoader.load(Gdx.files.internal("physics/character/player/playerBodies.json"));
        characterBodyDef = new BodyDef();
        characterBodyDef.type = BodyDef.BodyType.DynamicBody;
        characterBodyDef.position.set(super.getCharacterComponent().getPosition().x - 5, super.getCharacterComponent().getPosition().y -5);
        characterBodyDef.fixedRotation = true;

        characterBody = world.getWorld().createBody(characterBodyDef);

        characterFixtureDef = new FixtureDef();

        characterFixtureDef.shape = characterShape;
        characterFixtureDef.density = 0.5f;
        characterFixtureDef.friction = 1f;
        characterFixtureDef.restitution = 0.01f;

        characterBody.isFixedRotation();
        characterBody.setUserData(this);

        bodyLoader.attachFixture(characterBody, "standing", characterFixtureDef);
    }

    public PolygonShape getCharacterShape() {
        return characterShape;
    }

    public void setCharacterShape(Vector2[] characterShape) {
        this.getCharacterShape().set(characterShape);
    }

    public Body getCharacterBody() {
        return this.characterBody;
    }

    public void setCharacterBody(Body characterBody) {
        this.characterBody = characterBody;
    }


    public Vector2 getPosition() {
        return this.position;
    }

    public void setPosition(Vector3 position) {
        this.position.x = position.x;
        this.position.y = position.y;
        this.characterBody.getPosition().set(position.x, position.y);
    }

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
        this.characterBody.getPosition().set(position.x, position.y);
    }

    public void setX(float x) {
        this.position.x = x;
        this.characterBody.getPosition().x = x;
    }

    public void setY(float y) {
        this.position.y = y;
        this.characterBody.getPosition().y = y;
    }
}
