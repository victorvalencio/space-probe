package com.elo7.space_probe.ui.probes;

import com.elo7.space_probe.domain.Probe;
import org.springframework.stereotype.Component;

@Component
public class ProbeToDtoConverter {
    public ProbeDTO convert(Probe probe) {
        return new ProbeDTO(
                probe.getId(),
                probe.getName(),
                probe.getPosition().getX(),
                probe.getPosition().getY(),
                probe.getPlanet().getId()
        );
    }
}