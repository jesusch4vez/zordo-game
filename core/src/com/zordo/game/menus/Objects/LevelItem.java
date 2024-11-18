package com.zordo.game.menus.Objects;

public class LevelItem implements MenuItem {

    private int level;
    private String itemName;
    private boolean enabled;

    public LevelItem() {
        this.level = 0;
        this.itemName = "";
        this.enabled = false;
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
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
