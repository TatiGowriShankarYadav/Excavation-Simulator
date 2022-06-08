package com.GowriShankar.controller;


import com.GowriShankar.model.AddRequest;
import com.GowriShankar.model.SimulationInputs;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/api/add", method = RequestMethod.POST)
    @ResponseBody
    public Double getSum(@RequestBody AddRequest addRequest){
        return addRequest.getNum1() + addRequest.getNum2();
    }

    @RequestMapping(value = "api/path/inputs", method = RequestMethod.POST)
    @ResponseBody
    public List<String> getInputsList(@RequestBody SimulationInputs userInputs){
        List<String> listOfInputs = new ArrayList<>();
        listOfInputs.add(userInputs.getExcavator());
        listOfInputs.add(String.valueOf(userInputs.getHaulDistance()) );
        return listOfInputs;
    }


}