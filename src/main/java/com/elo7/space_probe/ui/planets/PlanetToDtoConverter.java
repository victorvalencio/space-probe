package com.elo7.space_probe.ui.planets;

import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.ui.ObstacleDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlanetToDtoConverter {
    public PlanetDTO convert(Planet planet) {
        List<ObstacleDTO> obstacleDTOs = planet.getObstacles().stream()
                .map(obstacle -> new ObstacleDTO(obstacle.getX(), obstacle.getY()))
                .toList();
        return new PlanetDTO(planet.getId(), planet.getName(), planet.getWidth(), planet.getHeight(), obstacleDTOs);
    }
}