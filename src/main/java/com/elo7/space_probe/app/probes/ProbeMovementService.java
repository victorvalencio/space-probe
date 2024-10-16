package com.elo7.space_probe.app.probes;

import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Position;
import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.infra.repository.SpringDataProbeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProbeMovementService {

    private final SpringDataProbeRepository probeRepository;

    public ProbeMovementService(SpringDataProbeRepository probeRepository) {
        this.probeRepository = probeRepository;
    }

    public void move(Probe probe, String commands) {
        Planet planet = probe.getPlanet();

        for (char command : commands.toUpperCase().toCharArray()) {
            switch (command) {
                case 'L' -> probe.turnLeft();
                case 'R' -> probe.turnRight();
                case 'M' -> {
                    Position nextPosition = probe.getPosition().getNextPosition(probe.getDirection());

                    if (nextPosition.getX() < 0 || nextPosition.getX() > planet.getWidth() ||
                            nextPosition.getY() < 0 || nextPosition.getY() > planet.getHeight()) {
                        throw new RuntimeException("Sonda está tentando mover-se para fora dos limites do planeta!");
                    }

                    if (planet.hasObstacleAt(nextPosition.getX(), nextPosition.getY())) {
                        throw new RuntimeException("Obstáculo encontrado na posição ("
                                + nextPosition.getX() + ", " + nextPosition.getY() + ")");
                    }

                    if (isCollidingWithAnotherProbe(planet, probe, nextPosition)) {
                        throw new RuntimeException("Colisão detectada! Outra sonda já está na posição ("
                                + nextPosition.getX() + ", " + nextPosition.getY() + ")");
                    }

                    probe.move();

                    probeRepository.save(probe);
                }
            }
        }
    }
    private boolean isCollidingWithAnotherProbe(Planet planet, Probe probe, Position nextPosition) {
        List<Probe> otherProbes = planet.getProbes();

        for (Probe otherProbe : otherProbes) {
            if (!otherProbe.equals(probe)) {
                System.out.println("Verificando colisão: Sonda " + otherProbe.getName() + " está em ("
                        + otherProbe.getPosition().getX() + ", " + otherProbe.getPosition().getY() +
                        "), próxima posição da sonda " + probe.getName() + " é ("
                        + nextPosition.getX() + ", " + nextPosition.getY() + ")");

                if (otherProbe.getPosition().getX().equals(nextPosition.getX()) &&
                        otherProbe.getPosition().getY().equals(nextPosition.getY())) {
                    System.out.println("Colisão detectada entre sonda " + probe.getName() + " e sonda " + otherProbe.getName());
                    return true;
                }
            }
        }
        return false;
    }
}
