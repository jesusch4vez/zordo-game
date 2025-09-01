package zordo.models.physics.terrain.surfaces;

import zordo.models.physics.world.WorldComponent;

public class LevelBoundaryComponent extends PlatformComponent {
    Boolean isWall;
    Boolean isCeiling;
    Boolean isFloor;

    public LevelBoundaryComponent(WorldComponent world) {
        super(world.getWorld());
        isWall = false;
        isCeiling = false;
        isFloor = false;
    }

    public LevelBoundaryComponent(Boolean isWall, Boolean isCeiling, Boolean isFloor, WorldComponent world) {
        super(world.getWorld());
        this.isWall = isWall;
        this.isCeiling = isCeiling;
        this.isFloor = isFloor;
    }
}
