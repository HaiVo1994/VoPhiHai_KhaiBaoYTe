package org.VoPhiHai_MedicalNotify.formatter;

import org.VoPhiHai_MedicalNotify.model.Exposure;
import org.VoPhiHai_MedicalNotify.service.ExposureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ExposureFormatter implements Formatter<Exposure> {
    private ExposureService exposureService;
    @Autowired
    public ExposureFormatter(ExposureService exposureService){
        this.exposureService = exposureService;
    }
    @Override
    public Exposure parse(String text, Locale locale) throws ParseException {
        return exposureService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Exposure exposure, Locale locale) {
        return "[" + exposure.getId() + ", " + exposure.getName() + "]";
    }
}
