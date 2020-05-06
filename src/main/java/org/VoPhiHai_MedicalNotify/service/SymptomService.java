package org.VoPhiHai_MedicalNotify.service;

import org.VoPhiHai_MedicalNotify.model.Symptom;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface SymptomService {
    Iterable<Symptom> findAll();
    Symptom findById(Long id);
    Symptom findByName(String name);
    List<Symptom> findAllEnable();
    HashMap<String, Symptom> findMapEnable();
    List<HashMap<String, Long>> countPersonHaveSymptom(Date begin, Date end);
}
