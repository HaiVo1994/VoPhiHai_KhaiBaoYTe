package org.VoPhiHai_MedicalNotify.formatter;

import org.VoPhiHai_MedicalNotify.model.Person;
import org.VoPhiHai_MedicalNotify.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class PersonFormatter implements Formatter<Person> {
    private PersonService personService;

    @Autowired
    public PersonFormatter(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public Person parse(String s, Locale locale) throws ParseException {
        return personService.findByPassport(s);
    }

    @Override
    public String print(Person person, Locale locale) {
        return "[" + person.getLegalDocument() + ", " + person.getName() + "]";
    }
}
