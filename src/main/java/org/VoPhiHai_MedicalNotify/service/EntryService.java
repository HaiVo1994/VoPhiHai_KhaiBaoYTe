package org.VoPhiHai_MedicalNotify.service;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.Entry;
import org.VoPhiHai_MedicalNotify.model.Person;
import org.VoPhiHai_MedicalNotify.model.Transport;
import org.VoPhiHai_MedicalNotify.model.support.Statistical;
import org.VoPhiHai_MedicalNotify.model.support.Statistical_Entry;
import org.VoPhiHai_MedicalNotify.model.support.Statistical_Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    List<Statistical_Entry> statisticalEntry(Date begin, Date end);
    List<Statistical_Entry> statisticalEntry(JsonObject timeFind);

    Page<Statistical_Person> getByImmigrationDate(Date begin, Date end, Pageable pageable);
    Page<Statistical_Person> getByImmigrationDate(JsonObject timeFind, int page, int size);
}
