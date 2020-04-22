package org.VoPhiHai_MedicalNotify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name =  "transport")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transportType")
    private TransportType transportType;
    @Column(length = 72)
    private String transportationNo;
    private String note;

    @OneToMany(mappedBy = "transport", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Entry> entries;

    public Transport() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public String getTransportationNo() {
        return transportationNo;
    }

    public void setTransportationNo(String transportationNo) {
        this.transportationNo = transportationNo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
