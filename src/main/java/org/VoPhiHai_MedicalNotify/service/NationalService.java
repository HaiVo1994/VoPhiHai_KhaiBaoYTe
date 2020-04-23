package org.VoPhiHai_MedicalNotify.service;

import org.VoPhiHai_MedicalNotify.model.National;

import java.util.List;

public interface NationalService {
    Iterable<National> findAll();
    National findById(Short id);
}
