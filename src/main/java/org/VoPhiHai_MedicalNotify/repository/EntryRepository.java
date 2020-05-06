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
            "WHERE (e.immigrationDate >= :startDate) AND (e.immigrationDate<= :endDate)" +
            "GROUP BY (e.statuses.size)")
    List<Statistical> statisticalSymptomDeparture(@Param("startDate") Date begin, @Param("endDate") Date end);

    @Query("SELECT new org.VoPhiHai_MedicalNotify.model.support.Statistical_Entry(e.immigrationDate,COUNT(e)) " +
            "FROM Entry e " +
            "WHERE (e.immigrationDate >= :startDate) AND (e.immigrationDate<= :endDate)" +
            "GROUP BY e.immigrationDate " +
            "ORDER BY e.immigrationDate asc ")
    List<Statistical_Entry> statisticalEntry(@Param("startDate") Date begin, @Param("endDate") Date end);

    @Query("SELECT e " +
            "FROM Entry e " +
            "WHERE (e.immigrationDate >= :startDate) AND (e.immigrationDate<= :endDate)" +
            "GROUP BY e.immigrationDate " +
            "ORDER BY e.immigrationDate asc ")
    List<Entry> getEntriesByImmigrationDate(@Param("startDate") Date begin, @Param("endDate") Date end);
}
