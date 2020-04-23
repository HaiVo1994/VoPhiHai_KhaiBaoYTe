package org.VoPhiHai_MedicalNotify.service.impl;

import org.VoPhiHai_MedicalNotify.model.Contact;
import org.VoPhiHai_MedicalNotify.model.Entry;
import org.VoPhiHai_MedicalNotify.repository.EntryRepository;
import org.VoPhiHai_MedicalNotify.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class EntryServiceImpl implements EntryService {
    @Autowired
    private EntryRepository entryRepository;

    @Override
    public Entry findById(String id) {
        return entryRepository.findById(id).orElse(null);
    }

    @Override
    public Entry create(Entry entry) {
        entry.setCreateAt(new Date());
        return entryRepository.save(entry);
    }

    @Override
    public Entry create(Entry entry, String helpDeclareName) {
        entry.setCreateBy(helpDeclareName);
        return this.create(entry);
    }

    @Override
    public Entry update(Entry entry, String updateName) {
        entry.setUpdateAt(new Date());
        entry.setCreateBy(updateName);
        if (this.findById(entry.getId()) != null){
            return entryRepository.save(entry);
        }
        return null;
    }
}
