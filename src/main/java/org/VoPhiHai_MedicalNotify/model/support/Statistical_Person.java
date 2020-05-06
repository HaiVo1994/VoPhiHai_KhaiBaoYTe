package org.VoPhiHai_MedicalNotify.model.support;

import java.util.Date;

public class Statistical_Person {
    private String name;
    private String idPerson;
    private Date date;
    private Long value;

    public Statistical_Person(String name, String idPerson, Date date) {
        this.name = name;
        this.idPerson = idPerson;
        this.date = date;
    }

    public Statistical_Person(String name, String idPerson, Date date, Long value) {
        this.name = name;
        this.idPerson = idPerson;
        this.date = date;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
