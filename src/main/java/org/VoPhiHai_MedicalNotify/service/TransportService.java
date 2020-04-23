package org.VoPhiHai_MedicalNotify.service;

import org.VoPhiHai_MedicalNotify.model.Transport;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface TransportService {
    Transport create(Transport transport);
    List<HashMap<String, Long>> countPersonHaveSymptomInPlane(Date begin, Date end);
    List<HashMap<String, Long>> countPersonHaveExposureInPlane(Date begin, Date end);
    long countPeronHaveSymptomByTransportType(Date begin, Date end, Short transportTypeId);
}
