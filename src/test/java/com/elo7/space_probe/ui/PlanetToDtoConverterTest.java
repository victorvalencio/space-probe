package com.elo7.space_probe.ui;

import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Obstacle;
import com.elo7.space_probe.domain.Position;
import com.elo7.space_probe.ui.planets.PlanetToDtoConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlanetToDtoConverterTest {

    private PlanetToDtoConverter planetToDtoConverter;

    @BeforeEach
    void setUp() {
        planetToDtoConverter = new PlanetToDtoConverter();
    }

    @Test
    void testConvertPlanetToPlanetDTO() {
        var planet = new Planet("Earth", 10, 10);
        planet.setId(1);
        planet.addObstacle(new Obstacle(new Position(2, 3)));
        planet.addObstacle(new Obstacle(new Position(4, 5)));

        var planetDTO = planetToDtoConverter.convert(planet);

        assertEquals(1, planetDTO.id());
        assertEquals("Earth", planetDTO.name());
        assertEquals(10, planetDTO.width());
        assertEquals(10, planetDTO.height());

        List<ObstacleDTO> obstacleDTOs = planetDTO.obstacles();
        assertEquals(2, obstacleDTOs.size());
        assertEquals(2, obstacleDTOs.get(0).x());
        assertEquals(3, obstacleDTOs.get(0).y());
        assertEquals(4, obstacleDTOs.get(1).x());
        assertEquals(5, obstacleDTOs.get(1).y());
    }
}