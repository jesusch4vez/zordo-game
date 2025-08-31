package zordo.components.gamePad;

import zordo.components.Component;

import java.util.HashMap;

public class ControllerComponent extends Component {
    static HashMap<String, ButtonComponent> pressedButtons = new HashMap<>();
    static HashMap<String, AxisComponent> movedAxes = new HashMap<>();

    public static HashMap<String, ButtonComponent> getPressedButtons() {
        return pressedButtons;
    }

    public static HashMap<String, AxisComponent> getMovedAxes() {
        return movedAxes;
    }

    public void addPressedButton(String buttonName, ButtonComponent buttonComponent) {
        pressedButtons.put(buttonName, buttonComponent);
    }

    public void removePressedButton(String buttonName) {
        pressedButtons.remove(buttonName);
    }

    public void addMovedAxis(String axisName, AxisComponent axisComponent) {
        movedAxes.put(axisName, axisComponent);
    }

    public void removeMovedAxes(String axisName) {
        movedAxes.remove(axisName);
    }

    public static ButtonComponent START_BUTTON = new ButtonComponent(6, "debug_assets/controller/xbox/button/xbox_button_menu.png");
    public static ButtonComponent SELECT_BUTTON = new ButtonComponent(4, "debug_assets/controller/xbox/button/xbox_button_view_outline.png");

    public static ButtonComponent D_PAD_UP = new ButtonComponent(11, "debug_assets/controller/xbox/button/xbox_dpad_up.png");
    public static ButtonComponent D_PAD_DOWN = new ButtonComponent(12, "debug_assets/controller/xbox/button/xbox_dpad_down.png");
    public static ButtonComponent D_PAD_LEFT = new ButtonComponent(13, "debug_assets/controller/xbox/button/xbox_dpad_left.png");
    public static ButtonComponent D_PAD_RIGHT = new ButtonComponent(14, "debug_assets/controller/xbox/button/xbox_dpad_right.png");

    public static ButtonComponent A_BUTTON = new ButtonComponent(0, "debug_assets/controller/xbox/button/xbox_button_color_a.png");
    public static ButtonComponent B_BUTTON = new ButtonComponent(1, "debug_assets/controller/xbox/button/xbox_button_color_b.png");
    public static ButtonComponent X_BUTTON = new ButtonComponent(2, "debug_assets/controller/xbox/button/xbox_button_color_x.png");
    public static ButtonComponent Y_BUTTON = new ButtonComponent(3, "debug_assets/controller/xbox/button/xbox_button_color_y.png");

    public static ButtonComponent LEFT_STICK = new ButtonComponent(7, "debug_assets/controller/xbox/button/xbox_ls.png");
    public static ButtonComponent RIGHT_STICK = new ButtonComponent(8, "debug_assets/controller/xbox/button/xbox_rs.png");

    public static ButtonComponent LEFT_BUMPER = new ButtonComponent(9, "debug_assets/controller/xbox/button/xbox_rb.png");
    public static ButtonComponent RIGHT_BUMPER = new ButtonComponent(10, "debug_assets/controller/xbox/button/xbox_lb.png");

    public static AxisComponent LEFT_STICK_X = new AxisComponent(0,0, "debug_assets/controller/xbox/axes/xbox_stick_l_horizontal.png");
    public static AxisComponent LEFT_STICK_Y = new AxisComponent(1,0, "debug_assets/controller/xbox/axes/xbox_stick_l_vertical.png");
    public static AxisComponent RIGHT_STICK_X = new AxisComponent(2,0, "debug_assets/controller/xbox/axes/xbox_stick_r_horizontal.png");
    public static AxisComponent RIGHT_STICK_Y = new AxisComponent(3,0, "debug_assets/controller/xbox/axes/xbox_stick_r_vertical.png");

    public static AxisComponent LEFT_TRIGGER = new AxisComponent(4,0, "debug_assets/controller/xbox/axes/xbox_lt.png");
    public static AxisComponent RIGHT_TRIGGER = new AxisComponent(5,0, "debug_assets/controller/xbox/axes/xbox_rt.png");

    public void pressButton(int buttonCode) {
        switch (buttonCode) {
            case 6:
                START_BUTTON.press();
                this.addPressedButton("START", START_BUTTON);
                break;
            case 4:
                SELECT_BUTTON.press();
                this.addPressedButton("SELECT", SELECT_BUTTON);
                break;
            case 11:
                D_PAD_UP.press();
                this.addPressedButton("D UP", D_PAD_UP);
                break;
            case 12:
                D_PAD_DOWN.press();
                this.addPressedButton("D DOWN", D_PAD_DOWN);
                break;
            case 13:
                D_PAD_LEFT.press();
                this.addPressedButton("D LEFT", D_PAD_LEFT);
                break;
            case 14:
                D_PAD_RIGHT.press();
                this.addPressedButton("D RIGHT", D_PAD_RIGHT);
                break;
            case 0:
                A_BUTTON.press();
                this.addPressedButton("A", A_BUTTON);
                break;
            case 1:
                B_BUTTON.press();
                this.addPressedButton("B", B_BUTTON);
                break;
            case 2:
                X_BUTTON.press();
                this.addPressedButton("X", X_BUTTON);
                break;
            case 3:
                Y_BUTTON.press();
                this.addPressedButton("Y", Y_BUTTON);
                break;
            case 7:
                LEFT_STICK.press();
                this.addPressedButton("Left Stick", LEFT_STICK);
                break;
            case 8:
                RIGHT_STICK.press();
                this.addPressedButton("Right Stick", RIGHT_STICK);
                break;
            case 9:
                LEFT_BUMPER.press();
                this.addPressedButton("Left Bumper", LEFT_BUMPER);
                break;
            case 10:
                RIGHT_BUMPER.press();
                this.addPressedButton("Right Bumper", RIGHT_BUMPER);
                break;
            default:
                break;
        }
    }

    public void releaseButton(int buttonCode) {
        switch (buttonCode) {
            case 6:
                START_BUTTON.release();
                this.removePressedButton("START");
                break;
            case 4:
                SELECT_BUTTON.release();
                this.removePressedButton("SELECT");

                break;
            case 11:
                D_PAD_UP.release();
                this.removePressedButton("D UP");

                break;
            case 12:
                D_PAD_DOWN.release();
                this.removePressedButton("D DOWN");

                break;
            case 13:
                D_PAD_LEFT.release();
                this.removePressedButton("D LEFT");

                break;
            case 14:
                D_PAD_RIGHT.release();
                this.removePressedButton("D RIGHT");

                break;
            case 0:
                A_BUTTON.release();
                this.removePressedButton("A");

                break;
            case 1:
                B_BUTTON.release();

                this.removePressedButton("B");
                break;
            case 2:
                X_BUTTON.release();

                this.removePressedButton("X");
                break;
            case 3:
                Y_BUTTON.release();

                this.removePressedButton("Y");
                break;
            case 7:
                LEFT_STICK.release();

                this.removePressedButton("Left Stick");
                break;
            case 8:
                RIGHT_STICK.release();

                this.removePressedButton("Right Stick");
                break;
            case 9:
                LEFT_BUMPER.release();

                this.removePressedButton("Left Bumper");
                break;
            case 10:
                RIGHT_BUMPER.release();

                this.removePressedButton("Right Bumper");
                break;
            default:
                break;
        }
    }

    public void tiltAxis(int axisCode, float value) {
        switch (axisCode) {
            case 0:
                LEFT_STICK_X.tilt(value);
                if(Math.abs(value)>0) {
                    addMovedAxis("LEFT_STICK_X", LEFT_STICK_X);
                } else {
                    removeMovedAxes("LEFT_STICK_X");
                }
                break;
            case 1:
                LEFT_STICK_Y.tilt(value);
                if(Math.abs(value)>0) {
                    addMovedAxis("LEFT_STICK_Y", LEFT_STICK_Y);
                } else {
                    removeMovedAxes("LEFT_STICK_Y");
                }
                break;
            case 2:
                RIGHT_STICK_X.tilt(value);
                if(Math.abs(value)>0) {
                    addMovedAxis("RIGHT_STICK_X", RIGHT_STICK_X);
                } else {
                    removeMovedAxes("RIGHT_STICK_X");
                }
                break;
            case 3:
                RIGHT_STICK_Y.tilt(value);
                if(Math.abs(value)>0) {
                    addMovedAxis("RIGHT_STICK_Y", RIGHT_STICK_Y);
                } else {
                    removeMovedAxes("RIGHT_STICK_Y");
                }
                break;
            case 4:
                LEFT_TRIGGER.tilt(value);
                if(Math.abs(value)>0) {
                    addMovedAxis("LEFT_TRIGGER", LEFT_TRIGGER);
                } else {
                    removeMovedAxes("LEFT_TRIGGER");
                }
                break;
            case 5:
                RIGHT_TRIGGER.tilt(value);
                if(Math.abs(value)>0) {
                    addMovedAxis("RIGHT_TRIGGER", RIGHT_TRIGGER);
                } else {
                    removeMovedAxes("RIGHT_TRIGGER");
                }
                break;
            default:
                break;
        }
    }
}
