package org.VoPhiHai_MedicalNotify.formatter;

import org.VoPhiHai_MedicalNotify.model.Province;
import org.VoPhiHai_MedicalNotify.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ProvinceFormatter implements Formatter<Province> {
    private ProvinceService provinceService;

    @Autowired
    public ProvinceFormatter(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @Autowired


    @Override
    public Province parse(String text, Locale locale) throws ParseException {
        return provinceService.findById(Integer.parseInt(text));
    }

    @Override
    public String print(Province province, Locale locale) {
        return "[" + province.getId() + ", " + province.getName() + "]";
    }
}
