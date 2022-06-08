package com.GowriShankar.repository;


import com.GowriShankar.model.Soil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;



@Component
public interface SoilRepository extends JpaRepository<Soil,Long> {
//    public List<Soil> soilList;

    @Query("SELECT ud from SOIL_PROPERTIES ud where ud.name= ?1")
    public Soil getSoilByName(String SOIL) ;



}
