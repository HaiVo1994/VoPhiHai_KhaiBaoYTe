package org.VoPhiHai_MedicalNotify.service;

import org.VoPhiHai_MedicalNotify.model.District;
import org.VoPhiHai_MedicalNotify.model.Province;

import java.util.List;

public interface DistrictService {
    Iterable<District> findAll();
    List<District> findByProvince(Integer id);
    List<District> findByProvince(Province province);
    District findById(Long id);
}
