package org.VoPhiHai_MedicalNotify.service;

import org.VoPhiHai_MedicalNotify.model.Person;

import java.util.LinkedHashMap;
import java.util.List;

public interface PersonService {
    Person save(Person person);
    Person create(Person person, String declareName);
    Person declare(Person person);
    Person declare(Person person, String declareName);
    Person declare(LinkedHashMap<String, String> personJson);
    Person update(Person person, String updateBy);

    Person findByPassport(String passport);
    List<Person> findAllByNationality(Short idNational);
}
