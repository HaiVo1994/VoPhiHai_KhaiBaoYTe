package org.VoPhiHai_MedicalNotify.repository;

import org.VoPhiHai_MedicalNotify.model.Exposure;
import org.VoPhiHai_MedicalNotify.model.Symptom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SymptomRepository extends CrudRepository<Symptom,Long> {
    @Query("SELECT s FROM Symptom s " +
            "WHERE s.isEnabled = true")
    List<Symptom> getAllEnabled();
    @Query("SELECT s FROM Symptom s " +
            "WHERE s.name=:name " +
            "AND s.isEnabled = true ")
    Symptom findByNameAndEnabled(@Param("name") String name);
}
