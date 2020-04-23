package org.VoPhiHai_MedicalNotify.service.impl;

import org.VoPhiHai_MedicalNotify.model.Gate;
import org.VoPhiHai_MedicalNotify.repository.GateRepository;
import org.VoPhiHai_MedicalNotify.service.GateService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GateServiceImpl implements GateService {
    @Autowired
    private GateRepository gateRepository;
    @Override
    public Iterable<Gate> findAll() {
        return gateRepository.findAll();
    }

    @Override
    public Gate findById(Short id) {
        return gateRepository.findById(id).orElse(null);
    }

    @Override
    public List<HashMap<String, Long>> countPersonEntry(Date begin, Date end) {
        return null;
    }

    @Override
    public List<HashMap<String, Long>> countPersonSymptom(Date begin, Date end) {
        return null;
    }

    @Override
    public List<HashMap<String, Long>> countPersonExposure(Date begin, Date end) {
        return null;
    }
}
