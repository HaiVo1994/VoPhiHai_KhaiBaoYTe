package org.VoPhiHai_MedicalNotify.service;

import org.VoPhiHai_MedicalNotify.model.Contact;
import org.VoPhiHai_MedicalNotify.model.Person;

public interface ContactService {
    Contact findCurrentContactByPerson(Person person);
    Contact findByPhoneAndEmail(String phone, String email);
    void create(Contact contact);
    void change(Contact contact, Person person);
}
