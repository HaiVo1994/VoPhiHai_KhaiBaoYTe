package org.VoPhiHai_MedicalNotify.repository;

import org.VoPhiHai_MedicalNotify.model.Status;
import org.VoPhiHai_MedicalNotify.model.support.Statistical;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StatusRepository extends CrudRepository<Status, Long> {
    @Query("SELECT new org.VoPhiHai_MedicalNotify.model.support.Statistical(s.entry.id, " +
            "SUM(CASE s.haveSymptom WHEN true THEN 1 ELSE 0 END)) " +
            "FROM Status s " +
            "WHERE s.entry.immigrationDate >= :startDate AND s.entry.immigrationDate<= :endDate " +
            "GROUP BY s.entry")
    List<Statistical> countSymptom(@Param("startDate") Date begin, @Param("endDate") Date end);

    @Query("SELECT new  org.VoPhiHai_MedicalNotify.model.support.Statistical(s.symptom.name, " +
            "SUM(CASE s.haveSymptom WHEN true THEN 1 ELSE 0 END))" +
            "FROM Status s " +
            "WHERE s.entry.immigrationDate >= :startDate AND s.entry.immigrationDate <= :endDate " +
            "GROUP BY s.symptom")
    List<Statistical> countForSymptomType(@Param("startDate") Date begin, @Param("endDate") Date end);
}
