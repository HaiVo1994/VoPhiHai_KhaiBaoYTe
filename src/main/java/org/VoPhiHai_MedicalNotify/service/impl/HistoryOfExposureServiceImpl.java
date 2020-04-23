package org.VoPhiHai_MedicalNotify.service.impl;

import org.VoPhiHai_MedicalNotify.model.Entry;
import org.VoPhiHai_MedicalNotify.model.Exposure;
import org.VoPhiHai_MedicalNotify.model.HistoryOfExposure;
import org.VoPhiHai_MedicalNotify.repository.HistoryOfExposureRepository;
import org.VoPhiHai_MedicalNotify.service.HistoryOfExposureService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class HistoryOfExposureServiceImpl implements HistoryOfExposureService {
    @Autowired
    private HistoryOfExposureRepository historyOfExposureRepository;
    @Override
    public HistoryOfExposure create(HistoryOfExposure historyOfExposure, Entry entry, Exposure exposure) {
        historyOfExposure.setEntry(entry);
        historyOfExposure.setExposure(exposure);
        return historyOfExposureRepository.save(historyOfExposure);
    }

    @Override
    public void declare(List<HistoryOfExposure> historyOfExposures, Entry entry, Exposure exposure) {
        Date current = new Date();
        for (HistoryOfExposure historyOfExposure: historyOfExposures) {
            historyOfExposure.setDateDeclare(current);
            this.create(historyOfExposure, entry, exposure);
        }
    }


    @Override
    public List<Entry> getListEntryHaveExposure(Date begin, Date end) {
        return null;
    }

    @Override
    public List<Entry> getListEntryHaveExposureById(Date begin, Date end, Long exposureId) {
        return null;
    }
}
