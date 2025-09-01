package zordo.models.debug;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import zordo.models.physics.terrain.surfaces.PlatformComponent;
import zordo.models.physics.world.WorldComponent;

public class DebugCollision extends PlatformComponent {

    public DebugCollision(World world) {
        super(world);
        super.setPlatformTexture(new Texture("environment/walltexture.png"));
    }
}
