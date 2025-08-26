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

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
        if(!this.pressed) {
            this.released = true;
        }
    }
    public void setReleased(boolean released) {
        this.released = released;
        if(!this.released) {
            this.pressed = true;
        }
    }
}
