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
}
