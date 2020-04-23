package org.VoPhiHai_MedicalNotify.service.impl;

import org.VoPhiHai_MedicalNotify.model.Exposure;
import org.VoPhiHai_MedicalNotify.repository.ExposureRepository;
import org.VoPhiHai_MedicalNotify.service.ExposureService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ExposureServiceImpl implements ExposureService {
    @Autowired
    private ExposureRepository exposureRepository;
    @Override
    public Iterable<Exposure> findAll() {
        return exposureRepository.findAll();
    }

    @Override
    public Exposure findById(Long id) {
        return exposureRepository.findById(id).orElse(null);
    }

    @Override
    public List<Exposure> findAllEnable() {
        return exposureRepository.getAllEnabled();
    }

    @Override
    public List<HashMap<String, Long>> countPersonHaveExposure(Date begin, Date end) {
        return null;
    }
}
