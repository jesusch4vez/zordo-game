package zordo.game.models.gamePad;

import com.badlogic.gdx.graphics.Texture;
import zordo.game.models.Component;

public class AxisComponent extends Component {
    private boolean tilted;
    private final int axisCode;
    private float axisValue;
    private Texture texture;

    public AxisComponent(int axisCode, float axisValue, String texturePath) {
        this.axisCode = axisCode;
        this.axisValue = axisValue;
        tilted = false;
        this.texture = new Texture(texturePath);
    }

    public int getAxisCode() {
        return axisCode;
    }

    public float getAxisValue() {
        return axisValue;
    }

    public void tilt(float value) {
        axisValue = value;
        tilted = axisValue != 0;
    }

    public boolean isTilted() {
        return axisValue != 0;
    }

    public Texture getTexture() {
        return texture;
    }
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
