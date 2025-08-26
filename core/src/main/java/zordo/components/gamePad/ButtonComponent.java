package zordo.components.gamePad;

import zordo.components.Component;

public class ButtonComponent extends Component {
    private boolean pressed;
    private boolean released;
    private final int buttonCode;

    public ButtonComponent(int buttonCode) {
        pressed = false;
        released = true;
        this.buttonCode = buttonCode;
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
}
