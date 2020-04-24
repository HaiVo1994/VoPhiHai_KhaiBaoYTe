package org.VoPhiHai_MedicalNotify.service.impl;

import org.VoPhiHai_MedicalNotify.model.District;
import org.VoPhiHai_MedicalNotify.model.Province;
import org.VoPhiHai_MedicalNotify.repository.DistrictRepository;
import org.VoPhiHai_MedicalNotify.service.DistrictService;
import org.VoPhiHai_MedicalNotify.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;
    @Override
    public Iterable<District> findAll() {
        return districtRepository.findAll();
    }

    @Autowired
    private ProvinceService provinceService;
    @Override
    public List<District> findByProvince(Integer id) {
        Province province = provinceService.findById(id);
        if (province!=null)
            return this.findByProvince(province);
        return null;
    }

    @Override
    public List<District> findByProvince(Province province) {
        return districtRepository.findAllByProvince(province);
    }

    @Override
    public District findById(Long id) {
        return districtRepository.findById(id).orElse(null);
    }
}
