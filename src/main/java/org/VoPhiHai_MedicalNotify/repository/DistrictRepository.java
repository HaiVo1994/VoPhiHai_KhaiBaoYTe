package org.VoPhiHai_MedicalNotify.repository;

import org.VoPhiHai_MedicalNotify.model.District;
import org.VoPhiHai_MedicalNotify.model.Province;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DistrictRepository extends CrudRepository<District, Long> {
    List<District> findAllByProvince(Province province);
}
