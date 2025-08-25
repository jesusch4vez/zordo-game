package zordo.components.gamePad;

import zordo.components.Component;

public class ControllerComponent extends Component {
    public static Button START_BUTTON = new Button(6);
    public static Button SELECT_BUTTON = new Button(4);

    public static Button D_PAD_UP = new Button(11);
    public static Button D_PAD_DOWN = new Button(12);
    public static Button D_PAD_LEFT = new Button(13);
    public static Button D_PAD_RIGHT = new Button(14);

    public static Button A_BUTTON = new Button(0);
    public static Button B_BUTTON = new Button(1);
    public static Button X_BUTTON = new Button(2);
    public static Button Y_BUTTON = new Button(3);

    public static Button LEFT_STICK = new Button(7);
    public static Button RIGHT_STICK = new Button(8);

    public static Button LEFT_BUMPER = new Button(9);
    public static Button RIGHT_BUMPER = new Button(10);
}
