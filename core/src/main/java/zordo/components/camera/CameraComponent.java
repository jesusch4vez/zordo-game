package zordo.components.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import zordo.components.Component;

public class CameraComponent extends Component {
    private OrthographicCamera camera;
    public CameraComponent() {
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
