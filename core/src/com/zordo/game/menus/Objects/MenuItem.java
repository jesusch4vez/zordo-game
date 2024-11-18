package com.zordo.game.menus.Objects;

public interface MenuItem {
    String text = "";
    boolean enabled = true;

    String getText();
    void setText(String name);

    boolean isEnabled();
    void setEnabled(boolean enabled);
}
