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
    public Contact create(Contact contact) {
        contact.setCreate_at(new Date());
        return contactRepository.save(contact);
    }

    @Override
    public Contact create(Contact contact, String nameHelper) {
        contact.setCreate_by(nameHelper);
        return this.create(contact);
    }

    @Override
    public Contact change(Contact contact, Person person) {
        Contact currentContact = this.findCurrentContactByPerson(person);
        if (currentContact!=null){
            if (
                    (currentContact.getEmail().equals(contact.getAddress())) &&
                            (currentContact.getEmail().equals(contact.getEmail())) &&
                            (currentContact.getName().equals(contact.getName())) &&
                            (currentContact.getLocation() == contact.getLocation()) &&
                            (currentContact.getPhone().equals(contact.getPhone()))
            ){
                return currentContact;
            }
            else {
                currentContact.setDisable_at(new Date());
                currentContact.setDisable_by(contact.getCreate_by());
                currentContact.setEnabled(false);
                contactRepository.save(currentContact);
            }
        }
        return this.create(contact);
    }

    @Override
    public Contact change(Contact contact, Person person, String nameHelper) {
        contact.setCreate_by(nameHelper);
        return this.change(contact,person);
    }
}
