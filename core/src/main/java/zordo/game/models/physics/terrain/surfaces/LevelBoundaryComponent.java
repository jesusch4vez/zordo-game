package zordo.game.models.physics.terrain.surfaces;

import zordo.game.models.physics.world.WorldComponent;

public class LevelBoundaryComponent extends PlatformComponent {
    public LevelBoundaryComponent(WorldComponent world, float width, float height, float x, float y) {
        super(world, width, height, x, y);
    }
}
