package zordo.game.entities.characters.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import zordo.game.models.gamePad.ControllerComponent;
import zordo.game.entities.characters.Character;
import zordo.game.models.physics.BodyLoader;
import zordo.game.models.physics.world.WorldComponent;

import java.util.HashMap;

public class Player extends Character implements ContactListener {
    public ControllerComponent playerController;

    public BodyDef characterBodyDef;
    public HashMap<String, Body> playerBodies;
    public Body characterBody;
    public PolygonShape characterShape;
    public PolygonShape colliderShape;

    public FixtureDef characterFixtureDef;

    Vector2 position;

    public Player(WorldComponent world) {
        super(world);
        colliderShape = new PolygonShape();
        FixtureDef collisionSensor = new FixtureDef();
        collisionSensor.isSensor = true;

        this.position = new Vector2();

        playerBodies = new HashMap<>();
        playerController = new ControllerComponent();
        BodyLoader.load(Gdx.files.internal("physics/character/player/playerBodies.json"));
        characterBodyDef = new BodyDef();
        characterBodyDef.type = BodyDef.BodyType.DynamicBody;
        characterBodyDef.position.set(super.getCharacterComponent().getPosition().x - 5, super.getCharacterComponent().getPosition().y -5);
        characterBodyDef.fixedRotation = true;

        characterBody = world.getWorld().createBody(characterBodyDef);

        characterFixtureDef = new FixtureDef();

        collisionSensor.shape = colliderShape;

        characterBody.createFixture(collisionSensor);

        characterFixtureDef.shape = characterShape;
        characterFixtureDef.density = 0.5f;
        characterFixtureDef.friction = 1f;
        characterFixtureDef.restitution = 0.01f;

        characterBody.isFixedRotation();

        BodyLoader.attachFixture(characterBody, "standing", characterFixtureDef, 1.5f);
        characterBody.setUserData(this);
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

    @Override
    public void beginContact(Contact contact) {
        this.getCharacterComponent().setIsAirborne(false);
        if (contact.getFixtureA().getBody().getPosition().y < this.characterBody.getPosition().y) {
            this.characterBody.setLinearVelocity(characterBody.getLinearVelocity().x, 0f);
        } else if (contact.getFixtureB().getBody().getPosition().y < this.characterBody.getPosition().y) {
            this.characterBody.setLinearVelocity(characterBody.getLinearVelocity().x, 0f);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
