package com.zordo.entity_component_system.component.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.zordo.entity_component_system.component.Component;

public class Camera extends Component {
    private OrthographicCamera camera;
    public Camera() {
        super();
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920,1080);
    }

    public OrthographicCamera getCamera() {
        return this.camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }
}
