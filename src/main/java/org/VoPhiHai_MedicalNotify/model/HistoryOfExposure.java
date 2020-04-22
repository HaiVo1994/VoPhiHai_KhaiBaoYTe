package org.VoPhiHai_MedicalNotify.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history_of_exposure")
public class HistoryOfExposure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Entry entry;
    @ManyToOne(fetch = FetchType.EAGER)
    private Exposure exposure;
    private boolean hasExposure;
    private Date dateDeclare;

    public HistoryOfExposure() {
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

    public Exposure getExposure() {
        return exposure;
    }

    public void setExposure(Exposure exposure) {
        this.exposure = exposure;
    }

    public boolean isHasExposure() {
        return hasExposure;
    }

    public void setHasExposure(boolean hasExposure) {
        this.hasExposure = hasExposure;
    }

    public Date getDateDeclare() {
        return dateDeclare;
    }

    public void setDateDeclare(Date dateDeclare) {
        this.dateDeclare = dateDeclare;
    }
}
