package com.zordo.components.debug;

import com.badlogic.gdx.graphics.Texture;
import com.zordo.components.physics.terrain.surfaces.PlatformComponent;

public class DebugCollision extends PlatformComponent {

    public DebugCollision() {
        super();
        super.setPlatformTexture(new Texture("environment/walltexture.png"));
    }
}
