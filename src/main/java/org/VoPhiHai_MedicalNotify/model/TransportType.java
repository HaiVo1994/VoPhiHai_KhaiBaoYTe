package org.VoPhiHai_MedicalNotify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "transportType")
public class TransportType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private short id;
    private String name;
    @OneToMany(mappedBy = "transportType", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Transport> transports;
}
