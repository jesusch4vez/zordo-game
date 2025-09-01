package zordo.models.character;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import zordo.models.animation.character.AnimationComponent;
import zordo.models.health.HeartComponent;
import zordo.models.physics.world.WorldComponent;

import java.util.ArrayList;

public class CharacterComponent {
    Boolean isFlippedRight;
    Boolean isJumping;
    Boolean isStepping;
    Boolean isRunning;
    Boolean isAirborne;
    Boolean isStanding;

    Boolean isColliding;
    Boolean onSurface;

    Boolean isAscending;
    Boolean isDescending;

    Boolean isDucking;

    int jumps;

    Rectangle collider;
    Vector3 position;

    Vector3 previousPosition;

    public ArrayList<HeartComponent> hearts;
    public int health;
    AnimationComponent animation;

    public BodyDef characterBodyDef;
    public Body characterBody;
    public PolygonShape characterShape;
    public FixtureDef characterFixtureDef;

    public CharacterComponent(WorldComponent world) {
        jumps = 0;
        position = new Vector3();
        previousPosition = new Vector3();
        collider = new Rectangle();
        collider.x = 1;
        collider.y = 1;
        collider.height = 5;
        collider.width = 2;
        position.x = collider.x;
        position.y = collider.y;
        previousPosition.x = collider.x;
        previousPosition.y = collider.y;

        isFlippedRight = true;
        isJumping = false;
        isStepping = false;
        isRunning = false;
        isAirborne = false;
        isColliding = false;
        isAscending = false;
        isDescending = false;
        isStanding = true;
        onSurface = false;
        isDucking = false;

        health = 8;

        hearts = new ArrayList<>(health);
        for(int i = 0; i < health; i++) {
            hearts.add(new HeartComponent());
        }
        animation = new AnimationComponent();

        characterBodyDef = new BodyDef();
        characterBodyDef.type = BodyDef.BodyType.DynamicBody;
        characterBodyDef.position.set(100, 200);
        characterBodyDef.fixedRotation = true;

        characterBody = world.getWorld().createBody(characterBodyDef);

        characterShape = new PolygonShape();
//        characterShape.setAsBox(5, 5.0f);
        Vector2 [] vertices = { new Vector2(collider.x, collider.y), new Vector2(collider.x, collider.y+collider.height), new Vector2(collider.x + collider.width, collider.y), new Vector2(collider.x+collider.width, collider.y + collider.height)};
        characterShape.set(vertices);

        characterFixtureDef = new FixtureDef();

        characterFixtureDef.shape = characterShape;
        characterFixtureDef.density = 2f;
        characterFixtureDef.friction = 0.2f;
        characterFixtureDef.restitution = 0.01f;

        characterBody.createFixture(characterFixtureDef);
        characterBody.setLinearVelocity(1.0f, 0.0f);
        characterBody.setUserData(this);
    }

    public int getHealth() {
        return this.health;
    }

    public void setCollider(Rectangle collider) {
        this.collider = collider;
    }

    public Rectangle getCollider() {
        return this.collider;
    }

    public Vector3 getPosition() {
        return this.position;
    }

    public void setPosition(Vector3 position) {
        this.collider.x = position.x;
        this.collider.y = position.y;
        this.position.x = collider.x;
        this.position.y = collider.y;
        this.characterBody.getPosition().set(position.x, position.y);
    }

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
        this.collider.x = position.x;
        this.collider.y = position.y;
        this.characterBody.getPosition().set(position.x, position.y);
    }

    public Vector3 getPreviousPosition() {
        return this.previousPosition;
    }

    public void setX(float x) {
        this.position.x = x;
        this.getCollider().setX(x);
        this.characterBody.getPosition().x = x;
    }

    public void setY(float y) {
        this.position.y = y;
        this.getCollider().setY(y);
        this.characterBody.getPosition().y = y;
    }

    public void setPreviousPosition(Vector3 previousPosition) {
        this.previousPosition = previousPosition;
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

    public void setIsColliding(Boolean isColliding) {
        this.isColliding = isColliding;
    }

    public Boolean getIsColliding() {
        return this.isColliding;
    }

    public void setIsStanding(Boolean isStanding) {
        this.isStanding = isStanding;
    }

    public Boolean getIsStanding() {
        return this.isStanding;
    }

    public Boolean getIsAscending() {
        return isAscending;
    }
    public void setIsAscending(Boolean isAscending) {
        this.isAscending = isAscending;
    }
    public Boolean getIsDescending() {
        return isDescending;
    }
    public void setIsDescending(Boolean isDescending) {
        this.isDescending = isDescending;
    }

    public Boolean getIsDucking() { return  isDucking; }

    public void setIsDucking(Boolean isDucking) {
        this.isDucking = isDucking;
    }
}
