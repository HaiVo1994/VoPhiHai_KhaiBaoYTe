package org.VoPhiHai_MedicalNotify.service;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.Entry;
import org.VoPhiHai_MedicalNotify.model.Exposure;
import org.VoPhiHai_MedicalNotify.model.HistoryOfExposure;
import org.VoPhiHai_MedicalNotify.model.support.Statistical;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public interface HistoryOfExposureService {
    HistoryOfExposure create(HistoryOfExposure historyOfExposure);
    HistoryOfExposure create(HistoryOfExposure historyOfExposure, Entry entry, Exposure exposure);
    List<HistoryOfExposure> declare(List<HistoryOfExposure> historyOfExposures, Entry entry, Exposure exposure);
    List<HistoryOfExposure> declare(List<LinkedHashMap<String,String>> historyOfExposures, Entry entry);
    List<Entry> getListEntryHaveExposure(Date begin, Date end);
    List<Entry> getListEntryHaveExposureById(Date begin, Date end, Long exposureId);

    List<JsonObject> statisticalByCountPerson(Date begin, Date end);
    List<JsonObject> statisticalByCountPerson(JsonObject dateEntry);
    List<Statistical> statisticalByTypeExposure(Date begin, Date end);
    List<Statistical> statisticalByTypeExposure(JsonObject dateEntry);
}
