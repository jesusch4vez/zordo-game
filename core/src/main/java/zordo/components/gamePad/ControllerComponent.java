package zordo.components.gamePad;

import zordo.components.Component;

public class ControllerComponent extends Component {
    public static ButtonComponent START_BUTTON = new ButtonComponent(6);
    public static ButtonComponent SELECT_BUTTON = new ButtonComponent(4);

    public static ButtonComponent D_PAD_UP = new ButtonComponent(11);
    public static ButtonComponent D_PAD_DOWN = new ButtonComponent(12);
    public static ButtonComponent D_PAD_LEFT = new ButtonComponent(13);
    public static ButtonComponent D_PAD_RIGHT = new ButtonComponent(14);

    public static ButtonComponent A_BUTTON = new ButtonComponent(0);
    public static ButtonComponent B_BUTTON = new ButtonComponent(1);
    public static ButtonComponent X_BUTTON = new ButtonComponent(2);
    public static ButtonComponent Y_BUTTON = new ButtonComponent(3);

    public static ButtonComponent LEFT_STICK = new ButtonComponent(7);
    public static ButtonComponent RIGHT_STICK = new ButtonComponent(8);

    public static ButtonComponent LEFT_BUMPER = new ButtonComponent(9);
    public static ButtonComponent RIGHT_BUMPER = new ButtonComponent(10);

    public static AxisComponent LEFT_STICK_X = new AxisComponent(0,0);
    public static AxisComponent LEFT_STICK_Y = new AxisComponent(1,0);
    public static AxisComponent RIGHT_STICK_X = new AxisComponent(2,0);
    public static AxisComponent RIGHT_STICK_Y = new AxisComponent(3,0);

    public static AxisComponent RIGHT_TRIGGER = new AxisComponent(4,0);
    public static AxisComponent LEFT_TRIGGER = new AxisComponent(5,0);

    public void pressButton(int buttonCode) {
        switch (buttonCode) {
            case 6:
                START_BUTTON.press();
                break;
            case 11:
                D_PAD_UP.press();
                break;
            case 12:
                D_PAD_DOWN.press();
                break;
            case 13:
                D_PAD_LEFT.press();
                break;
            case 14:
                D_PAD_RIGHT.press();
                break;
            case 0:
                A_BUTTON.press();
                break;
            case 1:
                B_BUTTON.press();
                break;
            case 2:
                X_BUTTON.press();
                break;
            case 3:
                Y_BUTTON.press();
                break;
            case 7:
                LEFT_STICK.press();
                break;
            case 8:
                RIGHT_STICK.press();
                break;
            case 9:
                LEFT_BUMPER.press();
                break;
            case 10:
                RIGHT_BUMPER.press();
                break;
            default:
                break;
        }
    }

    public void releaseButton(int buttonCode) {
        switch (buttonCode) {
            case 6:
                START_BUTTON.release();
                break;
            case 11:
                D_PAD_UP.release();
                break;
            case 12:
                D_PAD_DOWN.release();
                break;
            case 13:
                D_PAD_LEFT.release();
                break;
            case 14:
                D_PAD_RIGHT.release();
                break;
            case 0:
                A_BUTTON.release();
                break;
            case 1:
                B_BUTTON.release();
                break;
            case 2:
                X_BUTTON.release();
                break;
            case 3:
                Y_BUTTON.release();
                break;
            case 7:
                LEFT_STICK.release();
                break;
            case 8:
                RIGHT_STICK.release();
                break;
            case 9:
                LEFT_BUMPER.release();
                break;
            case 10:
                RIGHT_BUMPER.release();
                break;
            default:
                break;
        }
    }

    public void tiltAxis(int axisCode, float value) {
        switch (axisCode) {
            case 0:
                LEFT_STICK_X.tilt(value);
                break;
            case 1:
                LEFT_STICK_Y.tilt(value);
                break;
            case 2:
                RIGHT_STICK_X.tilt(value);
                break;
            case 3:
                RIGHT_STICK_Y.tilt(value);
                break;
            case 4:
                RIGHT_TRIGGER.tilt(value);
                break;
            case 5:
                LEFT_TRIGGER.tilt(value);
                break;
            default:
                break;
        }
    }
}
