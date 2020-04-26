package org.VoPhiHai_MedicalNotify.service;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.Contact;
import org.VoPhiHai_MedicalNotify.model.Person;

public interface ContactService {
    Contact findCurrentContactByPerson(Person person);
    Contact findByPhoneAndEmail(String phone, String email);
    Contact create(Contact contact);
    Contact create(Contact contact, String nameHelper);
    Contact change(Contact contact, Person person);
    Contact change(Contact contact, Person person, String nameHelper);
    Contact change(JsonObject jsonContact);
}
