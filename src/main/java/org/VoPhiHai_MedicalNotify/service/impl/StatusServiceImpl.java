package org.VoPhiHai_MedicalNotify.service.impl;

import org.VoPhiHai_MedicalNotify.model.Entry;
import org.VoPhiHai_MedicalNotify.model.Status;
import org.VoPhiHai_MedicalNotify.model.Symptom;
import org.VoPhiHai_MedicalNotify.repository.StatusRepository;
import org.VoPhiHai_MedicalNotify.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;
    @Override
    public Status create(Status status, Entry entry, Symptom symptom) {
        status.setEntry(entry);
        status.setSymptom(symptom);
        return statusRepository.save(status);
    }

    @Override
    public void declare(List<Status> statuses, Entry entry, Symptom symptom) {
        Date current = new Date();
        for(Status status: statuses){
            status.setDateDeclare(current);
            this.create(status,entry,symptom);
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
