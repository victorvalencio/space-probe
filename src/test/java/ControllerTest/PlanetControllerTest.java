package ControllerTest;

import com.elo7.space_probe.app.planets.CreatePlanetService;
import com.elo7.space_probe.app.planets.FindAllPlanetService;
import com.elo7.space_probe.app.planets.FindPlanetService;
import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.ui.planets.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PlanetControllerTest {

@InjectMocks
private PlanetController planetController;

@Mock
private CreatePlanetService createPlanetService;

@Mock
private FindPlanetService findPlanetService;

@Mock
private FindAllPlanetService findAllPlanetService;

@Mock
private PlanetCreateDTOToModelConverter planetCreateDTOToModelConverter;

@Mock
private PlanetToDtoConverter planetToDtoConverter;

@BeforeEach
void setUp() {
    MockitoAnnotations.openMocks(this);
}

@Test
void testFindAll() {
    var planet1 = new Planet("Earth", 10, 10);
    var planet2 = new Planet("Mars", 5, 5);
    when(findAllPlanetService.execute()).thenReturn(List.of(planet1, planet2));

    when(planetToDtoConverter.convert(planet1)).thenReturn(new PlanetDTO(1, "Earth", 10, 10, List.of()));
    when(planetToDtoConverter.convert(planet2)).thenReturn(new PlanetDTO(2, "Mars", 5, 5, List.of()));

    List<PlanetDTO> result = planetController.findAll();

    assertEquals(2, result.size());
    verify(findAllPlanetService).execute();
}

@Test
void testFindByIdSuccess() {
    var planet = new Planet("Earth", 10, 10);
    when(findPlanetService.execute(1)).thenReturn(Optional.of(planet));

    when(planetToDtoConverter.convert(planet)).thenReturn(new PlanetDTO(1, "Earth", 10, 10, List.of()));

    var result = planetController.findById(1);

    assertNotNull(result);
    assertEquals("Earth", result.name());
    verify(findPlanetService).execute(1);
}

@Test
void testFindByIdNotFound() {
    when(findPlanetService.execute(1)).thenReturn(Optional.empty());

    var exception = assertThrows(ResponseStatusException.class, () -> {
        planetController.findById(1);
    });

    assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    assertEquals("Planet not found", exception.getReason());
}

@Test
void testCreatePlanet() {
    var planetCreateDTO = new PlanetCreateDTO("Earth", 10, 10, List.of());
    var planet = new Planet("Earth", 10, 10);
    when(planetCreateDTOToModelConverter.convert(planetCreateDTO)).thenReturn(planet);

    when(createPlanetService.execute(planet)).thenReturn(planet);

    when(planetToDtoConverter.convert(planet)).thenReturn(new PlanetDTO(1, "Earth", 10, 10, List.of()));

    PlanetDTO result = planetController.create(planetCreateDTO);

    assertNotNull(result);
    assertEquals("Earth", result.name());
    verify(createPlanetService).execute(planet);
}
}

