package org.VoPhiHai_MedicalNotify.repository;

import org.VoPhiHai_MedicalNotify.model.Transport;
import org.springframework.data.repository.CrudRepository;

public interface TransportRepository extends CrudRepository<Transport,Long> {
    Transport findByTransportationNo(String transportationNo);
}
