package com.elo7.space_probe.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Obstacle {

    private Position position;

    public Obstacle() {}

    public Obstacle(Position position) {
        this.position = position;
    }

    public Integer getX() {
        return position.getX();
    }

    public Integer getY() {
        return position.getY();
    }

    public Position getPosition() {
        return position;
    }

}