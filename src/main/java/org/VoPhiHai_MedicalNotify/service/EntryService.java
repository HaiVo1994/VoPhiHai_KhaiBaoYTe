package org.VoPhiHai_MedicalNotify.service;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.Entry;
import org.VoPhiHai_MedicalNotify.model.Person;
import org.VoPhiHai_MedicalNotify.model.Transport;
import org.VoPhiHai_MedicalNotify.model.support.Statistical;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public interface EntryService {
    Entry findById(String id);
    Entry create(Entry entry);
    Entry create(Entry entry, String helpDeclareName);
    Entry create(LinkedHashMap<String, String> jsonEntry, Person person, Transport transport);
    Entry update(Entry entry, String updateName);

    List<Statistical> statisticalSymptomDeparture(Date begin, Date end);
    List<Statistical> statisticalSymptomDeparture(JsonObject timeFind);

}
