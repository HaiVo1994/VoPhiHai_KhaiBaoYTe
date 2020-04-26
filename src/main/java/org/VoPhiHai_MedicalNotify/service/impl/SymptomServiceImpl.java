package org.VoPhiHai_MedicalNotify.service.impl;

import org.VoPhiHai_MedicalNotify.model.Symptom;
import org.VoPhiHai_MedicalNotify.repository.SymptomRepository;
import org.VoPhiHai_MedicalNotify.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SymptomServiceImpl implements SymptomService {
    @Autowired
    private SymptomRepository symptomRepository;

    @Override
    public Iterable<Symptom> findAll() {
        return symptomRepository.findAll();
    }

    @Override
    public Symptom findById(Long id) {
        return symptomRepository.findById(id).orElse(null);
    }

    @Override
    public List<Symptom> findAllEnable() {
        return symptomRepository.getAllEnabled();
    }

    @Override
    public HashMap<String, Symptom> findMapEnable() {
        List<Symptom> symptoms = this.findAllEnable();
        HashMap<String, Symptom> mapSymptom = new HashMap<>();
        for (Symptom symptom: symptoms){
            mapSymptom.put(String.valueOf(symptom.getId()),symptom);
        }
        return mapSymptom;
    }

    @Override
    public List<HashMap<String, Long>> countPersonHaveSymptom(Date begin, Date end) {
        return null;
    }
}
