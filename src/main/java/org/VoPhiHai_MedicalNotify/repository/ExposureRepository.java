package org.VoPhiHai_MedicalNotify.repository;

import org.VoPhiHai_MedicalNotify.model.Exposure;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExposureRepository extends CrudRepository<Exposure,Long> {
    @Query("SELECT e FROM Exposure e " +
            "WHERE e.isEnabled = true")
    List<Exposure> getAllEnabled();
    @Query("SELECT e FROM Exposure e " +
            "WHERE e.name=:name " +
            "AND e.isEnabled = true")
    Exposure findByName(@Param("name") String name);
}
