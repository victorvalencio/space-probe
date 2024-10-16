package com.elo7.space_probe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "planet")
@JsonIgnoreProperties(value = {"probes"}, allowSetters = true)
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Min(value = 1, message = "'width' must be greater than 0")
    @Column(name = "width", nullable = false)
    private Integer width;

    @Min(value = 1, message = "'height' must be greater than 0")
    @Column(name = "height", nullable = false)
    private Integer height;

    @OneToMany(mappedBy = "planet", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Probe> probes;

    @Deprecated
    public Planet() {}

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "planet_obstacles", joinColumns = @JoinColumn(name = "planet_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "obstacle_x")),
            @AttributeOverride(name = "y", column = @Column(name = "obstacle_y"))
    })
    private List<Obstacle> obstacles = new ArrayList<>();


    public Planet(String name, Integer width, Integer height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public List<Probe> getProbes() {
        return probes;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public void addObstacle(Obstacle obstacle) {
        this.obstacles.add(obstacle);
    }

    public boolean hasObstacleAt(int x, int y) {
        return obstacles.stream().anyMatch(o -> o.getX() == x && o.getY() == y);
    }
}
