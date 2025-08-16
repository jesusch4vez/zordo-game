package com.zordo.entity.level;

import com.badlogic.gdx.Screen;

public class LevelItem {

    private final Screen level;
    private String itemName;
    private boolean selected;

    public LevelItem(Screen level) {
        this.level = level;
        this.itemName = "";
    }

    public LevelItem(Screen level, boolean isDefault) {
        this.level = level;
        this.itemName = "";
        if(isDefault) {
            this.selected = true;
        }
    }

    public String getText() {
        return this.itemName;
    }

    public void setText(String name) {
        this.itemName = name;
    }

    public Screen getLevel() {
        return this.level;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
