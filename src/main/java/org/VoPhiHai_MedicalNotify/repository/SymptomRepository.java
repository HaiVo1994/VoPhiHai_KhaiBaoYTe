package org.VoPhiHai_MedicalNotify.repository;

import org.VoPhiHai_MedicalNotify.model.Exposure;
import org.VoPhiHai_MedicalNotify.model.Symptom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SymptomRepository extends CrudRepository<Symptom,Long> {
    @Query("SELECT s FROM Symptom s " +
            "WHERE s.isEnabled = true")
    List<Symptom> getAllEnabled();
}
