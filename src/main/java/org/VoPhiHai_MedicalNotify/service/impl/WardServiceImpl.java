package org.VoPhiHai_MedicalNotify.service.impl;

import org.VoPhiHai_MedicalNotify.model.District;
import org.VoPhiHai_MedicalNotify.model.Ward;
import org.VoPhiHai_MedicalNotify.repository.WardRepository;
import org.VoPhiHai_MedicalNotify.service.DistrictService;
import org.VoPhiHai_MedicalNotify.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WardServiceImpl implements WardService {
    @Autowired
    private WardRepository wardRepository;
    @Override
    public Iterable<Ward> findAll() {
        return wardRepository.findAll();
    }

    @Autowired
    private DistrictService districtService;
    @Override
    public List<Ward> findByDistrict(Long id) {
        District district = districtService.findById(id);
        if (district!=null){
            return this.findByDistrict(district);
        }
        return null;
    }

    @Override
    public List<Ward> findByDistrict(District district) {
        return wardRepository.findAllByDistrict(district);
    }

    @Override
    public Ward findById(Long id) {
        return wardRepository.findById(id).orElse(null);
    }
}
