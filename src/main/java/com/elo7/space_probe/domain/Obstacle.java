package com.elo7.space_probe.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Obstacle {

    private Integer x;
    private Integer y;

    public Obstacle() {}

    public Obstacle(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}