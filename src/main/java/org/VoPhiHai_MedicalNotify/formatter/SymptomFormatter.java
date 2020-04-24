package org.VoPhiHai_MedicalNotify.formatter;

import org.VoPhiHai_MedicalNotify.model.Symptom;
import org.VoPhiHai_MedicalNotify.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class SymptomFormatter implements Formatter<Symptom> {
    private SymptomService symptomService;
    @Autowired
    public SymptomFormatter(SymptomService symptomService) {
        this.symptomService = symptomService;
    }

    @Override
    public Symptom parse(String text, Locale locale) throws ParseException {
        return symptomService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Symptom symptom, Locale locale) {
        return "[" + symptom.getId() + ", " + symptom.getName() + "]";
    }
}
