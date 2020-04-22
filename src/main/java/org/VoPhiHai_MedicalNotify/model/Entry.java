package org.VoPhiHai_MedicalNotify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "entry")
public class Entry {
    @Id
    @Column(length = 80)
    private String id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idGate")
    @NotNull
    private Gate gate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idMainTransport")
    @NotNull
    private Transport transport;
    @Column(length = 32)
    private String seatNo;

    @NotNull
    private Date departureDate;
    @NotNull
    private Date immigrationDate;
    @Column(columnDefinition = "MEDIUMTEXT")
    @NotNull
    private String placeTravel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idOfProvinceDeparture")
    @NotNull
    private District provinceDeparture;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idOfProvinceDestination")
    @NotNull
    private District provinceDestination;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPerson")
    @NotNull
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idContactHelpDeclare")
    @JsonIgnore
    private Contact contactHelpDeclare;

    @OneToMany(mappedBy = "entry", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Status> statuses;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String listCure;

    @OneToMany(mappedBy = "entry", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<HistoryOfExposure> historyOfExposures;

    @Column(length = 200)
    private String createBy;
    private Date createAt;
    private boolean isDelete;
    @Column(length = 200)
    private String updateBy;
    private Date updateAt;


}
