package com.elo7.space_probe.ui;

import com.elo7.space_probe.domain.Planet;
import com.elo7.space_probe.domain.Enum.Direction;
import com.elo7.space_probe.ui.probes.ProbeCreateDTO;
import com.elo7.space_probe.ui.probes.ProbeCreateDTOToModelConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProbeCreateDTOToModelConverterTest {

    private ProbeCreateDTOToModelConverter probeCreateDTOToModelConverter;

    @BeforeEach
    void setUp() {
        probeCreateDTOToModelConverter = new ProbeCreateDTOToModelConverter();
    }

    @Test
    void testConvertProbeCreateDTOToProbe() {
        var planet = new Planet("Mars", 5, 5);
        planet.setId(1);

        var probeCreateDTO = new ProbeCreateDTO("Probe1", 1, 2, 1, Direction.NORTH);

        var probe = probeCreateDTOToModelConverter.convert(probeCreateDTO, planet);

        assertEquals("Probe1", probe.getName());
        assertEquals(1, probe.getPosition().getX());
        assertEquals(2, probe.getPosition().getY());
        assertEquals(Direction.NORTH, probe.getDirection());
        assertEquals(planet, probe.getPlanet());

        assertEquals(1, probe.getPlanet().getId());
    }
}
