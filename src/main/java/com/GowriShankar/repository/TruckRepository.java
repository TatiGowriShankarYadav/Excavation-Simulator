package com.GowriShankar.repository;

import com.GowriShankar.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TruckRepository extends JpaRepository<Truck, Long> {

    @Query("SELECT ud from TRUCK ud where ud.name=?1")
    Truck getTruckByName(String truck);
}
