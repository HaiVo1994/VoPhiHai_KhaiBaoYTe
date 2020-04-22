package org.VoPhiHai_MedicalNotify.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entryId")
    private Entry entry;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "symptomId")
    private Symptom symptom;
    private boolean haveSymptom;
    private Date dateDeclare;

    public Status() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

    public boolean isHaveSymptom() {
        return haveSymptom;
    }

    public void setHaveSymptom(boolean haveSymptom) {
        this.haveSymptom = haveSymptom;
    }

    public Date getDateDeclare() {
        return dateDeclare;
    }

    public void setDateDeclare(Date dateDeclare) {
        this.dateDeclare = dateDeclare;
    }
}
