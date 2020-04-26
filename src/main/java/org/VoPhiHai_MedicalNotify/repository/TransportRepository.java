package org.VoPhiHai_MedicalNotify.repository;

import org.VoPhiHai_MedicalNotify.model.Transport;
import org.VoPhiHai_MedicalNotify.model.TransportType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransportRepository extends CrudRepository<Transport,Long> {
    Transport findByTransportationNo(String transportationNo);
    List<Transport> findAllByTransportType(TransportType transportType);
}
