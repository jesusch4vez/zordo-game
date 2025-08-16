package com.zordo.entity_component_system.component.menu;

import com.zordo.entity_component_system.component.Component;

public class Item extends Component {
    boolean isSelectable;
    String name;

    public Item() {
        this.isSelectable = false;
        this.name = "";
    }

    public Item(String name) {
        this.isSelectable = false;
        this.name = name;
    }

    public void setIsSelectable(boolean isSelectable) {
        this.isSelectable = isSelectable;
    }

    public boolean getIsSelectable() {
        return this.isSelectable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
