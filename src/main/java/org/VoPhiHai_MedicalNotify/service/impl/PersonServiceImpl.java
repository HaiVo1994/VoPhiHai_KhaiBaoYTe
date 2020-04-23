package org.VoPhiHai_MedicalNotify.service.impl;

import org.VoPhiHai_MedicalNotify.model.Person;
import org.VoPhiHai_MedicalNotify.repository.PersonRepository;
import org.VoPhiHai_MedicalNotify.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person create(Person person) {
        person.setCreateAt(new Date());
        person.setCreateBy(person.getName());
        return personRepository.save(person);
    }

    @Override
    public Person create(Person person, String declareName) {
        person.setCreateAt(new Date());
        person.setCreateBy(declareName);
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person, String updateBy) {
        if (this.findByLegalDocument(person.getLegalDocument()) != null){
            person.setUpdateAt(new Date());
            person.setUpdateBy(updateBy);
            return personRepository.save(person);
        }
        return null;
    }

    @Override
    public Person findByLegalDocument(String legalDocument) {
        return personRepository.findById(legalDocument).orElse(null);
    }

    @Override
    public List<Person> findAllByNationality(Short idNational) {
        return null;
    }
}
