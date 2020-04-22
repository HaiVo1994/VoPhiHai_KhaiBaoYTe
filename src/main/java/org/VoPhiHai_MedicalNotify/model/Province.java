package org.VoPhiHai_MedicalNotify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 150)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nationId")
    private National national;

    @OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<District> districts;

    @OneToMany(mappedBy = "provinceDeparture", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Entry> departureEntry;
    @OneToMany(mappedBy = "provinceDestination", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Entry> destinationEntry;

    public Province() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public National getNational() {
        return national;
    }

    public void setNational(National national) {
        this.national = national;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public List<Entry> getDepartureEntry() {
        return departureEntry;
    }

    public void setDepartureEntry(List<Entry> departureEntry) {
        this.departureEntry = departureEntry;
    }

    public List<Entry> getDestinationEntry() {
        return destinationEntry;
    }

    public void setDestinationEntry(List<Entry> destinationEntry) {
        this.destinationEntry = destinationEntry;
    }
}
