package org.VoPhiHai_MedicalNotify.service.impl;

import org.VoPhiHai_MedicalNotify.model.National;
import org.VoPhiHai_MedicalNotify.model.Province;
import org.VoPhiHai_MedicalNotify.repository.ProvinceRepository;
import org.VoPhiHai_MedicalNotify.service.NationalService;
import org.VoPhiHai_MedicalNotify.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ProvinceServiceImpl  implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private NationalService nationalService;
    @Override
    public Province findById(Integer id) {
        return provinceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Province> findAllByNational(Short idNational) {
        National national = nationalService.findById(idNational);
        if (national!=null)
            return this.findAllByNational(national);

        return null;
    }

    @Override
    public List<Province> findAllByNational(National national) {
        return provinceRepository.findAllByNational(national);
    }

    @Override
    public List<HashMap<String, Long>> countPerSonByDeparture(Date begin, Date end) {
        return null;
    }

    @Override
    public List<HashMap<String, Long>> countPerSonHaveSymptomByDeparture(Date begin, Date end) {
        return null;
    }

    @Override
    public List<HashMap<String, Long>> countPerSonHaveSymptomByDestination(Date begin, Date end) {
        return null;
    }

    @Override
    public List<HashMap<String, Long>> countPerSonHaveExposureByDeparture(Date begin, Date end) {
        return null;
    }

    @Override
    public List<HashMap<String, Long>> countPerSonHaveExposureByDestination(Date begin, Date end) {
        return null;
    }
}
