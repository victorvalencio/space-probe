package com.elo7.space_probe.ui.probes;

public record ProbeDTO(
        Integer id,
        String name,
        Integer x,
        Integer y,
        Integer planetId
) { }