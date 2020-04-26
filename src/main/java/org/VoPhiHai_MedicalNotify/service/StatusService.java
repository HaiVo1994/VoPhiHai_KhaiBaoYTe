package org.VoPhiHai_MedicalNotify.service;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.Entry;
import org.VoPhiHai_MedicalNotify.model.Status;
import org.VoPhiHai_MedicalNotify.model.Symptom;

import java.util.Date;
import java.util.List;

public interface StatusService {
    Status create(Status status, Entry entry, Symptom symptom);
    Status create(Status status);
    void declare(List<Status> statuses, Entry entry);
    void declare(List<JsonObject> statusJson, String entryId);

    List<Entry> getListEntryHaveSymptom(Date begin, Date end);
    List<Entry> getListEntryHaveSymptomById(Date begin, Date end, Long symptomId);
}
