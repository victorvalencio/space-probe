package ServiceTest;

import com.elo7.space_probe.app.planets.FindAllPlanetService;
import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Planets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class FindAllPlanetServiceTest {

    @InjectMocks
    private FindAllPlanetService findAllPlanetService;

    @Mock
    private Planets planets;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllPlanets() {
        List<Planet> planetList = List.of(new Planet("Earth", 10, 10), new Planet("Mars", 5, 5));
        when(planets.findAll()).thenReturn(planetList);

        List<Planet> result = findAllPlanetService.execute();
        assertEquals(2, result.size());
    }
}