package org.VoPhiHai_MedicalNotify.service.impl;

import org.VoPhiHai_MedicalNotify.model.Contact;
import org.VoPhiHai_MedicalNotify.model.Person;
import org.VoPhiHai_MedicalNotify.repository.ContactRepository;
import org.VoPhiHai_MedicalNotify.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Override
    public Contact findCurrentContactByPerson(Person person) {
        return contactRepository.findCurrentContactByPerson(person);
    }

    @Override
    public Contact findByPhoneAndEmail(String phone, String email) {
        return contactRepository.findContactByPhoneAndEmail(phone,email);
    }

    @Override
    public void create(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void change(Contact contact, Person person) {
        Contact currentContact = this.findCurrentContactByPerson(person);
        currentContact.setDisable_at(new Date());
        currentContact.setDisable_by(contact.getCreate_by());
        currentContact.setEnabled(false);
        contactRepository.save(currentContact);
        contactRepository.save(contact);
    }
}
