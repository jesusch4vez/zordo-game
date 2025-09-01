package zordo.entities.characters;

import zordo.models.character.CharacterComponent;
import zordo.models.physics.world.WorldComponent;

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
