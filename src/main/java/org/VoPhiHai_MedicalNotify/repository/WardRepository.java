package org.VoPhiHai_MedicalNotify.repository;

import org.VoPhiHai_MedicalNotify.model.District;
import org.VoPhiHai_MedicalNotify.model.Ward;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WardRepository extends CrudRepository<Ward,Long> {
    List<Ward> findAllByDistrict(District district);
}
