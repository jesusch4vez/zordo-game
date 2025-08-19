package com.zordo.components.physics.terrain.surfaces;

public class LevelBoundaryComponent extends PlatformComponent {
    Boolean isWall;
    Boolean isCeiling;
    Boolean isFloor;

    public LevelBoundaryComponent() {
        isWall = false;
        isCeiling = false;
        isFloor = false;
    }

    public LevelBoundaryComponent(Boolean isWall, Boolean isCeiling, Boolean isFloor) {
        this.isWall = isWall;
        this.isCeiling = isCeiling;
        this.isFloor = isFloor;
    }
}
