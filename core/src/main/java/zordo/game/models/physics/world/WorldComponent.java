package zordo.game.models.physics.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class WorldComponent {
    private World world;

    public WorldComponent() {
        this.world = new World(new Vector2(0, -1), true);
    }

    public World getWorld() {
        return this.world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
