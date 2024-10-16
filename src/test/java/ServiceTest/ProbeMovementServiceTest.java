package ServiceTest;

import com.elo7.space_probe.app.probes.ProbeMovementService;
import com.elo7.space_probe.domain.Enum.Direction;
import com.elo7.space_probe.domain.Obstacle;
import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.infra.repository.SpringDataProbeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProbeMovementServiceTest {

    @InjectMocks
    private ProbeMovementService probeMovementService;

    @Mock
    private SpringDataProbeRepository probeRepository;

    @Mock
    private Planet planet;

    private Probe probe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        probe = new Probe("Test Probe", 1, 1, planet, Direction.NORTH);

        when(planet.getWidth()).thenReturn(5);
        when(planet.getHeight()).thenReturn(5);

        List<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(2, 2));
        when(planet.getObstacles()).thenReturn(obstacles);
    }

    @Test
    void testMoveWithinBounds() {
        probeMovementService.move(probe, "M");

        assertEquals(1, probe.getPosition().getX());
        assertEquals(2, probe.getPosition().getY());
    }

    @Test
    void testMoveOutOfBounds() {
        var exception = assertThrows(RuntimeException.class, () -> {
            probeMovementService.move(probe, "MMMMM");
        });

        assertTrue(exception.getMessage().contains("fora dos limites"));
    }

    @Test
    void testMoveAndSave() {
        probeMovementService.move(probe, "M");
        verify(probeRepository).save(probe);
    }
}