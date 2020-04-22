package org.VoPhiHai_MedicalNotify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 150)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nationId")
    private National national;

    @OneToMany(mappedBy = "province")
    @JsonIgnore
    private List<District> districts;

    @OneToMany(mappedBy = "provinceDeparture")
    @JsonIgnore
    private List<Entry> departureEntry;
    @OneToMany(mappedBy = "provinceDestination")
    @JsonIgnore
    private List<Entry> destinationEntry;
}
