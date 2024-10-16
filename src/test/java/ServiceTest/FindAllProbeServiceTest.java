package ServiceTest;

import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.app.probes.FindAllProbeService;
import com.elo7.space_probe.domain.Probes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class FindAllProbeServiceTest {

    @InjectMocks
    private FindAllProbeService findAllProbeService;

    @Mock
    private Probes probes;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllProbes() {
        List<Probe> probeList = List.of(new Probe(), new Probe());
        when(probes.findAll()).thenReturn(probeList);

        List<Probe> result = findAllProbeService.execute();
        assertEquals(2, result.size());
    }
}