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
}
