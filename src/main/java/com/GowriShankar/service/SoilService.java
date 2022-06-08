package com.GowriShankar.service;

import com.GowriShankar.model.Soil;
import com.GowriShankar.repository.SoilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Locale;

@Component
public class SoilService {

    @Autowired
    SoilRepository soilrepository;

    // POST  Add Soil
    public Soil addSoil(Soil soil){
        soil.setName(soil.getName().toLowerCase(Locale.ROOT));
        soilrepository.save(soil);
        return soil;
    }

    //GET  REQUEST
    public Soil getSoilById(Long id){
        return soilrepository.findById(id).orElseThrow();//----> add code for throw
    }

    public Soil getSoilByName(String name){
        return soilrepository.getSoilByName(name.toLowerCase(Locale.ROOT));//----> add code for throw
    }

    public List<Soil> getAllSoils(){
        return soilrepository.findAll();
    }

    // PUT   modify Soil
    public Soil modifySoilById(Long soilId,Soil soilDetails) {
        Soil soil = getSoilById(soilId);

        soil.setBankCubicDensity(soilDetails.getBankCubicDensity());
        soil.setBucketFillFactor(soilDetails.getBucketFillFactor());
        soil.setLooseCubicDensity(soilDetails.getLooseCubicDensity());
        soil.setSwell(soilDetails.getSwell());
        soil.setSoilCondition(soilDetails.getSoilCondition());
        final Soil updatedSoil = soilrepository.save(soil);
        return getSoilById(soilId);
    }
    public Soil modifySoilByName(String name,Soil soilDetails) {
        Soil soil = getSoilByName(name);

        soil.setBankCubicDensity(soilDetails.getBankCubicDensity());
        soil.setBucketFillFactor(soilDetails.getBucketFillFactor());
        soil.setLooseCubicDensity(soilDetails.getLooseCubicDensity());
        soil.setSwell(soilDetails.getSwell());
        soil.setSoilCondition(soilDetails.getSoilCondition());
        final Soil updatedSoil = soilrepository.save(soil);
        return getSoilByName(name);
    }

    //DELETE      DELETE
    public String deleteSoilById(Long soilId)  {
        soilrepository.delete(getSoilById(soilId));
        return "DELETE : TRUE";
    }

    public String deleteSoilByName(String name)  {
        soilrepository.delete(getSoilByName(name));
        return "DELETE : TRUE";
    }

}
