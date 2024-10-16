package com.elo7.space_probe.ui.planets;

import com.elo7.space_probe.ui.ObstacleDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PlanetCreateDTO(
        @JsonProperty("name") String name,
        @JsonProperty("width") Integer width,
        @JsonProperty("height") Integer height,
        @JsonProperty("obstacles") List<ObstacleDTO> obstacles
) { }