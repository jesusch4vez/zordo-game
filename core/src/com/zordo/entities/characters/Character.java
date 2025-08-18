package com.zordo.entities.characters;

import com.zordo.components.character.CharacterComponent;

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
