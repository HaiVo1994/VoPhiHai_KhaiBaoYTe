package org.VoPhiHai_MedicalNotify.service;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.Entry;

public interface EntryService {
    Entry findById(String id);
    Entry create(Entry entry);
    Entry create(Entry entry, String helpDeclareName);
    Entry create(JsonObject jsonEntry);
    Entry update(Entry entry, String updateName);
}
