package org.VoPhiHai_MedicalNotify.service;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.Transport;
import org.VoPhiHai_MedicalNotify.model.TransportType;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface TransportService {
    Transport create(Transport transport);
    Transport create(JsonObject transportJson);
    Transport findByTransportationNo(String transportationNo );
    List<HashMap<String, Long>> countPersonHaveSymptomInPlane(Date begin, Date end);
    List<HashMap<String, Long>> countPersonHaveExposureInPlane(Date begin, Date end);
    long countPeronHaveSymptomByTransportType(Date begin, Date end, Short transportTypeId);
}
