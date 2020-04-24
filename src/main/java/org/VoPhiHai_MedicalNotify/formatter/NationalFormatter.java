package org.VoPhiHai_MedicalNotify.formatter;

import org.VoPhiHai_MedicalNotify.model.National;
import org.VoPhiHai_MedicalNotify.service.NationalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class NationalFormatter implements Formatter<National> {
    private NationalService nationalService;

    @Autowired
    public NationalFormatter(NationalService nationalService) {
        this.nationalService = nationalService;
    }

    @Override
    public National parse(String text, Locale locale) throws ParseException {
        return nationalService.findById(Short.parseShort(text));
    }

    @Override
    public String print(National national, Locale locale) {
        return "[" + national.getId() + ", " + national.getName() + "]";
    }
}
