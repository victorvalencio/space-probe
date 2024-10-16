package ServiceTest;

import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.app.probes.FindProbeService;
import com.elo7.space_probe.domain.Probes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class FindProbeServiceTest {

    @InjectMocks
    private FindProbeService findProbeService;

    @Mock
    private Probes probes;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindProbeById() {
        var probe = new Probe();
        when(probes.findById(1)).thenReturn(Optional.of(probe));

        Optional<Probe> result = findProbeService.execute(1);
        assertTrue(result.isPresent());
    }
}