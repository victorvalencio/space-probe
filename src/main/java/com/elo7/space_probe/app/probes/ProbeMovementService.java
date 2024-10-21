package com.elo7.space_probe.app.probes;

import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.domain.Probes;
import org.springframework.stereotype.Service;

@Service
public class ProbeMovementService {

    private final Probes probeRepository;

    public ProbeMovementService(Probes probeRepository) {
        this.probeRepository = probeRepository;
    }

    public void move(Probe probe, String commands) {
        Planet planet = probe.getPlanet();

        for (char command : commands.toUpperCase().toCharArray()) {
            switch (command) {
                case 'L' -> probe.turnLeft();
                case 'R' -> probe.turnRight();
                case 'M' -> probe.move();
            }
        }

        probeRepository.save(probe);
    }
}
