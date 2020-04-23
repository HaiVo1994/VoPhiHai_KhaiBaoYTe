package org.VoPhiHai_MedicalNotify.repository;

import org.VoPhiHai_MedicalNotify.model.Contact;
import org.VoPhiHai_MedicalNotify.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    @Query("SELECT c FROM Contact c " +
            "WHERE c.isEnabled = true AND c.person = :person")
    Contact findCurrentContactByPerson(@Param("person") Person person);
    Contact findContactByPhoneAndEmail(String phone, String email);
}
