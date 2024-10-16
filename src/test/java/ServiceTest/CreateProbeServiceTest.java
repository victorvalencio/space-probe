package ServiceTest;

import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.app.probes.CreateProbeService;
import com.elo7.space_probe.domain.Probes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class CreateProbeServiceTest {

    @InjectMocks
    private CreateProbeService createProbeService;

    @Mock
    private Probes probes;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProbe() {
        var probe = new Probe();
        when(probes.save(probe)).thenReturn(probe);

        var result = createProbeService.execute(probe);
        assertNotNull(result);
    }
}
