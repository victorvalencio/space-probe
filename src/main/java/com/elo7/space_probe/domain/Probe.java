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
        System.out.println("Movendo sonda na direção: " + direction);
        switch (direction) {
            case NORTH -> {
                position.setY(position.getY() + 1);
                System.out.println("Nova posição: (" + position.getX() + ", " + position.getY() + ")");
            }
            case SOUTH -> {
                position.setY(position.getY() - 1);
                System.out.println("Nova posição: (" + position.getX() + ", " + position.getY() + ")");
            }
            case EAST -> {
                position.setX(position.getX() + 1);
                System.out.println("Nova posição: (" + position.getX() + ", " + position.getY() + ")");
            }
            case WEST -> {
                position.setX(position.getX() - 1);
                System.out.println("Nova posição: (" + position.getX() + ", " + position.getY() + ")");
            }
        }
    }

    public void setId(int i) {
    }

    public void setName(String probe1) {
    }
}