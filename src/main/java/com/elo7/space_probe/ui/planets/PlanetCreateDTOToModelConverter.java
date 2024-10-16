package com.elo7.space_probe.ui.planets;

import com.elo7.space_probe.domain.Obstacle;
import com.elo7.space_probe.domain.Planet;
import org.springframework.stereotype.Component;

@Component
public class PlanetCreateDTOToModelConverter {
    public Planet convert(PlanetCreateDTO planetCreateDTO) {
        var planet = new Planet(planetCreateDTO.name(), planetCreateDTO.width(), planetCreateDTO.height());

        planetCreateDTO.obstacles().forEach(obstacleDTO ->
                planet.addObstacle(new Obstacle(obstacleDTO.x(), obstacleDTO.y()))
        );

        return planet;
    }
}