package org.VoPhiHai_MedicalNotify.repository;

import org.VoPhiHai_MedicalNotify.model.Entry;
import org.VoPhiHai_MedicalNotify.model.HistoryOfExposure;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface HistoryOfExposureRepository extends CrudRepository<HistoryOfExposure, Long> {
    @Query("SELECT distinct (h.entry) FROM HistoryOfExposure h " +
            "WHERE h.dateDeclare>= :beginDate AND h.dateDeclare< :endDate " +
            "AND h.hasExposure = true ")
    List<Entry> getEntryHasExposure(@Param("beginDate")Date begin, @Param("endDate") Date end);
}
