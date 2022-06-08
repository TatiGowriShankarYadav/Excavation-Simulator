package com.GowriShankar.repository;

import com.GowriShankar.model.Excavator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface ExcavatorRepository extends JpaRepository<Excavator,Long> {

    @Query("SELECT ud from EXCAVATOR ud where ud.name=?1")
    Excavator getExcavatorByName(String excavatorName);

}
