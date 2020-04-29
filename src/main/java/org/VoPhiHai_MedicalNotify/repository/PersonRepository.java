package org.VoPhiHai_MedicalNotify.repository;

import org.VoPhiHai_MedicalNotify.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person,String> {
    @Query("SELECT p FROM Person p " +
            "WHERE p.passport=:passport AND p.isEnabled=true ")
    List<Person> findByPassport(@Param("passport") String passport);
}
