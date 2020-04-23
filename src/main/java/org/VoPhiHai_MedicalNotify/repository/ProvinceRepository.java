package org.VoPhiHai_MedicalNotify.repository;

import org.VoPhiHai_MedicalNotify.model.National;
import org.VoPhiHai_MedicalNotify.model.Province;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProvinceRepository extends CrudRepository<Province,Integer> {
    List<Province> findAllByNational(National national);
}
