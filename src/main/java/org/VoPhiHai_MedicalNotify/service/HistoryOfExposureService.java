package org.VoPhiHai_MedicalNotify.service;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.VoPhiHai_MedicalNotify.model.Entry;
import org.VoPhiHai_MedicalNotify.model.Exposure;
import org.VoPhiHai_MedicalNotify.model.HistoryOfExposure;

import java.util.Date;
import java.util.List;

public interface HistoryOfExposureService {
    HistoryOfExposure create(HistoryOfExposure historyOfExposure);
    HistoryOfExposure create(HistoryOfExposure historyOfExposure, Entry entry, Exposure exposure);
    void declare(List<HistoryOfExposure> historyOfExposures, Entry entry, Exposure exposure);
    void declare(List<JsonObject> historyOfExposures, String entryId);
    List<Entry> getListEntryHaveExposure(Date begin, Date end);
    List<Entry> getListEntryHaveExposureById(Date begin, Date end, Long exposureId);
}
