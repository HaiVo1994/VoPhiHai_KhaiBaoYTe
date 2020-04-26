package org.VoPhiHai_MedicalNotify.service.impl;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.Transport;
import org.VoPhiHai_MedicalNotify.model.TransportType;
import org.VoPhiHai_MedicalNotify.repository.TransportRepository;
import org.VoPhiHai_MedicalNotify.service.TransportService;
import org.VoPhiHai_MedicalNotify.service.TransportTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TransportServiceImpl implements TransportService {
    @Autowired
    private TransportRepository transportRepository;
    @Autowired
    private TransportTypeService transportTypeService;

    @Override
    public Transport create(Transport transport) {
        return transportRepository.save(transport);
    }

    @Override
    public Transport create(JsonObject transportJson) {
        String transportationNo = (String) transportJson.get("transportationNo");
        Transport transport = this.findByTransportationNo(transportationNo);
        if (transport!=null){
            return transport;
        }

        transport = new Transport();
        TransportType transportType = transportTypeService.
                findById(Short.parseShort((String) transportJson.get("transportType")));
        if (transportType==null)
            return null;

        transport.setTransportType(transportType);
        transport.setTransportationNo(transportationNo);
        transport.setNote((String) transportJson.get("note"));
        return this.create(transport);
    }

    @Override
    public Transport findById(Long id) {
        return transportRepository.findById(id).orElse(null);
    }

    @Override
    public Transport findByTransportationNo(String transportationNo) {
        return transportRepository.findByTransportationNo(transportationNo);
    }

    @Override
    public List<Transport> findByType(Short idType) {
        TransportType transportType = transportTypeService.findById(idType);
        return transportRepository.findAllByTransportType(transportType);
    }

    @Override
    public List<HashMap<String, Long>> countPersonHaveSymptomInPlane(Date begin, Date end) {
        return null;
    }

    @Override
    public List<HashMap<String, Long>> countPersonHaveExposureInPlane(Date begin, Date end) {
        return null;
    }

    @Override
    public long countPeronHaveSymptomByTransportType(Date begin, Date end, Short transportTypeId) {
        return 0;
    }
}
