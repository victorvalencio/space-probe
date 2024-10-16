package com.elo7.space_probe.app.probes;

import com.elo7.space_probe.domain.Probe;
import com.elo7.space_probe.infra.repository.SpringDataProbeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProbeService {

    private final SpringDataProbeRepository probeRepository;

    @Autowired
    public ProbeService(SpringDataProbeRepository probeRepository) {
        this.probeRepository = probeRepository;
    }

    public Optional<Probe> findById(Integer id) {
        return probeRepository.findById(id);
    }

    public Probe save(Probe probe) {
        return probeRepository.save(probe);
    }
}

