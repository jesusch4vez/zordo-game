package zordo.models.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import zordo.models.animation.character.AnimationComponent;
import zordo.models.health.HeartComponent;
import zordo.models.physics.BodyEditorLoader;
import zordo.models.physics.world.WorldComponent;

import java.util.ArrayList;

public class CharacterComponent implements ContactListener {
    Boolean isFlippedRight;
    Boolean isJumping;
    Boolean isStepping;
    Boolean isRunning;
    Boolean isAirborne;
    Boolean isStanding;

    public Vector2 dimensions;

    Boolean isDucking;

    int jumps;

    Vector2 position;

    public ArrayList<HeartComponent> hearts;
    public int health;
    AnimationComponent animation;

    public BodyDef characterBodyDef;
    public Body characterBody;
    public PolygonShape characterShape;
    public FixtureDef characterFixtureDef;

    public BodyEditorLoader bodyEditorLoader;

    public CharacterComponent(WorldComponent world) {
        bodyEditorLoader = new BodyEditorLoader(Gdx.files.internal("physics/character/player/bodyEditorProject.json"));
        jumps = 0;
        position = new Vector2();
        position.x = 60;
        position.y = 60;

        isFlippedRight = true;
        isJumping = false;
        isStepping = false;
        isRunning = false;
        isAirborne = false;
        isStanding = true;
        isDucking = false;

        health = 8;

        hearts = new ArrayList<>(health);
        for(int i = 0; i < health; i++) {
            hearts.add(new HeartComponent());
        }
        animation = new AnimationComponent();

        characterBodyDef = new BodyDef();
        characterBodyDef.type = BodyDef.BodyType.DynamicBody;
        characterBodyDef.position.set(this.getPosition().x - 5, this.getPosition().y -5);
        characterBodyDef.fixedRotation = true;

        characterBody = world.getWorld().createBody(characterBodyDef);

        characterFixtureDef = new FixtureDef();

        characterFixtureDef.shape = characterShape;
        characterFixtureDef.density = 0.5f;
        characterFixtureDef.friction = 1f;
        characterFixtureDef.restitution = 0.01f;

        characterBody.isFixedRotation();
        characterBody.setUserData(this);

        bodyEditorLoader.attachFixture(characterBody, "link-standing-rigid", characterFixtureDef);
    }

    public int getHealth() {
        return this.health;
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

    public void setIsFlippedRight(Boolean flip) {
        this.isFlippedRight = flip;
    };

    public Boolean getIsFlippedRight() {
        return this.isFlippedRight;
    };

    public void setIsJumping(Boolean isJumping) {
        this.isJumping = isJumping;
    };

    public Boolean getIsJumping() {
        return this.isJumping;
    };

    public ArrayList<HeartComponent> getHearts() {
        return this.hearts;
    }

    public void setAnimation(AnimationComponent animation) {
        this.animation = animation;
    }

    public AnimationComponent getAnimation() {
        return this.animation;
    }

    public Boolean getIsStepping() {
        return isStepping;
    }

    public void setIsStepping(Boolean stepping) {
        this.isStepping = stepping;
    }

    public void setJumps(int jumps) {
        this.jumps = jumps;
    }

    public int getJumps() {
        return this.jumps;
    }

    public void setIsRunning(Boolean isRunning) {
        this.isRunning = isRunning;
    }

    public Boolean getIsRunning() {
        return this.isRunning;
    }

    public void setIsAirborne(Boolean isAirborne) {
        this.isAirborne = isAirborne;
    }

    public Boolean getIsAirborne() {
        return this.isAirborne;
    }

    public PolygonShape getCharacterShape() {
        return characterShape;
    }

    public void setCharacterShape(Vector2 [] characterShape) {
        this.getCharacterShape().set(characterShape);
    }

    public Body getCharacterBody() {
        return this.characterBody;
    }

    public void setCharacterBody(Body characterBody) {
        this.characterBody = characterBody;
    }

    public void setIsStanding(Boolean isStanding) {
        this.isStanding = isStanding;
    }

    public Boolean getIsStanding() {
        return this.isStanding;
    }

    public Boolean getIsDucking() { return  isDucking; }

    public void setIsDucking(Boolean isDucking) {
        this.isDucking = isDucking;
    }

    @Override
    public void beginContact(Contact contact) {
        Gdx.app.log("Contact", "beginContact with: " + contact);
    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("Contact", "endContact with: " + contact);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
