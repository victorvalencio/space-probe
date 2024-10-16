package com.elo7.space_probe.ui.planets;

import com.elo7.space_probe.ui.ObstacleDTO;

import java.util.List;

public record PlanetDTO(long id, String name, int width, int height, List<ObstacleDTO> obstacles) { }

