package com.GowriShankar.service;


import com.GowriShankar.model.Excavator;
import com.GowriShankar.repository.ExcavatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class ExcavatorService {

    @Autowired
    ExcavatorRepository excavatorRepository;

    // ----->GET Soils List
    public List<Excavator> getAllExcavators() {
        return excavatorRepository.findAll();
    }

    public Excavator getExcavatorById(Long id){
        return excavatorRepository.findById(id).orElseThrow( ); // Write code for exception throwing
    }

    public Excavator getExcavatorByName(String name){
        return excavatorRepository.getExcavatorByName(name.toLowerCase(Locale.ROOT));
    }

    //------> POST add new soils
    public Excavator addExcavator(Excavator excavator){
        excavator.setName(excavator.getName().toLowerCase(Locale.ROOT));
        excavatorRepository.save(excavator);
        return excavatorRepository.getExcavatorByName(excavator.getName());
    }

    // -----> PUT modify the existing Excavator
    public Excavator modifyExcavatorByName(String name,Excavator modifiedExcavator){
        Excavator excavator = excavatorRepository.getExcavatorByName(name);

        excavator.setCapacityInCUM(modifiedExcavator.getCapacityInCUM());
        excavator.setFuelConsumptionPerHour(modifiedExcavator.getFuelConsumptionPerHour());
        excavator.setHireCostPerMonth(modifiedExcavator.getHireCostPerMonth());

        final Excavator updatedExcavator = excavatorRepository.save(excavator);

        return excavatorRepository.getExcavatorByName(excavator.getName());
    }
    public Excavator modifyExcavatorById(Long Id,Excavator modifiedExcavator){
        Excavator excavator = getExcavatorById(Id);

        excavator.setCapacityInCUM(modifiedExcavator.getCapacityInCUM());
        excavator.setFuelConsumptionPerHour(modifiedExcavator.getFuelConsumptionPerHour());
        excavator.setHireCostPerMonth(modifiedExcavator.getHireCostPerMonth());

        final Excavator updatedExcavator = excavatorRepository.save(excavator);

        return excavatorRepository.getExcavatorByName(excavator.getName());
    }



    //------> DELETE  delete the Excavator from DataBase
    public String deleteExcavatorByName(String name){
        Excavator excavator = excavatorRepository.getExcavatorByName(name);
        excavatorRepository.delete(excavator);
        return "DELETE = TRUE";
    }
    public String deleteExcavatorId(Long Id){
        Excavator excavator = getExcavatorById(Id);
        excavatorRepository.delete(excavator);
        return "DELETE = TRUE";
    }

}
