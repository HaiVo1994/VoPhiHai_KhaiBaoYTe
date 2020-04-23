package org.VoPhiHai_MedicalNotify.service;

import org.VoPhiHai_MedicalNotify.model.Entry;
import org.VoPhiHai_MedicalNotify.model.Status;
import org.VoPhiHai_MedicalNotify.model.Symptom;

import java.util.Date;
import java.util.List;

public interface StatusService {
    Status create(Status status, Entry entry, Symptom symptom);
    void declare(List<Status> statuses, Entry entry, Symptom symptom);

    List<Entry> getListEntryHaveSymptom(Date begin, Date end);
    List<Entry> getListEntryHaveSymptomById(Date begin, Date end, Long symptomId);
}
