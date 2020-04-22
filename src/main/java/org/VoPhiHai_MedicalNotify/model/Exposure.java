package org.VoPhiHai_MedicalNotify.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exposure")
public class Exposure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ColumnDefault(value = "1")
    private boolean isEnabled;
    @OneToMany(mappedBy = "exposure", fetch = FetchType.LAZY)
    private List<HistoryOfExposure> historyOfExposures;

    public Exposure() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public List<HistoryOfExposure> getHistoryOfExposures() {
        return historyOfExposures;
    }

    public void setHistoryOfExposures(List<HistoryOfExposure> historyOfExposures) {
        this.historyOfExposures = historyOfExposures;
    }
}
