package com.zordo.entity_component_system.component.menu;

import com.zordo.entity_component_system.component.Component;

public class MenuItem extends Component {
    boolean isSelectable;
    boolean isSelected;
    String name;

    public MenuItem() {
        this.isSelectable = false;
        this.isSelected = false;
        this.name = "";
    }

    public void setIsSelected(boolean selected) {
        this.isSelected = selected;
    }

    public boolean getIsSelected() {
        return this.isSelected;
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
