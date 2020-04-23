package org.VoPhiHai_MedicalNotify.repository;

import org.VoPhiHai_MedicalNotify.model.Exposure;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExposureRepository extends CrudRepository<Exposure,Long> {
    @Query("SELECT e FROM Exposure e " +
            "WHERE e.isEnabled = true")
    List<Exposure> getAllEnabled();
}
