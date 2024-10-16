package ServiceTest;

import com.elo7.space_probe.app.planets.FindPlanetService;
import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Planets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class FindPlanetServiceTest {

    @InjectMocks
    private FindPlanetService findPlanetService;

    @Mock
    private Planets planets;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindPlanetById() {
        Planet planet = new Planet("Earth", 10, 10);
        when(planets.findById(1)).thenReturn(Optional.of(planet));

        Optional<Planet> result = findPlanetService.execute(1);
        assertTrue(result.isPresent());
    }
}