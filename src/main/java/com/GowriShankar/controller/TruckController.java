package com.GowriShankar.controller;


import com.GowriShankar.service.TruckService;
import com.GowriShankar.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/truck")
public class TruckController {

    @Autowired
    TruckService truckService;

    @GetMapping("/")
    public String getController(){
        return "You are in Truck controller class";
    }

    //----------------Add Truck (POST)--------------------------------------------------------------
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Truck addTruck(@RequestBody Truck truck){
        return truckService.addTruck(truck);
    }

    //--------------------REQUEST (GET)--------------------------------------------------------------
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Truck> getDetails(){
        return truckService.getAllTrucks();
    }

    @RequestMapping(value = "/id/{id}",method = RequestMethod.GET)
    public Truck getTruckById(@PathVariable(value = "id") Long id){
        return truckService.getTruckById(id);
    }

    @RequestMapping(value = "/name/{name}",method = RequestMethod.GET)
    public Truck getTruckByName(@PathVariable String name){
        return truckService.getTruckByName(name);
    }

    //----------------------------------MODIFY (PUT)----------------------------------------
    @RequestMapping(value = "/name/{name}",method = RequestMethod.PUT)
    public Truck modifyTruck (@PathVariable String name,@RequestBody Truck truck){
        return truckService.modifyTruckByName(name,truck);
    }

    @RequestMapping(value = "/id/{id}",method = RequestMethod.PUT)
    public Truck modifyTruck (@PathVariable(value = "id") Long id,@RequestBody Truck truck){
        return truckService.modifyTruckById(id,truck);
    }

    //------------------------------------DELETE (DELETE)----------------------------------------
    @RequestMapping(value = "/name/{name}",method = RequestMethod.DELETE)
    public String deleteTruck (@PathVariable String name){
        return truckService.deleteTruckByName(name);
    }

    @RequestMapping(value = "/id/{id}",method = RequestMethod.DELETE)
    public String deleteTruck (@PathVariable(value = "id") Long id){
        return truckService.deleteTruckId(id);
    }


}
