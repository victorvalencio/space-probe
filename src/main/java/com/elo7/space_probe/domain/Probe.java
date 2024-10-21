package com.elo7.space_probe.domain;

import com.elo7.space_probe.domain.Enum.Direction;
import jakarta.persistence.*;

@Entity
@Table(name = "probe")
public class Probe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "planet_id", referencedColumnName = "id", nullable = false)
    private Planet planet;

    @Enumerated(EnumType.STRING)
    private Direction direction;

    @Deprecated
    public Probe() {
    }

    public Probe(String name, Integer x, Integer y, Planet planet, Direction direction) {
        this.name = name;
        this.position = new Position(x, y);
        this.planet = planet;
        this.direction = direction;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position direction) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public void turnLeft() {
        this.direction = direction.turnLeft();
    }

    public void turnRight() {
        this.direction = direction.turnRight();
    }

    public void move() {

        int nextX = this.getPosition().getX();
        int nextY = this.getPosition().getY();

        switch (direction) {
            case NORTH -> nextY += 1;
            case SOUTH -> nextY -= 1;
            case EAST -> nextX += 1;
            case WEST -> nextX -= 1;
        }

        Position newPosition = new Position(nextX, nextY);
        if (planet.isPositionOutOfBoundiers(newPosition)) {
            throw new RuntimeException("Sonda está tentando mover-se para fora dos limites do planeta!");
        }

        if (planet.hasObstacleAt(newPosition)) {
            throw new RuntimeException("Obstáculo encontrado na posição ("
                    + newPosition.getX() + ", " + newPosition.getY() + ")");
        }

        if (planet.isCollidingWithAnotherProbe(newPosition)) {
            throw new RuntimeException("Colisão detectada! Outra sonda já está na posição ("
                    + newPosition.getX() + ", " + newPosition.getY() + ")");
        }
        this.position = newPosition;
    }


    public void setId(int i) {
    }

    public void setName(String probe1) {
    }
}