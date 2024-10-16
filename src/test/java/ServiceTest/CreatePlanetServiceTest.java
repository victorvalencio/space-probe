package ServiceTest;

import com.elo7.space_probe.app.planets.CreatePlanetService;
import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Planets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CreatePlanetServiceTest {

    @InjectMocks
    private CreatePlanetService createPlanetService;

    @Mock
    private Planets planetsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePlanet() {
        var planet = new Planet("Mars", 5, 5);
        createPlanetService.execute(planet);
        verify(planetsRepository, times(1)).save(planet);
    }
}