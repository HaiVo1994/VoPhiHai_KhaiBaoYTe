package org.VoPhiHai_MedicalNotify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "national")
public class National {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private short id;
    private String name;

    @OneToMany(mappedBy = "national", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Province> provinces;

    public National() {
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

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }
}
