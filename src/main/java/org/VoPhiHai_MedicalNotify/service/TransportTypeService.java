package org.VoPhiHai_MedicalNotify.service;

import org.VoPhiHai_MedicalNotify.model.TransportType;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface TransportTypeService {
    Iterable<TransportType> getAll();
    TransportType findById(Short id);
    List<HashMap<String, Long>> countPerson(Date begin, Date end);
    List<HashMap<String, Long>> countPersonHaveExposure(Date begin, Date end);
    List<HashMap<String, Long>> countPersonHaveSymptom(Date begin, Date end);
}
