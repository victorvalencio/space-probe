package ServiceTest;

import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.infra.repository.SpringDataProbeRepository;
import com.elo7.space_probe.app.probes.ProbeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class ProbeServiceTest {

    @InjectMocks
    private ProbeService probeService;

    @Mock
    private SpringDataProbeRepository probeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindProbeById() {
        var probe = new Probe();
        when(probeRepository.findById(1)).thenReturn(Optional.of(probe));

        Optional<Probe> result = probeService.findById(1);
        assertTrue(result.isPresent());
    }

    @Test
    void testSaveProbe() {
        var probe = new Probe();
        when(probeRepository.save(probe)).thenReturn(probe);

        var result = probeService.save(probe);
        assertNotNull(result);
    }
}