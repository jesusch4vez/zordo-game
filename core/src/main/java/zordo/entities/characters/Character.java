package zordo.entities.characters;

import zordo.components.character.CharacterComponent;

public class Character {
    CharacterComponent characterComponent;
    public Character() {
        characterComponent = new CharacterComponent();
    }

    public CharacterComponent getCharacterComponent() {
        return characterComponent;
    }

    public void setCharacterComponent(CharacterComponent characterComponent) {
        this.characterComponent = characterComponent;
    }
}
