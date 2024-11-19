package com.zordo.game.boundary;

import com.zordo.game.platforms.Platform;

public class Limit {
    private final Platform leftBoundary;
    private final Platform rightBoundary;
    private final Platform topBoundary;
    private final Platform bottomBoundary;

    public Limit() {
        this.leftBoundary = new Platform();
        this.rightBoundary = new Platform();
        this.topBoundary = new Platform();
        this.bottomBoundary = new Platform();

        this.leftBoundary.setWidth(10);
        this.rightBoundary.setWidth(10);
        this.leftBoundary.setHeight(1080);
        this.rightBoundary.setHeight(1080);

        this.topBoundary.setWidth(1980);
        this.bottomBoundary.setWidth(1980);
        this.topBoundary.setHeight(10);
        this.bottomBoundary.setHeight(10);

    }
}
