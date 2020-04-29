package org.VoPhiHai_MedicalNotify.service.impl;

import org.VoPhiHai_MedicalNotify.model.National;
import org.VoPhiHai_MedicalNotify.model.Person;
import org.VoPhiHai_MedicalNotify.repository.PersonRepository;
import org.VoPhiHai_MedicalNotify.service.NationalService;
import org.VoPhiHai_MedicalNotify.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person save(Person person) {
//        person.setCreateAt(new Date());
//        person.setCreateBy(person.getName());
        return personRepository.save(person);
    }

    @Override
    public Person create(Person person, String declareName) {
//        person.setCreateAt(new Date());
        person.setCreateBy(declareName);
        return this.save(person);
    }

    @Override
    public Person declare(Person person) {
        Person personDeclared = this.findByPassport(person.getPassport());
        if (personDeclared!= null)
        {
            if (personDeclared.getLegalDocument().equals(person.getLegalDocument())){
                return null;
            }
            if (personDeclared.equals(person))
                return personDeclared;
            else {
                personDeclared.setEnabled(false);
                personDeclared.setUpdateAt(new Date());
                personDeclared.setUpdateBy(person.getCreateBy());
                this.save(personDeclared);
            }
        }
        return this.save(person);
    }

    @Override
    public Person declare(Person person, String declareName) {
        //Fix late
        Person personDeclared = this.findByPassport(person.getPassport());
        if (personDeclared!= null)
            return personDeclared;
        else {
            return this.create(person, declareName);
        }
    }

    @Autowired
    private NationalService nationalService;
    @Override
    public Person declare(LinkedHashMap<String, String> personJson) {
        Person person = new Person();
        String passport = (String) personJson.get("legalDocument");
        person.setPassport(passport);
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd_MM_yyyy");
        Date today = new Date();
        person.setLegalDocument(simpleFormat.format(today) + "|" + passport);

        String name = (String) personJson.get("name");
        person.setName(name);
        person.setBirthYear(Short.parseShort((String) personJson.get("birthYear")));
        person.setGender(Byte.parseByte((String) personJson.get("gender")));
        National national = nationalService.findById(Short.parseShort((String) personJson.get("Nationality")));
        person.setNationality(national);
        person.setCreateAt(today);
        person.setCreateBy(name);
        return this.declare(person);
    }

    @Override
    public Person update(Person person, String updateBy) {
        if (personRepository.findById(person.getLegalDocument()).orElse(null) != null){
            person.setUpdateAt(new Date());
            person.setUpdateBy(updateBy);
            return this.save(person);
        }
        return null;
    }

    @Override
    public Person findByPassport(String passport) {
        List<Person> personList = personRepository.findByPassport(passport);
        if (personList.size()>0)
            return personList.get(0);
        else
            return null;
//        return personRepository.findByPassportAndEnabled(passport,true);
    }

    @Override
    public List<Person> findAllByNationality(Short idNational) {
        return null;
    }
}
