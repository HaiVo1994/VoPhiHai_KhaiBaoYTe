package org.VoPhiHai_MedicalNotify.service.impl;

import org.VoPhiHai_MedicalNotify.model.District;
import org.VoPhiHai_MedicalNotify.repository.DistrictRepository;
import org.VoPhiHai_MedicalNotify.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;
    @Override
    public Iterable<District> findAll() {
        return districtRepository.findAll();
    }

    @Override
    public List<District> findByProvince(Integer id) {
        return null;
    }

    @Override
    public District findById(Long id) {
        return districtRepository.findById(id).orElse(null);
    }
}
