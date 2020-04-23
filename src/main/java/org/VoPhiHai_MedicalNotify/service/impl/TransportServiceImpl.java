package org.VoPhiHai_MedicalNotify.service.impl;

import org.VoPhiHai_MedicalNotify.model.Transport;
import org.VoPhiHai_MedicalNotify.repository.TransportRepository;
import org.VoPhiHai_MedicalNotify.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TransportServiceImpl implements TransportService {
    @Autowired
    private TransportRepository transportRepository;
    @Override
    public Transport create(Transport transport) {
        return transportRepository.save(transport);
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
