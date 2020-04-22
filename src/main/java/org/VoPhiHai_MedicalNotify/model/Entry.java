package org.VoPhiHai_MedicalNotify.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "entry")
public class Entry {
    @Id
    @Column(length = 80)
    private String id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idGate")
    private Gate gate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idMainTransport")
    private Transport transport;
    @Column(length = 32)
    private String seatNo;

    private Date departureDate;
    private Date immigrationDate;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String placeTravel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idOfProvinceDeparture")
    private District provinceDeparture;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idOfProvinceDestination")
    private District provinceDestination;

}
