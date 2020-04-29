package org.VoPhiHai_MedicalNotify.service.impl;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.Entry;
import org.VoPhiHai_MedicalNotify.model.Exposure;
import org.VoPhiHai_MedicalNotify.model.HistoryOfExposure;
import org.VoPhiHai_MedicalNotify.repository.HistoryOfExposureRepository;
import org.VoPhiHai_MedicalNotify.service.EntryService;
import org.VoPhiHai_MedicalNotify.service.ExposureService;
import org.VoPhiHai_MedicalNotify.service.HistoryOfExposureService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class HistoryOfExposureServiceImpl implements HistoryOfExposureService {
    @Autowired
    private HistoryOfExposureRepository historyOfExposureRepository;

    @Override
    public HistoryOfExposure create(HistoryOfExposure historyOfExposure) {
        return historyOfExposureRepository.save(historyOfExposure);
    }

    @Override
    public HistoryOfExposure create(HistoryOfExposure historyOfExposure, Entry entry, Exposure exposure) {
        historyOfExposure.setEntry(entry);
        historyOfExposure.setExposure(exposure);
        return this.create(historyOfExposure);
    }

    @Override
    public List<HistoryOfExposure> declare(List<HistoryOfExposure> historyOfExposures, Entry entry, Exposure exposure) {
        Date current = new Date();
        //Fix late
        for (HistoryOfExposure historyOfExposure: historyOfExposures) {
            historyOfExposure.setDateDeclare(current);
            this.create(historyOfExposure, entry, exposure);
        }
        return null;
    }

    @Autowired
    private EntryService entryService;
    @Autowired
    private ExposureService exposureService;
    @Override
    public List<HistoryOfExposure> declare(List<LinkedHashMap<String,String>> historyOfExposures, Entry entry) {
        Date current = new Date();
//        Entry entry = entryService.findById(entryId);
        if (entry!=null){
            HistoryOfExposure historyOfExposure;
            HashMap<String, Exposure> mapExposure = exposureService.mapEnable();
            String checkExposure;
            List<HistoryOfExposure> historyOfExposureList = new ArrayList<>();
            for (LinkedHashMap<String,String> history: historyOfExposures){
                historyOfExposure = new HistoryOfExposure();
                historyOfExposure.setDateDeclare(current);
                checkExposure = String.valueOf(history.get("hasExposure"));
                if (checkExposure.equals("1")){
                    historyOfExposure.setHasExposure(true);
                }
                else {
                    historyOfExposure.setHasExposure(false);
                }
                historyOfExposure = this.create(historyOfExposure, entry, mapExposure.get(String.valueOf(history.get("exposure"))));
                historyOfExposureList.add(historyOfExposure);
            }
            return historyOfExposureList;
        }
        return null;
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
