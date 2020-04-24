package org.VoPhiHai_MedicalNotify.service;

import org.VoPhiHai_MedicalNotify.model.District;
import org.VoPhiHai_MedicalNotify.model.Ward;

import java.util.List;

public interface WardService {
    Iterable<Ward> findAll();
    List<Ward> findByDistrict(Long id);
    List<Ward> findByDistrict(District district);
    Ward findById(Long id);
}
