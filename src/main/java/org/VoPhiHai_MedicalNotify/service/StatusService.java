package org.VoPhiHai_MedicalNotify.service;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.Entry;
import org.VoPhiHai_MedicalNotify.model.Status;
import org.VoPhiHai_MedicalNotify.model.Symptom;
import org.VoPhiHai_MedicalNotify.model.support.Statistical;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public interface StatusService {
    Status create(Status status, Entry entry, Symptom symptom);
    Status create(Status status);
//    void declare(List<Status> statuses, Entry entry);
    List<Status> declare(List<LinkedHashMap<String,String>> statusJson, Entry entry);

    List<Entry> getListEntryHaveSymptom(Date begin, Date end);
    List<Entry> getListEntryHaveSymptomById(Date begin, Date end, Long symptomId);

    List<JsonObject> statisticalByCountSymptom(Date begin, Date end);
    List<JsonObject> statisticalByCountSymptom(JsonObject dateEntry);
    List<Statistical> statisticalByTypeSymptom(Date begin, Date end);
    List<Statistical> statisticalByTypeSymptom(JsonObject dateEntry);
}
