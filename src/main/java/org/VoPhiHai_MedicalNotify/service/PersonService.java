package org.VoPhiHai_MedicalNotify.service;

import org.VoPhiHai_MedicalNotify.model.Person;

import java.util.List;

public interface PersonService {
    Person create(Person person);
    Person create(Person person, String declareName);
    Person declare(Person person);
    Person declare(Person person, String declareName);
    Person update(Person person, String updateBy);

    Person findByLegalDocument(String legalDocument);
    List<Person> findAllByNationality(Short idNational);
}
