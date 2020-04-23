package org.VoPhiHai_MedicalNotify.service;

import org.VoPhiHai_MedicalNotify.model.Entry;

public interface EntryService {
    Entry findById(String id);
    Entry create(Entry entry);
    Entry create(Entry entry, String helpDeclareName);
    Entry update(Entry entry, String updateName);
}
