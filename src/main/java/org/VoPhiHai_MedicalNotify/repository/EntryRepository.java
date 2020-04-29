package org.VoPhiHai_MedicalNotify.repository;

import org.VoPhiHai_MedicalNotify.model.Entry;
import org.VoPhiHai_MedicalNotify.model.support.Statistical;
import org.VoPhiHai_MedicalNotify.model.support.Statistical_Entry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface EntryRepository extends CrudRepository<Entry,String> {
    @Query("SELECT " +
            "new org.VoPhiHai_MedicalNotify.model.support.Statistical(e.statuses.size, COUNT(e))  " +
            "FROM Entry e " +
            "WHERE (e.departureDate >= :startDate) AND (e.departureDate<= :endDate)" +
            "GROUP BY (e.statuses.size)")
    List<Statistical> statisticalSymptomDeparture(@Param("startDate") Date begin, @Param("endDate") Date end);
}
