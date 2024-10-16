package ControllerTest;

import com.elo7.space_probe.app.planets.FindPlanetService;
import com.elo7.space_probe.app.probes.*;
import com.elo7.space_probe.domain.Enum.Direction;
import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.ui.probes.*;
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
import static org.mockito.Mockito.*;

class ProbeControllerTest {

    @InjectMocks
    private ProbeController probeController;

    @Mock
    private CreateProbeService createProbeService;

    @Mock
    private FindProbeService findProbeService;

    @Mock
    private FindPlanetService findPlanetService;

    @Mock
    private FindAllProbeService findAllProbeService;

    @Mock
    private ProbeCreateDTOToModelConverter probeCreateDTOToModelConverter;

    @Mock
    private ProbeToDtoConverter probeToDtoConverter;

    @Mock
    private ProbeMovementService probeMovementService;

    @Mock
    private ProbeService probeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllProbes() {
        List<Probe> mockProbes = List.of(new Probe(), new Probe());
        when(findAllProbeService.execute()).thenReturn(mockProbes);

        when(probeToDtoConverter.convert(any(Probe.class))).thenReturn(new ProbeDTO(1, "Probe1", 1, 1, 1));

        List<ProbeDTO> result = probeController.findAll();

        assertEquals(2, result.size());
        verify(findAllProbeService).execute();
    }

    @Test
    void testFindProbeById() {
        var probe = new Probe();
        when(findProbeService.execute(1)).thenReturn(Optional.of(probe));

        when(probeToDtoConverter.convert(any(Probe.class))).thenReturn(new ProbeDTO(1, "Probe1", 1, 1, 1));

        var result = probeController.findById(1);

        assertNotNull(result);
        verify(findProbeService).execute(1);
    }

    @Test
    void testCreateProbe() {
        var planet = new Planet("Planet1", 5, 5);
        when(findPlanetService.execute(1)).thenReturn(Optional.of(planet));

        var probeCreateDTO = new ProbeCreateDTO("Probe1", 1, 1, 1, Direction.NORTH);
        var probe = new Probe("Probe1", 1, 1, planet, Direction.NORTH);
        when(probeCreateDTOToModelConverter.convert(probeCreateDTO, planet)).thenReturn(probe);

        when(createProbeService.execute(probe)).thenReturn(probe);

        when(probeToDtoConverter.convert(probe)).thenReturn(new ProbeDTO(1, "Probe1", 1, 1, 1));

        var result = probeController.create(probeCreateDTO);

        assertNotNull(result);
        verify(createProbeService).execute(probe);
    }

    @Test
    void testMoveProbe() {
        var probe = new Probe();
        when(probeService.findById(1)).thenReturn(Optional.of(probe));

        probeController.moveProbe(1, "M");

        verify(probeMovementService).move(probe, "M");

        verify(probeService).save(probe);
    }

    @Test
    void testMoveProbeNotFound() {
        when(probeService.findById(1)).thenReturn(Optional.empty());

        var exception = assertThrows(ResponseStatusException.class, () -> {
            probeController.moveProbe(1, "M");
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Probe not found", exception.getReason());
    }
}
