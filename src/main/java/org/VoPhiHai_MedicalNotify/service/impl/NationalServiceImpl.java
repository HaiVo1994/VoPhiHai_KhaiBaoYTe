package org.VoPhiHai_MedicalNotify.service.impl;

import org.VoPhiHai_MedicalNotify.model.National;
import org.VoPhiHai_MedicalNotify.repository.NationalRepository;
import org.VoPhiHai_MedicalNotify.service.NationalService;
import org.springframework.beans.factory.annotation.Autowired;

public class NationalServiceImpl implements NationalService {
    @Autowired
    private NationalRepository nationalRepository;
    @Override
    public Iterable<National> findAll() {
        return nationalRepository.findAll();
    }

    @Override
    public National findById(Short id) {
        return nationalRepository.findById(id).orElse(null);
    }
}
