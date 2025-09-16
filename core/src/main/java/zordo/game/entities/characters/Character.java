package zordo.game.entities.characters;

import zordo.game.models.character.CharacterComponent;
import zordo.game.models.physics.world.WorldComponent;

public class Character {
    CharacterComponent characterComponent;
    public Character(WorldComponent world) {
        characterComponent = new CharacterComponent(world);
    }

    public CharacterComponent getCharacterComponent() {
        return characterComponent;
    }

    public void setCharacterComponent(CharacterComponent characterComponent) {
        this.characterComponent = characterComponent;
    }
}
