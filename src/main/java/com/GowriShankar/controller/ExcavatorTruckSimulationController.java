package com.GowriShankar.controller;


import com.GowriShankar.model.SimulationInputs;
import com.GowriShankar.model.SimulationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/excavatorSimulation")
public class ExcavatorTruckSimulationController {
    @Autowired
    ExcavatorTruckSimulationController excavatorTruckSimulationController;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getController(){
        return "You are in Excavator Truck Simulation Controller class";
    }

    @RequestMapping(value = "/simulate",method = RequestMethod.POST)
    public List<SimulationResponse> getSimulationResults(@RequestBody SimulationInputs simulationInputs){
        return excavatorTruckSimulationController.getSimulationResults(simulationInputs);
    }

    @RequestMapping(value = "/userInputs",method = RequestMethod.POST)
    public SimulationInputs getInputsFromUser(@RequestBody SimulationInputs simulationInputs){
        return simulationInputs;
    }




}
