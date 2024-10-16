package com.elo7.space_probe.domain;

import com.elo7.space_probe.domain.Enum.Direction;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Position {

    @Column(name = "x", nullable = false)
    private Integer x;

    @Column(name = "y", nullable = false)
    private Integer y;

    @Deprecated // hibernate only
    public Position() {}

    public Position(Integer x, Integer y) {
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

    public Position getNextPosition(Direction direction) {
        int nextX = this.x;
        int nextY = this.y;

        switch (direction) {
            case NORTH -> nextY += 1;
            case SOUTH -> nextY -= 1;
            case EAST  -> nextX += 1;
            case WEST  -> nextX -= 1;
        }

        return new Position(nextX, nextY);
    }
}