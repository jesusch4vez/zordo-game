package zordo.entities.characters.player;

import zordo.components.gamePad.ControllerComponent;
import zordo.entities.characters.Character;

public class Player extends Character {
    public ControllerComponent playerController;

    public Player() {
        super();
        playerController = new ControllerComponent();
    }
}
