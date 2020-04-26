package org.VoPhiHai_MedicalNotify.service.impl;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.Entry;
import org.VoPhiHai_MedicalNotify.model.Status;
import org.VoPhiHai_MedicalNotify.model.Symptom;
import org.VoPhiHai_MedicalNotify.repository.StatusRepository;
import org.VoPhiHai_MedicalNotify.service.EntryService;
import org.VoPhiHai_MedicalNotify.service.StatusService;
import org.VoPhiHai_MedicalNotify.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;
    @Override
    public Status create(Status status, Entry entry, Symptom symptom) {
        status.setEntry(entry);
        status.setSymptom(symptom);
        return this.create(status);
    }

    @Override
    public Status create(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public void declare(List<Status> statuses, Entry entry) {
        Date current = new Date();
        for(Status status: statuses){
            status.setDateDeclare(current);
            status.setEntry(entry);
            this.create(status);
        }
    }

    @Autowired
    private EntryService entryService;
    @Autowired
    private SymptomService symptomService;
    @Override
    public void declare(List<JsonObject> statusJson, String entryId) {
        Date current = new Date();
        HashMap<String, Symptom> mapSymptom = symptomService.findMapEnable();
        Entry entry = entryService.findById(entryId);
        if (entry!=null){
            Status status;
            for (JsonObject jsonObject: statusJson){
                status = new Status();
                status.setDateDeclare(current);
                status.setEntry(entry);
                status.setSymptom(mapSymptom.get((String.valueOf(jsonObject.get("symptom")))));
                status.setHaveSymptom((boolean)jsonObject.get("haveSymptom"));
                this.create(status);
            }
        }
    }

    @Override
    public List<Entry> getListEntryHaveSymptom(Date begin, Date end) {
        return null;
    }

    @Override
    public List<Entry> getListEntryHaveSymptomById(Date begin, Date end, Long symptomId) {
        return null;
    }
}
