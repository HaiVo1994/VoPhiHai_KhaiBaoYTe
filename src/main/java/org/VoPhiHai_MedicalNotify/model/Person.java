package org.VoPhiHai_MedicalNotify.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(length = 36)
    private String id;
    @Column(length = 200)
    private String name;
    private short birthYear;
    private byte gender;
    private String legalDocument;


}
