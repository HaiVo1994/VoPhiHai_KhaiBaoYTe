package org.VoPhiHai_MedicalNotify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "gate")
public class Gate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private short id;
    @Column(length = 100)
    private String name;
    @OneToMany(mappedBy = "gate", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Entry> entries;

    public Gate() {
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
