package com.GowriShankar.service;

import com.GowriShankar.model.Excavator;
import com.GowriShankar.model.SimulationInputs;
import com.GowriShankar.model.Truck;
import com.GowriShankar.repository.ExcavatorRepository;
import com.GowriShankar.repository.SoilRepository;
import com.GowriShankar.repository.TruckRepository;
import org.hibernate.boot.registry.selector.StrategyRegistration;
import org.springframework.beans.factory.annotation.Autowired;

public class SimulationInputsService {

    @Autowired
    SimulationInputs inputs;
    SoilRepository soilRepository;
    ExcavatorRepository excavatorRepository;
    TruckRepository truckRepository;

    public double getBucketFillFactor(){
        return soilRepository.getSoilByName(inputs.getSoilName()).getBucketFillFactor();
    }

    public int getLooseCubicDensity(){
        return soilRepository.getSoilByName(inputs.getSoilName()).getLooseCubicDensity();
    }

    public String getSoilCondition(){
        return soilRepository.getSoilByName(inputs.getSoilName()).getSoilCondition();
    }

    public Excavator getExcavator(String excavator){
        return excavatorRepository.getExcavatorByName(excavator);
    }

    public Truck getTruck(String truck){
        return truckRepository.getTruckByName(truck);
    }


}
