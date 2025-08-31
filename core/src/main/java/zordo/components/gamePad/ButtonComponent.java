package zordo.components.gamePad;

import com.badlogic.gdx.graphics.Texture;
import zordo.components.Component;

public class ButtonComponent extends Component {
    private boolean pressed;
    private boolean released;
    private final int buttonCode;

    private Texture texture;

    public ButtonComponent(int buttonCode, String texturePath) {
        pressed = false;
        released = true;
        this.buttonCode = buttonCode;
        this.texture = new Texture(texturePath);
    }

    public int getButtonCode() {
        return buttonCode;
    }
    public boolean isPressed() {
        return pressed;
    }
    public boolean isReleased() {
        return released;
    }

    public void press() {
        this.pressed = true;
        this.released = false;
    }
    public void release() {
        this.released = true;
        this.pressed = false;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}
