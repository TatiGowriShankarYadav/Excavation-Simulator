package com.GowriShankar.service;

import com.GowriShankar.model.Excavator;
import com.GowriShankar.model.Truck;
import com.GowriShankar.repository.ExcavatorRepository;
import com.GowriShankar.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class TruckService {

    @Autowired
    TruckRepository truckRepository;

    // ----->GET Soils List
    public List<Truck> getAllTrucks() {
        return truckRepository.findAll();
    }

    public Truck getTruckById(Long id){
        return truckRepository.findById(id).orElseThrow( ); // Write code for exception throwing
    }

    public Truck getTruckByName(String name){
        return truckRepository.getTruckByName(name.toLowerCase(Locale.ROOT));
    }

    //------> POST add new soils
    public Truck addTruck(Truck truck){
        truck.setName(truck.getName().toLowerCase(Locale.ROOT));
        truckRepository.save(truck);
        return truckRepository.getTruckByName(truck.getName());
    }


    // -----> PUT modify the existing Excavator
    public Truck modifyTruckByName(String name,Truck modifiedTruck){
        Truck truck = truckRepository.getTruckByName(name);

        truck.setBodyWeightInKG(modifiedTruck.getBodyWeightInKG());
        truck.setCapacityInCUM(modifiedTruck.getCapacityInCUM());
        truck.setHorsePower(modifiedTruck.getHorsePower());
        truck.setMileageInKmPerLit(modifiedTruck.getMileageInKmPerLit());
        truck.setHireCostPerMonth(modifiedTruck.getHireCostPerMonth());
        truck.setPayloadWeightInKG(modifiedTruck.getPayloadWeightInKG());

        final Truck updatedTruck = truckRepository.save(truck);

        return truckRepository.getTruckByName(truck.getName());
    }
    public Truck modifyTruckById(Long Id,Truck modifiedTruck){
        Truck truck = getTruckById(Id);

        truck.setBodyWeightInKG(modifiedTruck.getBodyWeightInKG());
        truck.setCapacityInCUM(modifiedTruck.getCapacityInCUM());
        truck.setHorsePower(modifiedTruck.getHorsePower());
        truck.setMileageInKmPerLit(modifiedTruck.getMileageInKmPerLit());
        truck.setHireCostPerMonth(modifiedTruck.getHireCostPerMonth());
        truck.setPayloadWeightInKG(modifiedTruck.getPayloadWeightInKG());

        final Truck updatedTruck = truckRepository.save(truck);

        return truckRepository.getTruckByName(truck.getName());
    }



    //------> DELETE  delete the Excavator from DataBase
    public String deleteTruckByName(String name){
        Truck truck = truckRepository.getTruckByName(name);
        truckRepository.delete(truck);
        return "DELETE = TRUE";
    }
    public String deleteTruckId(Long Id){
        Truck truck = getTruckById(Id);
       truckRepository.delete(truck);
        return "DELETE = TRUE";
    }

}
