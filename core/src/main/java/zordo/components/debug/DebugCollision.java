package zordo.components.debug;

import com.badlogic.gdx.graphics.Texture;
import zordo.components.physics.terrain.surfaces.PlatformComponent;

public class DebugCollision extends PlatformComponent {

    public DebugCollision() {
        super();
        super.setPlatformTexture(new Texture("environment/walltexture.png"));
    }
}
