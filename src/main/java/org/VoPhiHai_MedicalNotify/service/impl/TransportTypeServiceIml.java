package org.VoPhiHai_MedicalNotify.service.impl;

import org.VoPhiHai_MedicalNotify.model.TransportType;
import org.VoPhiHai_MedicalNotify.repository.TransportTypeRepository;
import org.VoPhiHai_MedicalNotify.service.TransportTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TransportTypeServiceIml implements TransportTypeService {
    @Autowired
    private TransportTypeRepository transportTypeRepository;

    @Override
    public Iterable<TransportType> getAll() {
        return transportTypeRepository.findAll();
    }

    @Override
    public TransportType findById(Short id) {
        return transportTypeRepository.findById(id).orElse(null);
    }

    @Override
    public List<HashMap<String, Long>> countPerson(Date begin, Date end) {
        return null;
    }

    @Override
    public List<HashMap<String, Long>> countPersonHaveExposure(Date begin, Date end) {
        return null;
    }

    @Override
    public List<HashMap<String, Long>> countPersonHaveSymptom(Date begin, Date end) {
        return null;
    }
}
