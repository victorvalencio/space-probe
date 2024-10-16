package com.elo7.space_probe.ui;

import com.elo7.space_probe.domain.Obstacle;
import com.elo7.space_probe.ui.planets.PlanetCreateDTO;
import com.elo7.space_probe.ui.planets.PlanetCreateDTOToModelConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlanetCreateDTOToModelConverterTest {

    private PlanetCreateDTOToModelConverter planetCreateDTOToModelConverter;

    @BeforeEach
    void setUp() {
        planetCreateDTOToModelConverter = new PlanetCreateDTOToModelConverter();
    }

    @Test
    void testConvertPlanetCreateDTOToPlanet() {
        List<ObstacleDTO> obstacles = List.of(new ObstacleDTO(2, 3), new ObstacleDTO(4, 5));

        var planetCreateDTO = new PlanetCreateDTO("Earth", 10, 10, obstacles);

        var planet = planetCreateDTOToModelConverter.convert(planetCreateDTO);

        assertEquals("Earth", planet.getName());
        assertEquals(10, planet.getWidth());
        assertEquals(10, planet.getHeight());

        List<Obstacle> planetObstacles = planet.getObstacles();
        assertEquals(2, planetObstacles.size());
        assertEquals(2, planetObstacles.get(0).getX());
        assertEquals(3, planetObstacles.get(0).getY());
        assertEquals(4, planetObstacles.get(1).getX());
        assertEquals(5, planetObstacles.get(1).getY());
    }
}

