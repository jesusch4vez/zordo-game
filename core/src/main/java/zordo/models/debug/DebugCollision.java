package zordo.models.debug;

import com.badlogic.gdx.graphics.Texture;
import zordo.models.physics.terrain.surfaces.PlatformComponent;

public class DebugCollision extends PlatformComponent {

    public DebugCollision() {
        super();
        super.setPlatformTexture(new Texture("environment/walltexture.png"));
    }
}
