package zordo.entities.characters.player;

import zordo.models.gamePad.ControllerComponent;
import zordo.entities.characters.Character;
import zordo.models.physics.world.WorldComponent;

public class Player extends Character {
    public ControllerComponent playerController;

    public Player(WorldComponent world) {
        super(world);
        playerController = new ControllerComponent();
    }
}
