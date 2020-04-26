package org.VoPhiHai_MedicalNotify.service;

import org.VoPhiHai_MedicalNotify.model.Exposure;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface ExposureService {
    Iterable<Exposure> findAll();
    Exposure findById(Long id);
    List<Exposure> findAllEnable();
    HashMap<String, Exposure> mapEnable();
    List<HashMap<String, Long>> countPersonHaveExposure(Date begin, Date end);
}
