package com.GowriShankar.controller;


import com.GowriShankar.model.SimulationInputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/simulationInputs")
public class UserInputController {



    @GetMapping("/")
    public String getController() {
        return "You are in userInput Controller";
    }

    @RequestMapping(value = "/getDetails",method = RequestMethod.GET )
    @ResponseBody
    public SimulationInputs getDetails(SimulationInputs simulationInputs){
        return simulationInputs;
    }
}

