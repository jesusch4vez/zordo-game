package com.zordo.model.level;

import com.badlogic.gdx.Screen;
import com.zordo.model.menu.MenuItem;

public class LevelItem implements MenuItem {

    private Screen level;
    private String itemName;
    private boolean enabled;
    private boolean defaultSelection;
    private boolean selected;

    public LevelItem(Screen level) {
        this.level = level;
        this.itemName = "";
        this.enabled = false;
        this.defaultSelection = false;
    }

    public LevelItem(Screen level, boolean isDefault) {
        this.level = level;
        this.itemName = "";
        this.enabled = false;
        this.defaultSelection = isDefault;
        if(isDefault) {
            this.selected = true;
        }
    }

    @Override
    public String getText() {
        return this.itemName;
    }

    @Override
    public void setText(String name) {
        this.itemName = name;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Screen getLevel() {
        return this.level;
    }

    public void setLevel(Screen level) {
        this.level = level;
    }

    public boolean isDefaultSelection() {
        return defaultSelection;
    }

    public void setDefaultSelection(boolean defaultSelection) {
        this.defaultSelection = defaultSelection;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
