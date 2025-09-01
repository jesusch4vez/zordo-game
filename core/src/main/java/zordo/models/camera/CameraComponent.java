package zordo.models.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import zordo.models.Component;

public class CameraComponent extends Component {
    private OrthographicCamera camera;
    public CameraComponent() {
        super();
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    public OrthographicCamera getCamera() {
        return this.camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }
}
