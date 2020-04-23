package org.VoPhiHai_MedicalNotify.service;

import org.VoPhiHai_MedicalNotify.model.District;

import java.util.List;

public interface DistrictService {
    Iterable<District> findAll();
    List<District> findByProvince(Integer id);
    District findById(Long id);
}
